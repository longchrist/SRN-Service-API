package com.srn.api.service;

import com.srn.api.model.entity.SrnDevice;

public interface ISrnUserDevice {
    void registerUserDeviceSession(String session, long deviceId);
    void registerUserId(String session, long userId);
    void removeUserDeviceSession(String session);
}