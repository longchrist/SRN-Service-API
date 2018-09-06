/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.srn.api.converter;

import com.srn.api.dto.SrnUserProfileDTO;
import com.srn.api.model.SrnUserProfile;
import org.springframework.stereotype.Component;

/**
 *
 * @author long
 */

@Component
public class SrnUserProfileConverter {
     public SrnUserProfile dtoToSrnUserProfile(SrnUserProfileDTO supDto) {
        SrnUserProfile sup = new SrnUserProfile();
        sup.setUser_id(supDto.getUser_id());
        sup.setFull_name(supDto.getFull_name());
        sup.setNickname(supDto.getNickname());
        sup.setAddress(supDto.getAddress());
        sup.setCity(supDto.getCity());
        sup.setProvince(supDto.getProvince());
        sup.setPhone(supDto.getPhone());
        sup.setAlternate_email(supDto.getAlternate_email());
        sup.setCreated(supDto.getCreated());
        sup.setLast_updated(supDto.getLast_updated());
        return sup;
    }

    public SrnUserProfileDTO srnUserProfileToDto(SrnUserProfile sup) {
        SrnUserProfileDTO supDto = new SrnUserProfileDTO();
        supDto.setUser_id(sup.getUser_id());
        supDto.setFull_name(sup.getFull_name());
        supDto.setNickname(sup.getNickname());
        supDto.setAddress(sup.getAddress());
        supDto.setCity(sup.getCity());
        supDto.setProvince(sup.getProvince());
        supDto.setPhone(sup.getPhone());
        supDto.setAlternate_email(sup.getAlternate_email());
        supDto.setCreated(sup.getCreated());
        supDto.setLast_updated(sup.getLast_updated());
        return supDto;
    }
}
