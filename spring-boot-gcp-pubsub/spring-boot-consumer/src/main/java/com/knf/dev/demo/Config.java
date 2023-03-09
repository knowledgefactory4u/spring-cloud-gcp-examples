package com.knf.dev.demo;

import com.google.cloud.spring.pubsub.core.PubSubTemplate;
import com.google.cloud.spring.pubsub.integration.inbound.PubSubInboundChannelAdapter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.messaging.MessageChannel;

@Configuration
public class Config {

    @Bean
    public PubSubInboundChannelAdapter messageChannelAdapter(
            @Qualifier("knfInputChannel") MessageChannel knfInputChannel,
            PubSubTemplate pubSubTemplate) {

        PubSubInboundChannelAdapter adapter =
                new PubSubInboundChannelAdapter(
                        pubSubTemplate, "knowledgefactorySubscription");
        adapter.setOutputChannel(knfInputChannel);

        return adapter;
    }

    @Bean
    public MessageChannel knfInputChannel() {
        return new DirectChannel();
    }


    @ServiceActivator(inputChannel = "knfInputChannel")
    public void messageReceiver(String message) {

        System.out.println("Message arrived! Message: " + message);

    }
}
