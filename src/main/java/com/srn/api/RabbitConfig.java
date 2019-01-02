package com.srn.api;/*
 Author: vikraa
 created: 1/1/19 - 11:06 PM
*/

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    public static final String REDEEM_QUEUE = "queue.redeem";
    public static final String REDEEM_QUEUE_NOTIFICATION = "queue.redeem.notification";
    public static final String INBOX_QUEUE = "queue.inbox";
    public static final String POINT_CLAIM_QUEUE = "queue.point.claim";
    public static final String EXCHANGE_ROUTE = "srn.crm";

    /* queue */
    @Bean
    Queue redeem() {
        return QueueBuilder.durable(REDEEM_QUEUE).build();
    }

    @Bean
    Queue redeemNotification() {
        return QueueBuilder.durable(REDEEM_QUEUE_NOTIFICATION).build();
    }

    @Bean
    Queue inboxQueue() {
        return QueueBuilder.durable(INBOX_QUEUE).build();
    }

    @Bean
    Queue pointClaimQueue() {
        return QueueBuilder.durable(POINT_CLAIM_QUEUE).build();
    }

    /* exchange */
    @Bean
    Exchange exchangeRoute() {
        return ExchangeBuilder.topicExchange(EXCHANGE_ROUTE).build();
    }

    /* binding */
    @Bean
    Binding bindingRedeem(@Qualifier("redeem") Queue queueName, TopicExchange exchangeRoute) {
        return BindingBuilder.bind(queueName).to(exchangeRoute).with(REDEEM_QUEUE);
    }

    @Bean
    Binding bindingRedeemNotification(@Qualifier("redeemNotification") Queue queueName, TopicExchange exchangeRoute) {
        return BindingBuilder.bind(queueName).to(exchangeRoute).with(REDEEM_QUEUE_NOTIFICATION);
    }

    @Bean
    Binding bindingInbox(@Qualifier("inboxQueue") Queue queueName, TopicExchange exchangeRoute) {
        return BindingBuilder.bind(queueName).to(exchangeRoute).with(INBOX_QUEUE);
    }
    @Bean
    Binding bindingPointClaim(@Qualifier("pointClaimQueue") Queue queueName, TopicExchange exchangeRoute) {
        return BindingBuilder.bind(queueName).to(exchangeRoute).with(POINT_CLAIM_QUEUE);
    }

}