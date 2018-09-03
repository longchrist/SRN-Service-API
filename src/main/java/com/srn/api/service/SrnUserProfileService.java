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
 * @author user
 */
public interface SrnUserProfileService {
    SrnUserProfile find(int id);

    SrnUserProfile findByUsername(String username);

    List<SrnUserProfile> findAll();

    SrnUserProfile create(SrnUserProfile object);

    SrnUserProfile update(int id, SrnUserProfile object);

    boolean delete(int id);

    boolean deleteAll();
}
