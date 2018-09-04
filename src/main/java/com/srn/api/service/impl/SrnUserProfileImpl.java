/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.srn.api.service.impl;

import com.srn.api.service.SrnUserProfileService;
import com.srn.api.model.srnUserProfile;
import com.srn.api.repository.srnUserProfileRepository;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import com.srn.api.service.SrnUserProfileService;

/**
 *
 * @author user
 */
@Service
public class SrnUserProfileImpl implements SrnUserProfileService {
    private final Logger LOGGER = LoggerFactory.getLogger(SrnUserProfileImpl.class);
    private final srnUserProfileRepository userProfileRepository;

    @Autowired
    public SrnUserProfileImpl(srnUserProfileRepository userProfileRepository) {
        this.userProfileRepository = userProfileRepository;
    }

    @Override
    public srnUserProfile create(srnUserProfile user) {
        return userProfileRepository.save(user);
    }

    @Override
    public srnUserProfile find(int id) {
        return userProfileRepository.findOne(id);
    }

    @Override
    public srnUserProfile findByNickname(String nickname) {
        return userProfileRepository.findByNickname(nickname);
    }

    @Override
    public List<srnUserProfile> findAll() {
        return userProfileRepository.findAll();
    }

    @Override
    public srnUserProfile update(int id, srnUserProfile srnUser) {
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
