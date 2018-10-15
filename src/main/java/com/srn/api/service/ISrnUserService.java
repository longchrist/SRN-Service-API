package com.srn.api.service;

import com.srn.api.model.dto.SrnProfileDto;

public interface ISrnUserService {

    public static enum LoginType {
        UNKNOWN(0), GOOGLE(1), FACEBOOK(2);
        private int value;
        LoginType(int type) {
            this.value = value;
        }

        int getValue() {
            return this.value;
        }
    }

    SrnProfileDto userLogin(String requestBody);
    void userLogout(String session);
    SrnProfileDto userUpdateProfile(String requestBody);
}