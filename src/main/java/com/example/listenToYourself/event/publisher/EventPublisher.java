package com.example.listenToYourself.event.publisher;

import com.example.listenToYourself.infrastructure.IBrokerChannel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;


@Component
@Slf4j
public class EventPublisher {

    @Autowired
    private IBrokerChannel brokerChannel;

    public boolean publishOrderCreatedEvent(Message<?> message) {
        log.info("Publishing message: " + message.toString() + " to channel: " + brokerChannel.publishOrderChannelStream().toString());
        return brokerChannel.publishOrderChannelStream().send(message, 1000);
    }
}

