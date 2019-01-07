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

    public void publishOrderCreatedEvent(Message<?> message) {
        // Publish for internal consumer to consume and persist order in DB
        log.info("Publishing internal message: " + message.toString() + " to channel: " + brokerChannel.publishOrderInternalChannelStream().toString());
        brokerChannel.publishOrderInternalChannelStream().send(message, 1000);

        // Publish for external consumer to consume
        log.info("Publishing external message: " + message.toString() + " to channel: " + brokerChannel.publishOrderExternalChannelStream().toString());
        brokerChannel.publishOrderExternalChannelStream().send(message, 1000);
    }
}

