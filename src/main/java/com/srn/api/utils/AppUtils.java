package com.srn.api.utils;/*
 Author: vikraa
 created: 1/5/19 - 3:02 PM
*/

import com.fasterxml.jackson.databind.ObjectMapper;
import com.srn.api.model.entity.SrnDevice;

import java.io.IOException;

public class AppUtils {

    public static SrnDevice getDeviceFromSession(String encryptedSession) {
        String json = SecurityUtils.getInstance().setData(encryptedSession).setMethod(SecurityUtils.Method.SESSION_DECRYPT).build();
        ObjectMapper mapper = new ObjectMapper();
        SrnDevice device = null;
        try {
            device = mapper.readValue(json, SrnDevice.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return device;
    }

}