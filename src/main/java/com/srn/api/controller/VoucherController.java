package com.srn.api.controller;/*
 Author: vikraa
 created: 1/5/19 - 2:55 PM
*/

import com.srn.api.model.SrnResponse;
import com.srn.api.model.dto.SrnVoucherDto;
import com.srn.api.service.ISrnVoucherService;
import com.srn.api.utils.FormatterUtils;
import com.srn.api.utils.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class VoucherController {

    private static final Logger LOGGER = LoggerFactory.getLogger(VoucherController.class);

    @Autowired
    ISrnVoucherService voucherService;

    @RequestMapping(value = "/v1/voucher/voucher.json", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<SrnResponse<String>> userVoucher(@RequestParam("s") String session) {
        List<SrnVoucherDto> userVouchers = voucherService.findUserVoucher(session);
        SrnResponse<String> response = new SrnResponse<>();
        response.setTimestamp(FormatterUtils.getLongCurrentTimestamp());
        response.setData(SecurityUtils.getInstance().setData(userVouchers).setMethod(SecurityUtils.Method.DATA_ENCRYPT).build());
        LOGGER.info("campaign|response: {}", response.getData());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}