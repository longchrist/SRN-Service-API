package com.srn.api.service.impl;/*
 Author: vikraa
 created: 1/5/19 - 2:16 PM
*/

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.srn.api.model.dto.SrnVoucherDto;
import com.srn.api.model.entity.SrnUserDevice;
import com.srn.api.repo.ISrnCampaignRepo;
import com.srn.api.repo.ISrnUserDeviceRepo;
import com.srn.api.repo.ISrnVoucherCampaignDetailRepo;
import com.srn.api.service.ISrnVoucherService;
import org.apache.http.util.TextUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service("SrnVoucherServiceImpl")
public class SrnVoucherServiceImpl implements ISrnVoucherService {

    @Autowired
    ISrnVoucherCampaignDetailRepo voucherCampaignDetailRepo;

    @Autowired
    ISrnUserDeviceRepo userDeviceRepo;

    @Override
    public List<SrnVoucherDto> findUserVoucher(String session) {
        SrnUserDevice userDevice = userDeviceRepo.findBySessionId(session);
        return findUserVoucher(userDevice.getUserId());
    }

    private List<SrnVoucherDto> findUserVoucher(long userId) {
        String data = voucherCampaignDetailRepo.findVoucherUser(userId);
        List<SrnVoucherDto> voucherList = new ArrayList<>();
        if (!TextUtils.isEmpty(data)) {
            String jsonstring = data.substring(1, data.length() - 1);
            ObjectMapper mapper = new ObjectMapper();
            try {
                voucherList = mapper.readValue(jsonstring, new TypeReference<List<SrnVoucherDto>>(){});
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return voucherList;
    }
}