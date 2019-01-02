package com.example.listenToYourself.event.listener;

import com.example.listenToYourself.event.OrderCreatedEvent;
import com.example.listenToYourself.event.command.BaseCommand;
import com.example.listenToYourself.event.command.CommandListener;
import com.example.listenToYourself.infrastructure.IBrokerChannel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class EventListener extends Listener{

    @Autowired
    protected CommandListener commandListener;


    @StreamListener(value = IBrokerChannel.CONSUMEORDER)
    public void processOrderCreatedEvent(@Payload String payload,
                                     @Headers Map<String, Object> headers) {

        OrderCreatedEvent orderCreatedEvent = readValue(payload, OrderCreatedEvent.class);

        BaseCommand command = orderCreatedEvent.getOrderCreatedCommand();
        command.invoke(commandListener);

        acknowledgeMessage(headers);
    }
}
