package com.example.listenToYourself.service;

import com.example.listenToYourself.domain.model.Order;
import com.example.listenToYourself.event.command.OrderCreatedCommand;

public interface OrderService {

    public void createOrder(OrderCreatedCommand orderCreatedCommand);

    public void publishOrderCreatedEvent(Order order);
}
