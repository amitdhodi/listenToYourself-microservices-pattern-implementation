package com.example.listenToYourself.common;

import com.example.listenToYourself.domain.model.Order;
import com.example.listenToYourself.event.command.OrderCreatedCommand;
import org.springframework.stereotype.Component;

@Component
public class OrderCommandConverter extends Converter<Order, OrderCreatedCommand> {
    @Override
    public OrderCreatedCommand convertFromObj(Order fromObj) {
        return getModelMapper().map(fromObj, OrderCreatedCommand.class);
    }

    @Override
    public Order convertToObj(OrderCreatedCommand orderCreatedCommand) {
        return getModelMapper().map(orderCreatedCommand, Order.class);
    }
}
