package com.arhohuttunen.coffeeshop.ports.out;

import com.arhohuttunen.coffeeshop.domain.Order;

import java.util.UUID;

public interface Orders {
    Order findOrderById(UUID orderId) throws OrderNotFoundException;
    Order save(Order order);
    void deleteById(UUID orderId);
}
