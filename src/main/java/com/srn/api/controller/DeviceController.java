package com.srn.api.controller;

import com.srn.api.model.SrnResponse;
import com.srn.api.service.ISrnDeviceService;
import com.srn.api.utils.FormatterUtils;
import com.srn.api.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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


    @RequestMapping(value = "/v1/device/updatefcm.json", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SrnResponse<String>> updateFcmId(@RequestBody() String deviceParam, @RequestParam("s") String session) {
        if (srnDeviceService.updateFcm(deviceParam)) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}