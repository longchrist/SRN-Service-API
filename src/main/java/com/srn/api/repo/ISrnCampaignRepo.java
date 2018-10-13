package com.srn.api.repo;

import com.srn.api.model.entity.SrnCampaign;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ISrnCampaignRepo extends JpaRepository<SrnCampaign, Long> {
    List<SrnCampaign> findAll();
    SrnCampaign findCampaignById(long campaignId);
}