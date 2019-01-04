package com.srn.api.messaging;/*
 Author: vikraa
 created: 1/2/19 - 8:41 AM
*/

import com.google.api.client.util.ArrayMap;
import com.srn.api.model.dto.SrnCampaignRedeemDto;
import com.srn.api.model.entity.*;
import com.srn.api.repo.*;
import com.srn.api.utils.FormatterUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.Map;

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

    @Autowired
    NotificationPublish notifPublish;

    /* message */
    @Value("${message.redeem.success}")
    String mMessageRedeemSuccess;

    @Value("${message.redeem.failed.outofstock}")
    String mMessageRedeemFailedOutofstock;

    @Value("${message.redeem.failed.default}")
    String mMessageRedeemFailedDefault;

    @Value("${message.redeem.failed.expired}")
    String mMessageRedeemFailedExpired;

    private String userFcmId;

    @RabbitListener(queues = "queue.redeem")
    public void processRedeem(SrnCampaignRedeemDto data) {
        LOGGER.info("processing redeem --> "  + data.toString());
        userFcmId = data.getDeviceData().getFcmId();
        Long campaignId = Long.valueOf(data.getCampaignId());
        SrnCampaign campaign = campaignRepo.findCampaignById(campaignId);
        if (campaign != null) {
            if (!data.getRedeemTimestamp().after(campaign.getEndDate())) {
                if (campaign.getRequiredPoints() == data.getRequiredPoints()) {
                    // need to implement user point checks
                    SrnUserDevice userDevice = deviceUserRepo.findBySessionId(data.getSessionId());
                    if (userDevice != null) {
                        if (userDevice.getUserId() > 0 ) {
                            SrnDevice device = data.getDeviceData();
                            if (device != null) {
                                SrnVoucherCampaignDetail voucher = voucherRepo.findAvailableVoucher(campaignId);
                                if (voucher != null) {
                                    userVoucherRedeem(userDevice.getUserId(), userDevice.getId(), data.getRedeemTimestamp(), voucher, mMessageRedeemSuccess, 0);
                                } else {
                                    // voucher out of stock
                                    LOGGER.info("voucher campaignid {} is out of stock ! ", data.getCampaignId());
                                    userVoucherRedeem(userDevice.getUserId(), userDevice.getId(), data.getRedeemTimestamp(), voucher, mMessageRedeemFailedOutofstock, 1);
                                }
                            } else {
                                // device not found
                                LOGGER.info("device with imei {} is not found - potential fraud(-1) !", data.getDeviceData().getImei() );
                                userVoucherRedeem(userDevice.getUserId(), userDevice.getId(), data.getRedeemTimestamp(), null, mMessageRedeemFailedOutofstock, -1);
                            }
                        } else {
                            // user not login
                            LOGGER.info("device with imei {} is not logged in - potential fraud(-2) !", data.getDeviceData().getImei() );
                            userVoucherRedeem(userDevice.getUserId(), userDevice.getId(), data.getRedeemTimestamp(), null, mMessageRedeemFailedDefault, -2);
                        }
                    } else {
                        // user device not found
                        LOGGER.info("device with imei {} is not found - potential fraud(-3) !", data.getDeviceData().getImei() );
                    }
                } else {
                    // point not match
                    LOGGER.info("campaignId {} request redeem point is {} not match with existing required points {} - potential fraud(-5) !", data.getCampaignId(), data.getRequiredPoints(), campaign.getRequiredPoints());
                    //userVoucherRedeem(data.getDeviceData().getUserId(), userDevice.getId(), data.getRedeemTimestamp(), null, mMessageRedeemFailedDefault, -5);
                }
            } else {
                // redeem time is exceed from campaign end date
                LOGGER.info("redeemtime {} is exceed from campaign end date campaign[end_date {}]", data.getRedeemTimestamp().toString(), campaign.getEndDate().toString());
                userVoucherRedeem(0, 0, data.getRedeemTimestamp(), null, mMessageRedeemFailedExpired, 2);
            }
        } else {
            // campaign is not valid or already expired
            LOGGER.info("campaignid {} is not valid or already expired !", data.getCampaignId());
            userVoucherRedeem(0, 0, data.getRedeemTimestamp(), null, mMessageRedeemFailedExpired, 2);
        }
    }

    private void userVoucherRedeem(long userId, long deviceId, Timestamp redeemTimestamp, SrnVoucherCampaignDetail voucher, String message, int redeemstatus) {

        Map<String, Object> data = new ArrayMap<>();
        data.put("key","redeem");
        data.put("redeemStatus", String.valueOf(redeemstatus));
        data.put("message", message);

        if (voucher != null) {
            SrnRedeem redeem = new SrnRedeem();
            redeem.setUserId(userId);
            redeem.setDeviceId(deviceId);
            redeem.setVoucherCode(voucher.getVoucherCode());
            redeem.setRedeemDate(redeemTimestamp);
            redeem.setCreated(FormatterUtils.getCurrentTimestamp());
            redeem.setLastUpdated(FormatterUtils.getCurrentTimestamp());
            if (campaignRedeemRepo.save(redeem) != null ) {
                notifPublish.sendNotification(userFcmId, message, data);
            }
        } else {
            notifPublish.sendNotification(userFcmId, message, data);
        }
    }
}