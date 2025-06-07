package com.arhohuttunen.coffeeshop.port.out;

import com.arhohuttunen.coffeeshop.domain.order.Order;
import org.jmolecules.architecture.hexagonal.SecondaryPort;

import java.util.UUID;

@SecondaryPort
public interface Orders {
    Order findOrderById(UUID orderId) throws OrderNotFound;
    Order save(Order order);
    void deleteById(UUID orderId);
}
