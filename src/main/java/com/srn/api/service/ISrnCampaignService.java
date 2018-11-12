package com.srn.api.service;

import com.srn.api.model.dto.SrnCampaignDto;
import com.srn.api.model.entity.SrnCampaign;

import java.util.List;

public interface ISrnCampaignService {
    List<SrnCampaign> getAllCampaign();
    List<SrnCampaignDto> getCampaignBrand(String brandId);
    SrnCampaign getCampaign(long campaignId);
    boolean isCampaignActive(SrnCampaign campaign);

}