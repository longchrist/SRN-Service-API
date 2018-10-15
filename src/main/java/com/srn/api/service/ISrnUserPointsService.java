package com.srn.api.service;

import com.srn.api.model.dto.SrnPointDto;

public interface ISrnUserPointsService {
    SrnPointDto addUserPoints(String param, String session);
    void deductUserPoints(String param, String session);
    long getTotalUserPoints(String session);
}