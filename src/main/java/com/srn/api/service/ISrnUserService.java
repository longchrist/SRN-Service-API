package com.srn.api.service;

import com.srn.api.model.dto.SrnProfileDto;
import com.srn.api.model.entity.SrnProfile;
import com.srn.api.service.impl.SrnUserServiceImpl;

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

    SrnProfileDto userLogin(String token, LoginType type);
    void userLogout(String type);
}