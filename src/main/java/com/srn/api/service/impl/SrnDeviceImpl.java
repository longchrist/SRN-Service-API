/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.srn.api.service.impl;

import com.srn.api.model.SrnDevice;
import com.srn.api.repository.SrnDeviceRepository;
import com.srn.api.repository.SrnUserProfileRepository;
import com.srn.api.service.SrnDeviceService;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

/**
 *
 * @author user
 */
@Service
public class SrnDeviceImpl implements SrnDeviceService{
    private final Logger LOGGER = LoggerFactory.getLogger(SrnDeviceImpl.class);
    private final SrnDeviceRepository deviceRepository;
    


    @Autowired
    public SrnDeviceImpl(SrnDeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }

    @Override
    public SrnDevice create(SrnDevice srnDevice) {
        return deviceRepository.save(srnDevice);
    }

    @Override
    public SrnDevice find(int id) {
        return deviceRepository.findOne(id);
    }

    @Override
    public SrnDevice findByImei(String imei) {
        return deviceRepository.findByImei(imei);
    }

    @Override
    public List<SrnDevice> findAll() {
        return deviceRepository.findAll();
    }

    @Override
    public SrnDevice update(String imei, SrnDevice srnDevice) {
        srnDevice.setImei(imei);
        return deviceRepository.save(srnDevice);
    }

    @Override
    public boolean delete(int id) {
        try {
            deviceRepository.delete(id);
            return true;
        } catch (DataAccessException e) {
            LOGGER.info(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean deleteAll() {
        try {
            deviceRepository.deleteAll();
            return true;
        } catch (DataAccessException e) {
            LOGGER.info(e.getMessage());
            return false;
        }
    }

    
}
