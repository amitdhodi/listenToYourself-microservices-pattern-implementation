package com.example.listenToYourself.service;

import com.example.listenToYourself.common.OrderCommandConverter;
import com.example.listenToYourself.domain.model.Order;
import com.example.listenToYourself.event.EventHeader;
import com.example.listenToYourself.event.OrderCreatedEvent;
import com.example.listenToYourself.event.command.OrderCreatedCommand;
import com.example.listenToYourself.event.publisher.EventPublisher;
import com.example.listenToYourself.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService{
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private EventPublisher eventPublisher;

    @Autowired
    private OrderCommandConverter orderCommandConverter;


    @Override
    public void createOrder(OrderCreatedCommand orderCreatedCommand) {
        // Idempotency check to ensure duplicates are ignored
        if(orderRepository.existsById(orderCreatedCommand.getOrderId())){
            log.warn("Order with id: " + orderCreatedCommand.getOrderId() + " already exists!");
        } else {
            Order order = orderCommandConverter.convertToObj(orderCreatedCommand);
            orderRepository.save(order);
        }
    }

    /**
     * Raise OrderCreated event to kafka
     * the event should be consumed internally which will then persist the order in repository
     * and the same event would be sent to the external consumers also
     * this way using Listen To Yourself pattern both: persistence of order to DB and raising event for
     * external consumers happen using kafka calls only ensuring atomicity i.e. either both succeed or both fail
     */
    @Override
    public void publishOrderCreatedEvent(Order order) {
        EventHeader header = new EventHeader("com.example.OrderCreated.1.0.0");
        OrderCreatedEvent orderCreatedEvent = new OrderCreatedEvent(header,
                                                    orderCommandConverter.convertFromObj(order));

        Message<?> message = MessageBuilder.withPayload(orderCreatedEvent).build();
        eventPublisher.publishOrderCreatedEvent(message);
    }
}
