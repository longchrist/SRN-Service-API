package com.srn.api.service;

import com.srn.api.model.entity.SrnCampaign;

import java.util.List;

public interface ISrnCampaignService {
    List<SrnCampaign> getAllCampaign();
    SrnCampaign getCampaign(long campaignId);
    boolean isCampaignActive(SrnCampaign campaign);
}