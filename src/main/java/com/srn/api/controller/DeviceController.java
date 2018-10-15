package com.srn.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.srn.api.model.SrnResponse;
import com.srn.api.model.entity.SrnDevice;
import com.srn.api.model.response.Session;
import com.srn.api.service.ISrnDeviceService;
import com.srn.api.utils.FormatterUtils;
import com.srn.api.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class DeviceController {

    @Autowired
    private ISrnDeviceService srnDeviceService;

    @RequestMapping(value = "/v1/device/provision.json", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SrnResponse<String>> provisioning(@RequestBody() String param) {
        SrnResponse<String> response = new SrnResponse<>();
        response.setTimestamp(FormatterUtils.getLongCurrentTimestamp());
        response.setData(SecurityUtils.getInstance().setData(srnDeviceService.registerDevice(param).toString()).setMethod(SecurityUtils.Method.DATA_ENCRYPT).build());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}