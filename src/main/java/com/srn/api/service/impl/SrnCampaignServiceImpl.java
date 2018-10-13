package com.srn.api.service.impl;

import com.srn.api.model.entity.SrnCampaign;
import com.srn.api.repo.ISrnCampaignRepo;
import com.srn.api.service.ISrnCampaignService;
import com.srn.api.utils.FormatterUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service("srnCampaignService")
public class SrnCampaignServiceImpl implements ISrnCampaignService {

    @Autowired
    ISrnCampaignRepo campaignRepo;

    @Override
    public List<SrnCampaign> getAllCampaign() {
        return campaignRepo.findAll();
    }

    @Override
    public SrnCampaign getCampaign(long campaignId) {
        return campaignRepo.findCampaignById(campaignId);
    }

    @Override
    public boolean isCampaignActive(SrnCampaign campaign) {
        return campaign.getEndDate().after(FormatterUtils.getCurrentTimestamp());
    }
}