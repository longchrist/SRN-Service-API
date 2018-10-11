package com.srn.api.service.impl;

import com.srn.api.model.entity.SrnUserDevice;
import com.srn.api.repo.ISrnUserDeviceRepo;
import com.srn.api.service.ISrnUserDevice;
import com.srn.api.utils.FormatterUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("srnUserDeviceService")
public class SrnUserDeviceServiceImpl implements ISrnUserDevice {

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
}