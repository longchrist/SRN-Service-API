package com.srn.api.messaging;/*
 Author: vikraa
 created: 1/2/19 - 8:41 AM
*/

import com.srn.api.model.dto.SrnCampaignRedeemDto;
import com.srn.api.model.entity.*;
import com.srn.api.repo.*;
import com.srn.api.utils.FormatterUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.sql.Timestamp;

@Transactional
@Component
public class RedeemConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(RedeemConsumer.class);
    private static String TAG = RedeemConsumer.class.getSimpleName();

    @Autowired
    ISrnCampaignRepo campaignRepo;

    @Autowired
    ISrnUserDeviceRepo deviceUserRepo;

    @Autowired
    ISrnDeviceRepo deviceRepo;

    @Autowired
    ISrnVoucherCampaignDetailRepo voucherRepo;

    @Autowired
    ISrnCampaignRedeemRepo campaignRedeemRepo;

    private String userFcmId;

    @RabbitListener(queues = "queue.redeem")
    public void processRedeem(SrnCampaignRedeemDto data) {
        LOGGER.info("processing redeem --> "  + data.toString());
        Long campaignId = Long.valueOf(data.getCampaignId());
        SrnCampaign campaign = campaignRepo.findCampaignById(campaignId);
        if (campaign != null) {
            if (!data.getRedeemTimestamp().after(campaign.getEndDate())) {
                if (campaign.getRequiredPoints() == data.getRequiredPoints()) {
                    // need to implement user point checks
                    SrnUserDevice userDevice = deviceUserRepo.findBySessionId(data.getSessionId());
                    if (userDevice != null) {
                        if (userDevice.getUserId() > 0 ) {
                            SrnDevice device = deviceRepo.findById(userDevice.getId());
                            userFcmId = device.getFcmId();
                            if (device != null) {
                                SrnVoucherCampaignDetail voucher = voucherRepo.findAvailableVoucher(campaignId);
                                if (voucher != null) {
                                    userVoucherRedeem(userDevice.getUserId(), data.getRedeemTimestamp(), voucher);
                                } else {
                                    // voucher out of stock
                                    LOGGER.info("voucher campaignid {} is out of stock ! ", data.getCampaignId());
                                }
                            } else {
                                // device not found
                                LOGGER.info("device with imei {} is not found - potential fraud(0) !", data.getDeviceData().getImei() );
                            }
                        } else {
                            // user not login
                            LOGGER.info("device with imei {} is not logged in - potential fraud(1) !", data.getDeviceData().getImei() );
                        }
                    } else {
                        // user device not found
                        LOGGER.info("device with imei {} is not found - potential fraud(2) !", data.getDeviceData().getImei() );
                    }
                } else {
                    // point not match
                    LOGGER.info("campaignId {} request redeem point is {} not match with existing required points {} - potential fraud !", data.getCampaignId(), data.getRequiredPoints(), campaign.getRequiredPoints());
                }
            } else {
                // redeem time is exceed from campaign end date
                LOGGER.info("redeemtime {} is exceed from campaign end date campaign[end_date {}]", data.getRedeemTimestamp().toString(), campaign.getEndDate().toString());
            }
        } else {
            // campaign is not valid or already expired
            LOGGER.info("campaignid {} is not valid or already expired !", data.getCampaignId());
        }
    }


    private void userVoucherRedeem(long userId, Timestamp redeemTimestamp, SrnVoucherCampaignDetail voucher) {
        if (voucher != null) {
            SrnRedeem redeem = new SrnRedeem();
            redeem.setUserId(userId);
            redeem.setVoucherCode(voucher.getVoucherCode());
            redeem.setRedeemDate(redeemTimestamp);
            redeem.setCreated(FormatterUtils.getCurrentTimestamp());
            redeem.setLastUpdated(FormatterUtils.getCurrentTimestamp());
            campaignRedeemRepo.save(redeem);
        }
    }
}