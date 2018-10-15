package com.srn.api.service.impl;

import com.srn.api.model.entity.SrnProfile;
import com.srn.api.repo.ISrnUserProfileRepo;
import com.srn.api.service.ISrnUserProfileService;
import org.apache.http.util.TextUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;

@Transactional
@Service("srnUserProfileService")
public class SrnUserProfileServiceImpl implements ISrnUserProfileService {

    @Autowired
    ISrnUserProfileRepo srnProfileRepo;

    private boolean isCompleted;
    private Timestamp lastLoginTimestamp;
    @Override
    public SrnProfile findProfileWithId(long userId) {
        SrnProfile profile = srnProfileRepo.findProfileByUserId(userId);
        if (TextUtils.isEmpty(profile.getFullName()) || TextUtils.isEmpty(profile.getNickName())
                || TextUtils.isEmpty(profile.getAddress()) || TextUtils.isEmpty(profile.getCity()) ||
                TextUtils.isEmpty(profile.getProvince()) || TextUtils.isEmpty(profile.getPhone()) ||
                TextUtils.isEmpty(profile.getAlternateEmail())) {
            isCompleted = false;
        } else {
            isCompleted = true;
        }
        lastLoginTimestamp = profile.getLastUpdated();
        return profile;
    }

    @Override
    public boolean isProfileCompleted() {
        return isCompleted;
    }

    @Override
    public Timestamp getLastloginTimestamp() {
        return lastLoginTimestamp;
    }

    @Override
    public SrnProfile updateUserProfile(SrnProfile profile) {
        return srnProfileRepo.save(profile);
    }
}