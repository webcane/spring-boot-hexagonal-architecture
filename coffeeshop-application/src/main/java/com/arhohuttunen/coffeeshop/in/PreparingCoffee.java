package com.arhohuttunen.coffeeshop.in;

import com.arhohuttunen.coffeeshop.order.Order;

import java.util.UUID;

public interface PreparingCoffee {
    Order startPreparingOrder(UUID orderId);
    Order finishPreparingOrder(UUID orderId);
}
