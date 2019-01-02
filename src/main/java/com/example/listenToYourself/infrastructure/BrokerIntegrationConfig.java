package com.example.listenToYourself.infrastructure;


import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Configuration;

/**
 * Binds the targets mentioned in the IBrokerChannel interface to the kafka broker
 */
@Configuration
@EnableBinding(IBrokerChannel.class)
public class BrokerIntegrationConfig {
}
