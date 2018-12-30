package com.srn.api.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.srn.api.model.dto.SrnProfileDto;
import com.srn.api.model.entity.SrnEmail;
import com.srn.api.model.entity.SrnProfile;
import com.srn.api.model.request.ParamLogin;
import com.srn.api.repo.ISrnUserEmailRepo;
import com.srn.api.service.ISrnUserAuthService;
import com.srn.api.service.ISrnUserDeviceService;
import com.srn.api.service.ISrnUserProfileService;
import com.srn.api.utils.FormatterUtils;
import com.srn.api.utils.SecurityUtils;
import org.apache.http.util.TextUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;

@Transactional
@Service("srnUserAuthService")
public class SrnUserAuthServiceImpl implements ISrnUserAuthService {

    private static final String GOOGLE_CLIENT_ID = "734320557909-q0k91popdikfe65fcao0ng313gfsg4ne.apps.googleusercontent.com";

    @Autowired
    ISrnUserEmailRepo srnUserEmailRepo;

    @Autowired
    ISrnUserDeviceService srnUserDeviceService;

    @Autowired
    ISrnUserProfileService srnUserProfileService;

    private String session;
    private SrnProfileDto profileDto;

    @Override
    public SrnProfileDto userLogin(String requestBody) {
        ParamLogin p = convertLoginBody(requestBody);
        if (p != null) {
            LoginType t = LoginType.values()[p.getLoginType()];
            this.session = p.getSessionId();
            switch (t) {
                case GOOGLE:
                    return userGoogleLogin(p.getToken());
                case FACEBOOK:
                    break;
            }
        }
        return null;
    }

    @Override
    public void userLogout(String session) {
        srnUserDeviceService.removeUserDeviceSession(session);
    }

    @Override
    public SrnProfileDto userUpdateProfile(String requestBody, String session) {
        SrnProfileDto profile = convertProfileBody(requestBody);
        String profileUrl = profile.getUrl();
        SrnEmail email = srnUserEmailRepo.findByEmail(profile.getEmail());
        if(profile != null) {
            SrnProfile entity = srnUserProfileService.findProfileWithId(email.getId());
            if (entity != null) {
                entity.setFullName(profile.getFullName());
                entity.setGender(profile.getGender());
                entity.setNickName(profile.getNickName());
                entity.setPhone(profile.getPhone());
                entity.setAlternateEmail(profile.getAlternateEmail());
                entity.setAddress(profile.getAddress());
                entity.setProvince(profile.getProvince());
                entity.setCity(profile.getCity());
                entity.setLastUpdated(FormatterUtils.getCurrentTimestamp());
                profile = srnUserProfileService.updateUserProfile(entity).toDto();
                profile.setEmail(email.getEmail());
                profile.setUrl(profileUrl);
                if (TextUtils.isEmpty(profile.getFullName()) || TextUtils.isEmpty(profile.getGender())
                        || TextUtils.isEmpty(profile.getProvince()) || TextUtils.isEmpty(profile.getPhone())
                        || TextUtils.isEmpty(profile.getCity())) {
                    profile.setNeedUpdate(true);
                } else {
                    profile.setNeedUpdate(false);
                }
                return profile; //srnUserProfileService.updateUserProfile(entity).toDto();
            }
        }
        return null;
    }

    private SrnProfileDto userGoogleLogin(String token) {
        GoogleIdToken.Payload googlePayload = null;
        try {
            JsonFactory jsonFactory = JacksonFactory.getDefaultInstance();
            HttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
            GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(httpTransport, jsonFactory)
                    .setAudience(Collections.singletonList(GOOGLE_CLIENT_ID)).build();
            GoogleIdToken googleIdToken = verifier.verify(token);
            googlePayload = googleIdToken.getPayload();
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return generateProfile(googlePayload);
    }

    private SrnProfileDto generateProfile(GoogleIdToken.Payload payload) {
        if (payload != null) {
            SrnEmail userEmail = srnUserEmailRepo.findByEmail(payload.getEmail());
            if (userEmail == null) {
                userEmail = new SrnEmail();
                userEmail.setEmail(payload.getEmail());
                userEmail.setLoginType(LoginType.GOOGLE.toString());
                userEmail.setCreated(FormatterUtils.getCurrentTimestamp());
                userEmail.setLastUpdated(FormatterUtils.getCurrentTimestamp());
                userEmail = srnUserEmailRepo.save(userEmail);
            }
            long emailId = userEmail.getId();
            SrnProfile profile = srnUserProfileService.findProfileWithId(emailId);
            if (profile == null) {
                profile = new SrnProfile();
                profile.setUserId(userEmail.getId());
                profile.setAlternateEmail(userEmail.getEmail());
                srnUserProfileService.updateUserProfile(profile);
            }
            srnUserDeviceService.registerUserId(this.session, userEmail.getId());
            profileDto = profile.toDto();
            profileDto.setUrl(payload.get("picture").toString());
            profileDto.setEmail(userEmail.getEmail());
            profileDto.setFullName(payload.get("name").toString());
            profileDto.setGender(profile.getGender());
            profileDto.setNickName(profile.getNickName());
            profileDto.setAddress(profile.getAddress());
            profileDto.setCity(profile.getCity());
            profileDto.setProvince(profile.getProvince());
            profileDto.setPhone(profile.getPhone());
            profileDto.setPoints(0);
            profileDto.setPointLevel("Taster");
            profileDto.setCreated(profile.getCreated());
            profileDto.setLastUpdated(profile.getLastUpdated());
            if (TextUtils.isEmpty(profileDto.getFullName()) || TextUtils.isEmpty(profileDto.getNickName())
                    || TextUtils.isEmpty(profileDto.getGender()) || TextUtils.isEmpty(profileDto.getAddress())
                    || TextUtils.isEmpty(profileDto.getProvince()) || TextUtils.isEmpty(profileDto.getPhone())
                    || TextUtils.isEmpty(profileDto.getCity())) {
                profileDto.setNeedUpdate(true);
            } else {
                profileDto.setNeedUpdate(false);
            }
            return profileDto;
        }
        return null;
    }

    private ParamLogin convertLoginBody(String requestBody) {
        String json = SecurityUtils.getInstance().setData(requestBody).setMethod(SecurityUtils.Method.DATA_DECRYPT).build();
        ObjectMapper jsonMapper = new ObjectMapper();
        try {
            return jsonMapper.readValue(json, ParamLogin.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private SrnProfileDto convertProfileBody(String requestBody) {
        String json = SecurityUtils.getInstance().setData(requestBody).setMethod(SecurityUtils.Method.DATA_DECRYPT).build();
        ObjectMapper jsonMapper = new ObjectMapper();
        try {
            return jsonMapper.readValue(json, SrnProfileDto.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


}