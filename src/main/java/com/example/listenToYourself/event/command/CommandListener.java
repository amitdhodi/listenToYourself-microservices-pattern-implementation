package com.example.listenToYourself.event.command;

import com.example.listenToYourself.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CommandListener {

    @Autowired
    private OrderService orderService;

    public void process(OrderCreatedCommand orderCreatedCommand){
        orderService.createOrder(orderCreatedCommand);
    }

}
