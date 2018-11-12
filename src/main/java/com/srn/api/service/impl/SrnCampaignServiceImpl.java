package com.srn.api.service.impl;

import com.srn.api.model.dto.SrnCampaignDto;
import com.srn.api.model.entity.SrnCampaign;
import com.srn.api.repo.ISrnCampaignRepo;
import com.srn.api.service.ISrnCampaignService;
import com.srn.api.utils.FormatterUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Transactional
@Service("srnCampaignService")
public class SrnCampaignServiceImpl implements ISrnCampaignService {

    @Autowired
    ISrnCampaignRepo campaignRepo;

    @Override
    public List<SrnCampaign> getAllCampaign() {
        return campaignRepo.findAll();
    }

    @Override
    public SrnCampaign getCampaign(long campaignId) {
        return campaignRepo.findCampaignById(campaignId);
    }

    @Override
    public boolean isCampaignActive(SrnCampaign campaign) {
        return campaign.getEndDate().after(FormatterUtils.getCurrentTimestamp());
    }

    @Override
    public List<SrnCampaignDto> getCampaignBrand(String brandId) {
        List<SrnCampaign> campaignList = campaignRepo.findCampaignByBrandId(brandId);

        List<SrnCampaignDto> dtos = campaignList.stream().map(c -> new SrnCampaignDto(c.getId(), c.getBrandId(), c.getCampaignType(),
                c.getDescription(), c.getTnc(), c.getStartDate().toInstant().toEpochMilli(), c.getEndDate().toInstant().toEpochMilli(),
                c.getRequiredPoints())).collect(Collectors.toList());

        return dtos;
    }
}