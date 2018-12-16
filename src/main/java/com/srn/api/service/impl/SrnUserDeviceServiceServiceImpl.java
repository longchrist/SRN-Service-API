package com.srn.api.service.impl;

import com.srn.api.model.entity.SrnUserDevice;
import com.srn.api.repo.ISrnUserDeviceRepo;
import com.srn.api.service.ISrnUserDeviceService;
import com.srn.api.utils.FormatterUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service("srnUserDeviceService")
public class SrnUserDeviceServiceServiceImpl implements ISrnUserDeviceService {

    @Autowired
    ISrnUserDeviceRepo srnUserDeviceRepo;

    private static final Logger LOGGER = LoggerFactory.getLogger(SrnUserDeviceServiceServiceImpl.class);

    @Override
    public void registerUserDeviceSession(String session, long deviceId) {
        LOGGER.info("bindDeviceSession| session {} with deviceid {}", session, deviceId);
        SrnUserDevice userDevice = new SrnUserDevice();
        userDevice.setId(deviceId);
        userDevice.setSessionId(session);
        userDevice.setUserId(0L);
        userDevice.setCreated(FormatterUtils.getCurrentTimestamp());
        userDevice.setLastUpdated(FormatterUtils.getCurrentTimestamp());
        srnUserDeviceRepo.save(userDevice);
        LOGGER.info("bindDeviceSession|completed");
    }

    @Override
    public void registerUserId(String session, long userId) {
        LOGGER.info("registerUserId| session {} with userId {}", session, userId);
        SrnUserDevice userDevice = srnUserDeviceRepo.findBySessionId(session);
        if (userDevice != null) {
            userDevice.setUserId(userId);
            srnUserDeviceRepo.save(userDevice);
            LOGGER.info("registerUserId|completed", session, userId);
        }
    }

    @Override
    public void removeUserDeviceSession(String session) {
        LOGGER.info("removeUserDeviceSession|session {}", session);
        SrnUserDevice userDevice = srnUserDeviceRepo.findBySessionId(session);
        if (userDevice != null) {
            srnUserDeviceRepo.delete(userDevice);
            LOGGER.info("removeUserDeviceSession|completed", session);
        }
    }

    @Override
    public SrnUserDevice getUserDeviceData(String session) {
        return srnUserDeviceRepo.findBySessionId(session);
    }
}