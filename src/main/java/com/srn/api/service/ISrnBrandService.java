package com.srn.api.service;

import com.srn.api.model.entity.SrnBrand;

import java.util.List;

public interface ISrnBrandService {
    List<SrnBrand> findAll();
    SrnBrand findBrandById(String id);
}