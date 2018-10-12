package com.srn.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.client.util.Value;
import com.srn.api.model.SrnResponse;
import com.srn.api.model.dto.SrnProfileDto;
import com.srn.api.model.request.ParamLogin;
import com.srn.api.service.ISrnUserService;
import com.srn.api.utils.FormatterUtils;
import com.srn.api.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
public class UserController {

    @Value("${google.login.url}")
    private String googleLogin;

    @Autowired
    ISrnUserService srnUserService;

    @RequestMapping(value = "/v1/user/logingoogle.json", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<SrnResponse<String>> loginGoogle(@RequestBody() String param, @RequestParam("s") String session) {
        System.out.println("received - login data encrypted --> " + param);
        String json = SecurityUtils.getInstance().setData(param).setMethod(SecurityUtils.Method.DATA_DECRYPT).build();
        System.out.println("received - login data encrypted --> " + json);
        ObjectMapper jsonMapper = new ObjectMapper();
        ParamLogin paramLogin = null;
        try {
           paramLogin = jsonMapper.readValue(json, ParamLogin.class);
           if (!SecurityUtils.getInstance().isSessionValid(paramLogin.getSessionId())) {
               return new ResponseEntity<>(HttpStatus.FORBIDDEN);
           }
        } catch (IOException e) {
            e.printStackTrace();
        }
        SrnProfileDto srnProfile = srnUserService.userLogin(paramLogin.getToken(), session, ISrnUserService.LoginType.GOOGLE);

        if (srnProfile == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        SrnResponse<String> response = new SrnResponse<>();
        response.setTimestamp(FormatterUtils.getLongCurrentTimestamp());
        response.setData(SecurityUtils.getInstance().setData(srnProfile).setMethod(SecurityUtils.Method.DATA_ENCRYPT).build());
        System.out.println("response - login data encrypted --> " + response.getData());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/v1/user/logout.json", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<SrnResponse<String>> logout(@RequestBody() String param) {
        //TODO:implement logout here

        return new ResponseEntity<>(HttpStatus.OK);
    }



    @RequestMapping(value = "/v1/user/profile.json", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<SrnResponse<String>> userProfile(@RequestBody() String param) {
        //TODO:implement profile create and update here

        return new ResponseEntity<>(HttpStatus.OK);
    }


    @RequestMapping(value = "/v1/user/profile.json", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<SrnResponse<String>> userProfileRead(@RequestParam("s") String session) {
        //TODO:implement profile get here

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/v1/user/points.json", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<SrnResponse<String>> userPoints(@RequestBody() String param) {
        //TODO:implement point create and update here

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/v1/user/points.json", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<SrnResponse<String>> userPointsRead(@RequestParam("s") String session) {
        //TODO:implement point get here

        return new ResponseEntity<>(HttpStatus.OK);
    }


}