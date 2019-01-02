package com.srn.api.messaging;/*
 Author: vikraa
 created: 1/1/19 - 11:30 PM
*/

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitPublish {
    private final RabbitTemplate rabbitTemplate;

    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitPublish.class);
    private static String TAG = RabbitPublish.class.getSimpleName();

    @Autowired
    public RabbitPublish(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
        LOGGER.info("init rabbit");
    }

    public void publishMessage(String queueName, Object data) {
        LOGGER.info("publish message : " + data.toString());
        this.rabbitTemplate.convertAndSend(queueName, data);
    }
}