package com.srn.api.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.srn.api.model.dto.SrnCampaignDto;
import com.srn.api.model.dto.SrnCampaignRedeemDto;
import com.srn.api.model.entity.SrnCampaign;
import com.srn.api.model.entity.SrnCampaignImage;
import com.srn.api.model.entity.SrnDevice;
import com.srn.api.repo.ISrnCampaignImageRepo;
import com.srn.api.repo.ISrnCampaignRepo;
import com.srn.api.repo.ISrnDeviceRepo;
import com.srn.api.service.ISrnCampaignService;
import com.srn.api.utils.FormatterUtils;
import com.srn.api.utils.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service("srnCampaignService")
public class SrnCampaignServiceImpl implements ISrnCampaignService {

    @Autowired
    ISrnCampaignRepo campaignRepo;

    @Autowired
    ISrnCampaignImageRepo campaignImageRepo;

    @Autowired
    ISrnDeviceRepo deviceRepo;

    private static final Logger LOGGER = LoggerFactory.getLogger(SrnCampaignServiceImpl.class);
    private static String TAG = SrnCampaignServiceImpl.class.getSimpleName();

    @Override
    public List<SrnCampaignDto> getAllCampaign(String session) {
        String json = SecurityUtils.getInstance().setData(session).setMethod(SecurityUtils.Method.SESSION_DECRYPT).build();
        ObjectMapper mapper = new ObjectMapper();
        SrnDevice dto = null;
        try {
            dto = mapper.readValue(json, SrnDevice.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        SrnDevice device = deviceRepo.findByImei(dto.getImei());

        List<SrnCampaign> campaignList = campaignRepo.findAll();

        LOGGER.info("campaignData|total campaign repo {}", campaignList.size());

        List<SrnCampaignImage> campaignImageList = campaignImageRepo.findAll();
        HashMap<Long, SrnCampaignImage> imageHashMap = new HashMap<>();
        for (SrnCampaignImage i : campaignImageList )
            imageHashMap.put(i.getCampaignId(), i);

        List<SrnCampaignDto> dtos = campaignList.stream()
                .filter(c -> isCampaignActive(c))
                .map(c -> new SrnCampaignDto(c.getId(), c.getBrandId(), c.getCampaignName(), c.getCampaignType(),
                        c.getDescription(), c.getTnc(), c.getStartDate().toInstant().toEpochMilli(), c.getEndDate().toInstant().toEpochMilli(),
                        c.getRequiredPoints())).collect(Collectors.toList());
        dtos.forEach(srnCampaignDto -> updateDto(srnCampaignDto, device.getScreenWidth(), imageHashMap));
        LOGGER.info("campaignData|{}", dtos.toString());
        return dtos;
    }

    private SrnCampaignDto updateDto(SrnCampaignDto dto, long screenWidth, HashMap<Long, SrnCampaignImage> map) {
        if (map.containsKey(dto.getId())) {
            int height = (int)screenWidth / 2;
            if (height <= 200) {
                dto.setImageUrl(map.get(dto.getId()).getImg400x200());
            } else if (height > 200 && height <= 400) {
                dto.setImageUrl(map.get(dto.getId()).getImg800x400());
            } else if (height > 400 && height <= 720) {
                dto.setImageUrl(map.get(dto.getId()).getImg1440x720());
            } else {
                dto.setImageUrl(map.get(dto.getId()).getImg1440x720());
            }
        }
        return dto;
    }

    @Override
    public SrnCampaignDto getCampaign(long campaignId) {
        SrnCampaign c = campaignRepo.findCampaignById(campaignId);
        SrnCampaignDto dto = new SrnCampaignDto();
        if (c != null) {
            dto = new SrnCampaignDto(c.getId(), c.getBrandId(), c.getCampaignName(), c.getCampaignType(),
                    c.getDescription(), c.getTnc(), c.getStartDate().toInstant().toEpochMilli(), c.getEndDate().toInstant().toEpochMilli(),
                    c.getRequiredPoints());
        }
        return dto;
    }

    @Override
    public boolean isCampaignActive(SrnCampaign campaign) {
        return campaign.getEndDate().after(FormatterUtils.getCurrentTimestamp());
    }

    @Override
    public List<SrnCampaignDto> getCampaignBrand(String brandId) {
        List<SrnCampaign> campaignList = campaignRepo.findCampaignByBrandId(brandId);
        List<SrnCampaignDto> dtos = new ArrayList<>();
        if (campaignList != null ) {
            dtos = campaignList.stream()
                    .filter(c -> isCampaignActive(c))
                    .map(c -> new SrnCampaignDto(c.getId(), c.getBrandId(), c.getCampaignName(), c.getCampaignType(),
                            c.getDescription(), c.getTnc(), c.getStartDate().toInstant().toEpochMilli(), c.getEndDate().toInstant().toEpochMilli(),
                            c.getRequiredPoints())).collect(Collectors.toList());
        }
        return dtos;
    }

    @Override
    public void redeem(String redeemRequest) {
        String redeemPayload = SecurityUtils.getInstance().setData(redeemRequest).setMethod(SecurityUtils.Method.DATA_DECRYPT).build();
        try {
            ObjectMapper mapper = new ObjectMapper();
            SrnCampaignRedeemDto redeemDto = mapper.readValue(redeemPayload, SrnCampaignRedeemDto.class);
            String sessionPlain = SecurityUtils.getInstance().setData(redeemDto.getSessionId()).setMethod(SecurityUtils.Method.SESSION_DECRYPT).build();
            SrnDevice device = mapper.readValue(sessionPlain, SrnDevice.class);
            LOGGER.info(TAG, "Redeem user session : {}", sessionPlain);
            LOGGER.info(TAG, "Redeem payload : {}", redeemPayload);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}