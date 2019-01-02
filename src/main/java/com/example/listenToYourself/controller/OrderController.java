package com.example.listenToYourself.controller;

import com.example.listenToYourself.domain.model.Order;
import com.example.listenToYourself.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping(value="v1/createOrder", consumes = "application/json", produces = "application/json")
    public ResponseEntity<String> createOrder(@RequestBody Order order){
        orderService.publishOrderCreatedEvent(order);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
