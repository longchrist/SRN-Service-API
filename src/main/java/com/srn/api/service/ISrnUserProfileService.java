package com.srn.api.service;

import com.srn.api.model.entity.SrnProfile;

import java.sql.Timestamp;

public interface ISrnUserProfileService {
    SrnProfile findProfileWithId(long userId);
    boolean isProfileCompleted();
    Timestamp getLastloginTimestamp();
    SrnProfile updateUserProfile(SrnProfile profile);
}