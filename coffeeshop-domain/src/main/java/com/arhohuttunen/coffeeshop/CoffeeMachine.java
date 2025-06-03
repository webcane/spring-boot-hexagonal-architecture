package com.arhohuttunen.coffeeshop;

import com.arhohuttunen.UseCase;
import com.arhohuttunen.coffeeshop.domain.Order;
import com.arhohuttunen.coffeeshop.ports.in.PreparingCoffeeService;
import com.arhohuttunen.coffeeshop.ports.out.Orders;

import java.util.UUID;

@UseCase
class CoffeeMachine implements PreparingCoffeeService {
    private final Orders orders;

    public CoffeeMachine(Orders orders) {
        this.orders = orders;
    }

    @Override
    public Order startPreparingOrder(UUID orderId) {
        var order = orders.findOrderById(orderId);

        return orders.save(order.markBeingPrepared());
    }

    @Override
    public Order finishPreparingOrder(UUID orderId) {
        var order = orders.findOrderById(orderId);

        return orders.save(order.markPrepared());
    }
}
