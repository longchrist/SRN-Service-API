package com.srn.api.controller;

import com.srn.api.model.SrnResponse;
import com.srn.api.model.entity.SrnBrand;
import com.srn.api.service.ISrnBrandService;
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
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class BrandController {

    @Autowired
    ISrnBrandService brandService;

    @RequestMapping(value = "/v1/branding/brand.json", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<SrnResponse<String>> getAllBrands(@RequestParam("s") String session) {
        List<SrnBrand> brands = brandService.findAllBrand();

        SrnResponse<String> response = new SrnResponse<>();
        response.setTimestamp(FormatterUtils.getLongCurrentTimestamp());
        response.setData(SecurityUtils.getInstance().setData(brands).setMethod(SecurityUtils.Method.DATA_ENCRYPT).build());

        System.out.println("response - provision data encrypted --> " + response.getData());
        System.out.println("response - provision data decrypted --> " + SecurityUtils.getInstance().setData(response.getData()).setMethod(SecurityUtils.Method.DATA_DECRYPT).build());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/v1/branding/brand/{brandId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<SrnResponse<String>> getBrandId(@RequestParam("s") String session, @PathVariable long brandId) {
        SrnBrand brand = brandService.findBrandById(brandId);

        SrnResponse<String> response = new SrnResponse<>();
        response.setTimestamp(FormatterUtils.getLongCurrentTimestamp());
        response.setData(SecurityUtils.getInstance().setData(brand).setMethod(SecurityUtils.Method.DATA_ENCRYPT).build());

        System.out.println("response - provision data encrypted --> " + response.getData());
        System.out.println("response - provision data decrypted --> " + SecurityUtils.getInstance().setData(response.getData()).setMethod(SecurityUtils.Method.DATA_DECRYPT).build());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}