/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.srn.api.controller;

import com.srn.api.converter.SrnUserProfileConverter;
import com.srn.api.dto.SrnUserProfileDTO;
import com.srn.api.model.SrnUserProfile;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.srn.api.service.SrnUserProfileService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author long
 */

@RestController
@RequestMapping("/apiuserprofile")
public class SrnUserProfileController {
    private final Logger LOGGER = LoggerFactory.getLogger(SrnUserProfileController.class);
    private final SrnUserProfileService userProfileService;
    private final SrnUserProfileConverter userProfileConverter;

    @Autowired
    public SrnUserProfileController(SrnUserProfileService userProfileService, SrnUserProfileConverter userProfileConverter) {
        this.userProfileService = userProfileService;
        this.userProfileConverter = userProfileConverter;
    }

    @RequestMapping
    public ResponseEntity<List<SrnUserProfile>> loadAll() {
        LOGGER.info("start load users profile");
        try {
            List<SrnUserProfile> lsup = userProfileService.findAll();
            LOGGER.info("Found {} users", lsup.size());
            return new ResponseEntity<>(lsup, HttpStatus.OK);
        } catch (DataAccessException e) {
            LOGGER.info(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    
    @RequestMapping("/{id}")
    public ResponseEntity<SrnUserProfileDTO> loadOne(@PathVariable int id) {
        LOGGER.info("start load one user profiling by id: ", id);
        try {
            SrnUserProfile user = userProfileService.find(id);
            LOGGER.info("Found: {}", user);
            return new ResponseEntity<>(userProfileConverter.srnUserProfileToDto(user), HttpStatus.OK);
        } catch (DataAccessException e) {
            LOGGER.info(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<SrnUserProfileDTO> create(@RequestBody SrnUserProfileDTO supDto) {
        LOGGER.info("start creating user profiling : ", supDto);
        try {
            SrnUserProfile user = userProfileService.create(userProfileConverter.dtoToSrnUserProfile(supDto));
            return new ResponseEntity<>(userProfileConverter.srnUserProfileToDto(user), HttpStatus.CREATED);
        } catch (DataAccessException e) {
            LOGGER.info(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<SrnUserProfileDTO> update(@PathVariable int id, @RequestBody SrnUserProfileDTO supDto) {
        LOGGER.info("start update user profiling : ", supDto);
        try {
            SrnUserProfile sup = userProfileService.update(id, userProfileConverter.dtoToSrnUserProfile(supDto));
            return new ResponseEntity<>(userProfileConverter.srnUserProfileToDto(sup), HttpStatus.OK);
        } catch (DataAccessException e) {
            LOGGER.info(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity delete(@PathVariable int id) {
        if (userProfileService.delete(id))
            return new ResponseEntity(HttpStatus.OK);
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
}
