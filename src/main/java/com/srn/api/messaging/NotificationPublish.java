package com.srn.api.messaging;
/*
 Author: vikraa
 created: 1/2/19 - 11:59 PM
*/

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class NotificationPublish {

    private static final Logger LOGGER = LoggerFactory.getLogger(NotificationPublish.class);
    private static String TAG = NotificationPublish.class.getSimpleName();

    @Value("google.fcm")
    private String url;

    public void sendRedeemNotificationResult(String fcmId, int redeemStatus, String message) {

    }

}