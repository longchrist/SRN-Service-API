package com.srn.api.service;

import com.srn.api.model.entity.SrnProfile;

import java.sql.Timestamp;

public interface ISrnProfile {
    SrnProfile findProfileWithId(long userId);
    boolean isProfileCompleted();
    Timestamp getLastloginTimestamp();
}