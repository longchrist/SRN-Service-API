package com.srn.api.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.srn.api.model.dto.SrnPointDto;
import com.srn.api.model.entity.SrnPoint;
import com.srn.api.repo.ISrnUserPointsRepo;
import com.srn.api.service.ISrnUserDeviceService;
import com.srn.api.service.ISrnUserPointsService;
import com.srn.api.utils.FormatterUtils;
import com.srn.api.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

@Transactional
@Service("srnUserPointsService")
public class SrnUserPointsServiceImpl implements ISrnUserPointsService {

    @Autowired
    ISrnUserPointsRepo srnUserPointsRepo;

    @Autowired
    ISrnUserDeviceService srnUserDeviceService;

    @Override
    public SrnPointDto addUserPoints(String param, String session) {
        SrnPointDto dto = convertAddPointBody(param);
        SrnPoint entity = new SrnPoint();
        entity.setUserId(dto.getUserId());
        entity.setBrandId(dto.getBrandId());
        entity.setStoreId(dto.getStoreId());
        entity.setReceiptNumber(dto.getReceiptNumber());
        entity.setCreated(FormatterUtils.getCurrentTimestamp());
        entity.setLastUpdated(FormatterUtils.getCurrentTimestamp());
        return srnUserPointsRepo.save(entity).toDto();
    }

    @Override
    public long getTotalUserPoints(String session) {
        List<SrnPoint> userPoints = srnUserPointsRepo.findPointsByUserId(srnUserDeviceService.getUserDeviceData(session).getUserId());
        long  totalPoints = 0;
        if (userPoints != null && userPoints.size() > 0) {
            for (SrnPoint p : userPoints) {
                totalPoints = totalPoints + p.getPointValue();
            }
        }
        return totalPoints;
    }

    @Override
    public void deductUserPoints(String param, String session) {

    }

    private SrnPointDto convertAddPointBody(String requestBody) {
        String json = SecurityUtils.getInstance().setData(requestBody).setMethod(SecurityUtils.Method.DATA_DECRYPT).build();
        ObjectMapper jsonMapper = new ObjectMapper();
        try {
            return jsonMapper.readValue(json, SrnPointDto.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}