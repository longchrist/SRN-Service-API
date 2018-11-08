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
        LOGGER.info("[INFO] - "+ getClass().getSimpleName() +" - findAllBrand - {}", result);
        return result;
    }

    @Override
    public SrnBrand findBrandById(long id) {
        LOGGER.info("info - findBrandById - brandid {}", id);
        return brandRepo.findById(id);
    }
}