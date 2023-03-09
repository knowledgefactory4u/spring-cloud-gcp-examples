package com.knf.dev.demo;

import org.springframework.integration.annotation.MessagingGateway;

@MessagingGateway(defaultRequestChannel = "knfOutputChannel")
public interface PubsubOutboundGateway {

		void sendToPubsub(String text);
	}