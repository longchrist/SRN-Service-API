package com.srn.api.repo;/*
 Author: vikraa
 created: 1/2/19 - 10:46 PM
*/

import com.srn.api.model.entity.SrnRedeem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ISrnCampaignRedeemRepo extends JpaRepository<SrnRedeem, Long> {

}