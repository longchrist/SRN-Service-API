package com.srn.api.service;

import com.srn.api.model.entity.SrnPoints;

public interface ISrnUserPointsService {
    SrnPoints addUserPoints(String param, String session);
    void deductUserPoints(String param, String session);
    long getTotalUserPoints(String session);
}