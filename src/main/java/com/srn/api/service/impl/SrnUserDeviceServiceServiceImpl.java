package com.srn.api.service.impl;

import com.srn.api.model.entity.SrnUserDevice;
import com.srn.api.repo.ISrnUserDeviceRepo;
import com.srn.api.service.ISrnUserDeviceService;
import com.srn.api.utils.FormatterUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service("srnUserDeviceService")
public class SrnUserDeviceServiceServiceImpl implements ISrnUserDeviceService {

    @Autowired
    ISrnUserDeviceRepo srnUserDeviceRepo;

    @Override
    public void registerUserDeviceSession(String session, long deviceId) {
        SrnUserDevice userDevice = new SrnUserDevice();
        userDevice.setId(deviceId);
        userDevice.setSessionId(session);
        userDevice.setUserId(0L);
        userDevice.setCreated(FormatterUtils.getCurrentTimestamp());
        userDevice.setLastUpdated(FormatterUtils.getCurrentTimestamp());
        srnUserDeviceRepo.save(userDevice);
    }

    @Override
    public void registerUserId(String session, long userId) {
        SrnUserDevice userDevice = srnUserDeviceRepo.findBySessionId(session);
        if (userDevice != null) {
            userDevice.setUserId(userId);
            srnUserDeviceRepo.save(userDevice);
        }
    }

    @Override
    public void removeUserDeviceSession(String session) {
        SrnUserDevice userDevice = srnUserDeviceRepo.findBySessionId(session);
        if (userDevice != null) {
            srnUserDeviceRepo.delete(userDevice);
        }
    }

    @Override
    public SrnUserDevice getUserDeviceData(String session) {
        return srnUserDeviceRepo.findBySessionId(session);
    }
}