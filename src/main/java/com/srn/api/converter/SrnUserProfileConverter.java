/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.srn.api.converter;

import com.srn.api.dto.SrnUserProfileDTO;
import com.srn.api.model.srnUserProfile;
import org.springframework.stereotype.Component;
/**
 *
 * @author user
 */

@Component
public class SrnUserProfileConverter {
     public srnUserProfile DTOtoSrnUserProfile(SrnUserProfileDTO SUPDTO) {
        srnUserProfile SUP = new srnUserProfile();
        SUP.setUser_id(SUPDTO.getUser_id());
        SUP.setFull_name(SUPDTO.getFull_name());
        SUP.setNickname(SUPDTO.getNickname());
        SUP.setAddress(SUPDTO.getAddress());
        SUP.setProvince(SUPDTO.getProvince());
        SUP.setPhone(SUPDTO.getPhone());
        SUP.setAlternate_email(SUPDTO.getAlternate_email());
        SUP.setCreated(SUPDTO.getCreated());
        SUP.setLast_updated(SUPDTO.getLast_updated());
        return SUP;
    }

    public SrnUserProfileDTO SrnUserProfileToDTO(srnUserProfile SUP) {
        SrnUserProfileDTO SUPDTO = new SrnUserProfileDTO();
        SUPDTO.setUser_id(SUP.getUser_id());
        SUPDTO.setFull_name(SUP.getFull_name());
        SUPDTO.setNickname(SUP.getNickname());
        SUPDTO.setAddress(SUP.getAddress());
        SUPDTO.setProvince(SUP.getProvince());
        SUPDTO.setPhone(SUP.getPhone());
        SUPDTO.setAlternate_email(SUP.getAlternate_email());
        SUPDTO.setCreated(SUP.getCreated());
        SUPDTO.setLast_updated(SUP.getLast_updated());
        return SUPDTO;
    }
}
