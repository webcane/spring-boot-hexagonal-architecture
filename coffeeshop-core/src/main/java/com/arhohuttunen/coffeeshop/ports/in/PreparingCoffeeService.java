package com.arhohuttunen.coffeeshop.ports.in;

import com.arhohuttunen.coffeeshop.domain.Order;

import java.util.UUID;

/**
 * Preparing coffee use-case
 */
public interface PreparingCoffeeService {

    // TODO rename
    Order startPreparingOrder(UUID orderId);
    Order finishPreparingOrder(UUID orderId);
}
