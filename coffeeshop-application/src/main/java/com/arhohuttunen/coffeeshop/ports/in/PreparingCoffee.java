package com.arhohuttunen.coffeeshop.ports.in;

import com.arhohuttunen.coffeeshop.domain.order.Order;

import java.util.UUID;

public interface PreparingCoffee {
    Order startPreparingOrder(UUID orderId);
    Order finishPreparingOrder(UUID orderId);
}
