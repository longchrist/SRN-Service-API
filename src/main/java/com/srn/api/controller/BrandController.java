package com.srn.api.controller;

import com.srn.api.model.SrnResponse;
import com.srn.api.model.dto.SrnStoreDto;
import com.srn.api.model.entity.SrnBrand;
import com.srn.api.service.ISrnBrandService;
import com.srn.api.service.ISrnStoreService;
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

    @Autowired
    ISrnStoreService storeService;

    @RequestMapping(value = "/v1/branding/brand.json", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<SrnResponse<String>> getAllBrands() {
        List<SrnBrand> brands = brandService.findAllBrand();

        SrnResponse<String> response = new SrnResponse<>();
        response.setTimestamp(FormatterUtils.getLongCurrentTimestamp());
        response.setData(SecurityUtils.getInstance()
                    .setData(brands)
                    .setMethod(SecurityUtils.Method.DATA_ENCRYPT)
                    .build());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/v1/branding/brand/{brandId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<SrnResponse<String>> getBrandId(@PathVariable String brandId) {
        SrnBrand brand = brandService.findBrandById(brandId);

        SrnResponse<String> response = new SrnResponse<>();
        response.setTimestamp(FormatterUtils.getLongCurrentTimestamp());
        response.setData(SecurityUtils.getInstance().setData(brand).setMethod(SecurityUtils.Method.DATA_ENCRYPT).build());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/v1/branding/store/{brandId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<SrnResponse<String>> getStoreBrand(@PathVariable String brandId) {
        List<SrnStoreDto> storeBrand = storeService.getStoreBrand(brandId);
        SrnResponse<String> response = new SrnResponse<>();
        response.setTimestamp(FormatterUtils.getLongCurrentTimestamp());
        response.setData(SecurityUtils.getInstance().setData(storeBrand).setMethod(SecurityUtils.Method.DATA_ENCRYPT).build());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/v1/branding/store", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<SrnResponse<String>> getAllStore() {
        List<SrnStoreDto> storeBrand = storeService.getAllStore();
        SrnResponse<String> response = new SrnResponse<>();
        response.setTimestamp(FormatterUtils.getLongCurrentTimestamp());
        response.setData(SecurityUtils.getInstance().setData(storeBrand).setMethod(SecurityUtils.Method.DATA_ENCRYPT).build());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }



}