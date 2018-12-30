package com.srn.api.service;

import com.srn.api.model.entity.SrnUserDevice;

public interface ISrnUserDeviceService {
    void registerUserDeviceSession(String session, long deviceId);
    void registerUserId(String session, long userId);
    void removeUserDeviceSession(String session);
    SrnUserDevice getUserDeviceData(String session);
}