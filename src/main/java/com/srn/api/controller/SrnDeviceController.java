/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.srn.api.controller;

import com.srn.api.converter.SrnDeviceConverter;
import com.srn.api.dto.SrnDeviceDTO;
import com.srn.api.model.SrnDevice;
import com.srn.api.service.SrnDeviceService;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author user
 */
@RestController
@RequestMapping("/srndevice")
public class SrnDeviceController {
    private final Logger LOGGER = LoggerFactory.getLogger(SrnDeviceController.class);
    private final SrnDeviceService deviceService;
    private final SrnDeviceConverter deviceConverter;

    @Autowired
    public SrnDeviceController(SrnDeviceService sdService, SrnDeviceConverter sdConverter) {
        this.deviceService = sdService;
        this.deviceConverter = sdConverter;
    }

    @RequestMapping
    public ResponseEntity<List<SrnDevice>> loadAll() {
        LOGGER.info("start load users device");
        try {
            List<SrnDevice> lsd = deviceService.findAll();
            LOGGER.info("Found {} devices", lsd.size());
            return new ResponseEntity<>(lsd, HttpStatus.OK);
        } catch (DataAccessException e) {
            LOGGER.info(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    
    @RequestMapping("/{id}")
    public ResponseEntity<SrnDeviceDTO> loadOne(@PathVariable int id) {
        LOGGER.info("start load one device by id: ", id);
        try {
            SrnDevice sd = deviceService.find(id);
            LOGGER.info("Found: {}", sd);
            return new ResponseEntity<>(deviceConverter.SrnDeviceToDTO(sd), HttpStatus.OK);
        } catch (DataAccessException e) {
            LOGGER.info(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<SrnDeviceDTO> create(@RequestBody SrnDeviceDTO sdDto) {
        LOGGER.info("start creating device : ", sdDto);
        try {
            SrnDevice sd = deviceService.create(deviceConverter.DTOtoSrnDevice(sdDto));
            return new ResponseEntity<>(deviceConverter.SrnDeviceToDTO(sd), HttpStatus.CREATED);
        } catch (DataAccessException e) {
            LOGGER.info(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<SrnDeviceDTO> update(@PathVariable String imei, @RequestBody SrnDeviceDTO sdDto) {
        LOGGER.info("start update device informations : ", sdDto);
        try {
            SrnDevice sd = deviceService.update(imei, deviceConverter.DTOtoSrnDevice(sdDto));
            return new ResponseEntity<>(deviceConverter.SrnDeviceToDTO(sd), HttpStatus.OK);
        } catch (DataAccessException e) {
            LOGGER.info(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity delete(@PathVariable int id) {
        if (deviceService.delete(id))
            return new ResponseEntity(HttpStatus.OK);
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
    
}
