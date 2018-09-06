/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.srn.api.service.impl;

import com.srn.api.service.SrnUserProfileService;
import com.srn.api.model.SrnUserProfile;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import com.srn.api.service.SrnUserProfileService;
import com.srn.api.repository.SrnUserProfileRepository;

/**
 *
 * @author long
 */

@Service
public class SrnUserProfileImpl implements SrnUserProfileService {
    private final Logger LOGGER = LoggerFactory.getLogger(SrnUserProfileImpl.class);
    private final SrnUserProfileRepository userProfileRepository;

    @Autowired
    public SrnUserProfileImpl(SrnUserProfileRepository userProfileRepository) {
        this.userProfileRepository = userProfileRepository;
    }

    @Override
    public SrnUserProfile create(SrnUserProfile user) {
        return userProfileRepository.save(user);
    }

    @Override
    public SrnUserProfile find(int id) {
        return userProfileRepository.findOne(id);
    }

    @Override
    public SrnUserProfile findByNickname(String nickname) {
        return userProfileRepository.findByNickname(nickname);
    }

    @Override
    public List<SrnUserProfile> findAll() {
        return userProfileRepository.findAll();
    }

    @Override
    public SrnUserProfile update(int id, SrnUserProfile srnUser) {
        srnUser.setUser_id(id);
        return userProfileRepository.save(srnUser);
    }

    @Override
    public boolean delete(int id) {
        try {
            userProfileRepository.delete(id);
            return true;
        } catch (DataAccessException e) {
            LOGGER.info(e.getMessage());
            return false;
        }
    }

    @Override
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
