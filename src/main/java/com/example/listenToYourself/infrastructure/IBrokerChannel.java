package com.example.listenToYourself.infrastructure;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface IBrokerChannel {

    String CONSUMEORDER = "consumeOrder";
    String PUBLISHORDERINTERNAL = "publishOrderInternal";
    String PUBLISHORDEREXTERNAL = "publishOrderExternal";

    @Input(CONSUMEORDER)
    SubscribableChannel orderChannelStream();

    @Output(PUBLISHORDERINTERNAL)
    MessageChannel publishOrderInternalChannelStream();

    @Output(PUBLISHORDEREXTERNAL)
    MessageChannel publishOrderExternalChannelStream();
}
