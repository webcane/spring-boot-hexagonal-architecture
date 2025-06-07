package com.arhohuttunen.coffeeshop.port.in;

import com.arhohuttunen.coffeeshop.domain.order.Order;
import org.jmolecules.architecture.hexagonal.PrimaryPort;

import java.util.UUID;

@PrimaryPort
public interface PreparingCoffee {
    Order startPreparingOrder(UUID orderId);
    Order finishPreparingOrder(UUID orderId);
}
