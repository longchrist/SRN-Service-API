package com.srn.api.service;

import com.srn.api.model.dto.SrnStoreDto;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ISrnStoreService {
    List<SrnStoreDto> getStoreBrand(String brandId);
    List<SrnStoreDto> getAllStore();
}