package com.srn.api.repo;/*
 Author: vikraa
 created: 1/2/19 - 10:21 PM
*/

import com.srn.api.model.entity.SrnVoucherCampaignDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ISrnVoucherCampaignDetailRepo extends JpaRepository<SrnVoucherCampaignDetail, Long> {
    @Query(value = "select svcd.* from srn_voucher_campaign_detail svcd left join srn_campaign_redeem scr on svcd.voucher_code = scr.voucher_code where svcd.voucher_campaign_id = :campaignId and scr is null limit 1"
            , nativeQuery = true)
    SrnVoucherCampaignDetail findAvailableVoucher(@Param("campaignId") long campaignId);
}