package com.srn.api.repo;
/*
 Author: vikraa
 created: 12/16/18 - 1:04 PM
*/

import com.srn.api.model.entity.SrnCampaignImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ISrnCampaignImageRepo extends JpaRepository<SrnCampaignImage, Long> {
    List<SrnCampaignImage> findAll();
    SrnCampaignImage findByCampaignId(long campaignId);
}