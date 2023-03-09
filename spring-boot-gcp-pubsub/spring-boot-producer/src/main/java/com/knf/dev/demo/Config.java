package com.knf.dev.demo;

import com.google.cloud.spring.pubsub.core.PubSubTemplate;
import com.google.cloud.spring.pubsub.integration.outbound.PubSubMessageHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.MessageHandler;

@Configuration
public class Config {

    @Bean
    @ServiceActivator(inputChannel = "knfOutputChannel")
    public MessageHandler messageSender(PubSubTemplate pubsubTemplate) {

        return new PubSubMessageHandler(
                pubsubTemplate, "knowledgefactoryTopic");
    }
}
