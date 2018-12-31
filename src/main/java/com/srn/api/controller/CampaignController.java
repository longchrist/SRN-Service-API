package com.srn.api.controller;

import com.srn.api.model.SrnResponse;
import com.srn.api.model.dto.SrnCampaignDto;
import com.srn.api.service.ISrnCampaignService;
import com.srn.api.utils.FormatterUtils;
import com.srn.api.utils.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CampaignController {

    @Autowired
    ISrnCampaignService campaignService;

    private static final Logger LOGGER = LoggerFactory.getLogger(CampaignController.class);

    @RequestMapping(value = "/v1/campaign/brand/{brandId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<SrnResponse<String>> getCampaignBrand(@PathVariable String brandId) {
        List<SrnCampaignDto> campaignDtos = campaignService.getCampaignBrand(brandId);
        LOGGER.info("campaign|totalCampaign {}", campaignDtos.size());
        SrnResponse<String> response = new SrnResponse<>();
        response.setTimestamp(FormatterUtils.getLongCurrentTimestamp());
        response.setData(SecurityUtils.getInstance().setData(campaignDtos).setMethod(SecurityUtils.Method.DATA_ENCRYPT).build());
        LOGGER.info("campaign|response: {}", response.getData());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/v1/campaign/promo", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<SrnResponse<String>> getCampaignPromo(@RequestParam("s") String session) {
        List<SrnCampaignDto> campaignDtos = campaignService.getAllCampaign(session);
        LOGGER.info("campaign|totalCampaign {}", campaignDtos.size());
        SrnResponse<String> response = new SrnResponse<>();
        response.setTimestamp(FormatterUtils.getLongCurrentTimestamp());
        response.setData(SecurityUtils.getInstance().setData(campaignDtos).setMethod(SecurityUtils.Method.DATA_ENCRYPT).build());
        LOGGER.info("campaign|response: {}", response.getData());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/v1/campaign/redeem", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<SrnResponse<String>> redeem(@RequestBody String redeem, @RequestParam("s") String session) {
        campaignService.redeem(redeem);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

}