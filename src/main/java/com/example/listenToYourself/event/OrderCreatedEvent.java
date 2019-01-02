package com.example.listenToYourself.event;

import com.example.listenToYourself.event.command.OrderCreatedCommand;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OrderCreatedEvent extends BaseEvent{

    private OrderCreatedCommand orderCreatedCommand;

    public OrderCreatedEvent(EventHeader eventHeader, OrderCreatedCommand orderCreatedCommand){
        this.event = eventHeader;
        this.orderCreatedCommand = orderCreatedCommand;
    }

}
