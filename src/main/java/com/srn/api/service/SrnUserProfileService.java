/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.srn.api.service;

import com.srn.api.model.srnUserProfile;
import java.util.List;

/**
 *
 * @author long
 */
public interface SrnUserProfileService {

    srnUserProfile create(srnUserProfile user);

    boolean delete(int id);

    boolean deleteAll();

    srnUserProfile find(int id);

    List<srnUserProfile> findAll();

    srnUserProfile findByNickname(String nickname);

    srnUserProfile update(int id, srnUserProfile srnUser);
    
}
