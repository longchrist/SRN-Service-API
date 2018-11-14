package com.srn.api.service.impl;

import com.srn.api.model.dto.SrnStoreDto;
import com.srn.api.model.entity.SrnStore;
import com.srn.api.repo.ISrnStoreRepo;
import com.srn.api.service.ISrnStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service("srnStoreService")
public class SrnStoreServiceImpl implements ISrnStoreService {

    @Autowired
    ISrnStoreRepo storeRepo;

    @Override
    public List<SrnStoreDto> getStoreBrand(String brandId) {
        List<SrnStore> brandStore = storeRepo.findByBrandId(brandId);
        List<SrnStoreDto> result = brandStore
                .stream().map(s -> new SrnStoreDto(s.getId(), s.getBrandId(), s.getStoreName(), s.getStoreAddress(),
                        s.getStoreCity(), s.getStorePhone(), s.getStoreSeating(), s.getStoreOperationalHours(),
                        s.getStoreProvince(), s.getStoreLatitude(), s.getStoreLongitude())).collect(Collectors.toList());
        return result;
    }

    @Override
    public List<SrnStoreDto> getAllStore() {
        List<SrnStore> brandStore = storeRepo.findAll();
        List<SrnStoreDto> result = brandStore
                .stream().map(s -> new SrnStoreDto(s.getId(), s.getBrandId(), s.getStoreName(), s.getStoreAddress(),
                        s.getStoreCity(), s.getStorePhone(), s.getStoreSeating(), s.getStoreOperationalHours(),
                        s.getStoreProvince(), s.getStoreLatitude(), s.getStoreLongitude())).collect(Collectors.toList());
        return result;
    }
}