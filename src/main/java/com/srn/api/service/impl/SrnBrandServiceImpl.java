package com.srn.api.service.impl;

import com.srn.api.model.entity.SrnBrand;
import com.srn.api.repo.ISrnBrandRepo;
import com.srn.api.service.ISrnBrandService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service("srnBrandService")
public class SrnBrandServiceImpl implements ISrnBrandService {

    @Autowired
    ISrnBrandRepo brandRepo;

    private static final Logger LOGGER = LoggerFactory.getLogger(SrnBrandServiceImpl.class);

    @Override
    public List<SrnBrand> findAllBrand() {
        List<SrnBrand> result = new ArrayList<>();
        result = brandRepo.findAll();
        LOGGER.info("[INFO] - "+ getClass().getSimpleName() +" - findAllBrand size : {} ", result.size());
        return result;
    }

    @Override
    public SrnBrand findBrandById(long id) {
        SrnBrand brand = brandRepo.findById(id);
        if (brand != null) {
            LOGGER.info("[INFO] - "+ getClass().getSimpleName() +" - findBrandById - {} Found!", id);
        } else {
            brand = new SrnBrand();
            LOGGER.info("[INFO] - "+ getClass().getSimpleName() +" - findBrandById - {} Not Found !", id);
        }
        return brand;
    }
}