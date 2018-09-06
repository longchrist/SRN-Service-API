/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.srn.api.service;

import com.srn.api.model.SrnUserProfile;
import java.util.List;

/**
 *
 * @author long
 */
public interface SrnUserProfileService {

    SrnUserProfile create(SrnUserProfile user);

    boolean delete(int id);

    boolean deleteAll();

    SrnUserProfile find(int id);

    List<SrnUserProfile> findAll();

    SrnUserProfile findByNickname(String nickname);

    SrnUserProfile update(int id, SrnUserProfile srnUser);
    
}
