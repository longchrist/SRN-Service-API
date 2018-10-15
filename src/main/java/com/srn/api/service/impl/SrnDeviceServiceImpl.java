package com.srn.api.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.srn.api.model.SrnResponse;
import com.srn.api.model.entity.SrnDevice;
import com.srn.api.model.response.Session;
import com.srn.api.repo.ISrnDeviceRepo;
import com.srn.api.service.ISrnDeviceService;
import com.srn.api.service.ISrnUserDeviceService;
import com.srn.api.utils.FormatterUtils;
import com.srn.api.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;


@Transactional
@Service("srnDeviceService")
public class SrnDeviceServiceImpl implements ISrnDeviceService {

    @Autowired
    ISrnDeviceRepo srnDeviceRepo;

    @Autowired
    ISrnUserDeviceService userDeviceService;

    @Override
    public Session registerDevice(String param) {
        String json = SecurityUtils.getInstance().setData(param).setMethod(SecurityUtils.Method.DATA_DECRYPT).build();
        ObjectMapper jsonMapper = new ObjectMapper();
        SrnDevice deviceParam = null;
        try {
            deviceParam = jsonMapper.readValue(json, SrnDevice.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        SrnDevice entity = srnDeviceRepo.findByImei(deviceParam.getImei());
        if ( entity != null) {
            entity.setLastUpdated(FormatterUtils.getCurrentTimestamp());
            entity = srnDeviceRepo.save(entity);
        } else {
            deviceParam.setCreated(FormatterUtils.getCurrentTimestamp());
            deviceParam.setLastUpdated(FormatterUtils.getCurrentTimestamp());
            entity = srnDeviceRepo.save(deviceParam);
        }
        Session session = new Session(entity);
        session.setSessionId(SecurityUtils.getInstance().setData(session).setMethod(SecurityUtils.Method.SESSION_ENCRYPT).build());
        userDeviceService.registerUserDeviceSession(session.getSessionId(), entity.getId());
        return session;
    }

    @Override
    public SrnDevice findDeviceById(long id) {
        return srnDeviceRepo.findById(id);
    }

    @Override
    public boolean unregisterDevice(SrnDevice device) {
        if (srnDeviceRepo.findByImei(device.getImei()) != null) {
            srnDeviceRepo.delete(device);
            return true;
        }
        return false;
    }

}