/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.srn.api.service.impl;

import com.srn.api.model.SrnUserProfile;
import com.srn.api.repository.SrnUserProfileRepository;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

/**
 *
 * @author user
 */
public class SrnUserProfileImpl {
    private final Logger LOGGER = LoggerFactory.getLogger(SrnUserProfileImpl.class);
    private final SrnUserProfileRepository userProfileRepository;

    @Autowired
    public SrnUserProfileImpl(SrnUserProfileRepository userProfileRepository) {
        this.userProfileRepository = userProfileRepository;
    }

    public SrnUserProfile create(SrnUserProfile user) {
        return userProfileRepository.save(user);
    }

    public SrnUserProfile find(int id) {
        return userProfileRepository.findOne(id);
    }

    public SrnUserProfile findByUsername(String username) {
        return userProfileRepository.findByUsername(username);
    }

    public List<SrnUserProfile> findAll() {
        return userProfileRepository.findAll();
    }

    public SrnUserProfile update(int id, SrnUserProfile srnUser) {
        srnUser.setUser_id(id);
        return userProfileRepository.save(srnUser);
    }

    public boolean delete(int id) {
        try {
            userProfileRepository.delete(id);
            return true;
        } catch (DataAccessException e) {
            LOGGER.info(e.getMessage());
            return false;
        }
    }

    public boolean deleteAll() {
        try {
            userProfileRepository.deleteAll();
            return true;
        } catch (DataAccessException e) {
            LOGGER.info(e.getMessage());
            return false;
        }
    }
}
