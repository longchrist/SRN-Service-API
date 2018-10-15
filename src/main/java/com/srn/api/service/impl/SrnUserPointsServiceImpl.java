package com.srn.api.service.impl;

import com.srn.api.model.entity.SrnPoints;
import com.srn.api.service.ISrnUserPointsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service("srnUserPointsService")
public class SrnUserPointsServiceImpl implements ISrnUserPointsService {

    @Override
    public SrnPoints addUserPoints(String param, String session) {
        return null;
    }

    @Override
    public long getTotalUserPoints(String session) {
        return 0;
    }

    @Override
    public void deductUserPoints(String param, String session) {

    }
}