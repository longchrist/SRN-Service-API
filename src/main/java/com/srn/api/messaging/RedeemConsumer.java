package com.srn.api.messaging;/*
 Author: vikraa
 created: 1/2/19 - 8:41 AM
*/

import com.srn.api.model.dto.SrnCampaignRedeemDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RedeemConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(RedeemConsumer.class);
    private static String TAG = RedeemConsumer.class.getSimpleName();

    @RabbitListener(queues = "queue.redeem")
    public void processRedeem(SrnCampaignRedeemDto data) {
        LOGGER.info("processing redeem --> "  + data.toString());
    }
}