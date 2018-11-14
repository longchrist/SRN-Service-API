package com.srn.api.service;

import com.srn.api.model.entity.SrnBrand;

import java.util.List;

public interface ISrnBrandService {
    List<SrnBrand> findAllBrand();
    SrnBrand findBrandById(String id);
}