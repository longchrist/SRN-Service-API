package com.srn.api.service;

import com.srn.api.model.entity.SrnDevice;
import com.srn.api.model.response.Session;

public interface ISrnDeviceService {

    Session registerDevice(String param);
    boolean updateFcm(String deviceParam);
    SrnDevice findDeviceById(long id);
    boolean unregisterDevice(SrnDevice device);
}