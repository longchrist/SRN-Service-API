package com.srn.api.controller;

import com.srn.api.model.SrnResponse;
import com.srn.api.model.dto.SrnCampaignDto;
import com.srn.api.model.entity.SrnBrand;
import com.srn.api.service.ISrnCampaignService;
import com.srn.api.utils.FormatterUtils;
import com.srn.api.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class CampaignController {

    @Autowired
    ISrnCampaignService campaignService;

    @RequestMapping(value = "/v1/campaign/brand/{brandId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<SrnResponse<String>> getCampaignBrand(@PathVariable String brandId) {
        List<SrnCampaignDto> campaignDtos = campaignService.getCampaignBrand(brandId);

        SrnResponse<String> response = new SrnResponse<>();
        response.setTimestamp(FormatterUtils.getLongCurrentTimestamp());
        response.setData(SecurityUtils.getInstance().setData(campaignDtos).setMethod(SecurityUtils.Method.DATA_ENCRYPT).build());

        return new ResponseEntity<>(response, HttpStatus.OK);

    }
}