package com.srn.api.controller;

import com.srn.api.model.SrnResponse;
import com.srn.api.service.ISrnUserService;
import com.srn.api.utils.FormatterUtils;
import com.srn.api.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    ISrnUserService srnUserService;

    @RequestMapping(value = "/v1/user/logingoogle.json", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<SrnResponse<String>> loginGoogle(@RequestBody() String param) {
        SrnResponse<String> response = new SrnResponse<>();
        response.setTimestamp(FormatterUtils.getLongCurrentTimestamp());
        response.setData(SecurityUtils.getInstance().setData(srnUserService.userLogin(param)).setMethod(SecurityUtils.Method.DATA_ENCRYPT).build());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/v1/user/logout.json", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<SrnResponse<Void>> logout(@RequestParam("s") String session) {
        srnUserService.userLogout(session);
        SrnResponse<Void> response = new SrnResponse<>();
        response.setData(null);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @RequestMapping(value = "/v1/user/profile.json", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<SrnResponse<String>> userProfile(@RequestBody() String param) {
        SrnResponse<String> response = new SrnResponse<>();
        response.setTimestamp(FormatterUtils.getLongCurrentTimestamp());
        response.setData(SecurityUtils.getInstance().setData(srnUserService.userUpdateProfile(param)).setMethod(SecurityUtils.Method.DATA_ENCRYPT).build());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/v1/user/profile.json", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<SrnResponse<String>> userProfileRead(@RequestBody() String param) {
        SrnResponse<String> response = new SrnResponse<>();
        response.setTimestamp(FormatterUtils.getLongCurrentTimestamp());
        response.setData(SecurityUtils.getInstance().setData(srnUserService.userUpdateProfile(param)).setMethod(SecurityUtils.Method.DATA_ENCRYPT).build());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/v1/user/points.json", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<SrnResponse<String>> userPoints(@RequestBody() String param) {
        //TODO:implement point create and update here

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/v1/user/points.json", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<SrnResponse<String>> userPointsRead(@RequestParam("s") String session) {

        return new ResponseEntity<>(HttpStatus.OK);
    }


}