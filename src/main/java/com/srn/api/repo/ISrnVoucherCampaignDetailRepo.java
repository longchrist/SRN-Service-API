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

    @Query(value = "select '\"'||array_to_json(array_agg(voucheruser))||'\"' from (select scr.voucher_code as \"voucherCode\", extract(epoch from svcd.voucher_expired)*100000 as \"voucherExpired\", svcd.voucher_campaign_id as \"voucherCampaignId\", (select array_to_json(array_agg(r)) from (select scd.store_id as \"id\", ss.store_name as \"name\" from srn_campaign_detail scd join srn_store ss on ss.store_id = scd.store_id where scd.voucher_campaign_id = svcd.voucher_campaign_id) r) as \"store\" from srn_campaign_redeem scr join srn_voucher_campaign_detail svcd on svcd.voucher_code = scr.voucher_code where scr.user_id = :userId and svcd.voucher_expired > current_timestamp) voucheruser"
     , nativeQuery = true)
    String findVoucherUser(@Param("userId") long userId);
}