package com.example.listenToYourself.repository;

import com.example.listenToYourself.domain.model.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, String> {
}
