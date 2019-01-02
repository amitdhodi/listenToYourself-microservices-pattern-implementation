package com.example.listenToYourself.infrastructure;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface IBrokerChannel {

    String CONSUMEORDER = "consumeOrder";
    String PUBLISHORDER = "publishOrder";

    @Input(CONSUMEORDER)
    SubscribableChannel orderChannelStream();

    @Output(PUBLISHORDER)
    MessageChannel publishOrderChannelStream();
}
