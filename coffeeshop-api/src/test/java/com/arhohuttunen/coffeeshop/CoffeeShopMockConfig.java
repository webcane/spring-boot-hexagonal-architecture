package com.arhohuttunen.coffeeshop;

import com.arhohuttunen.coffeeshop.ports.in.OrderingCoffeeService;
import com.arhohuttunen.coffeeshop.ports.in.PreparingCoffeeService;
import com.arhohuttunen.coffeeshop.ports.out.InMemoryOrders;
import com.arhohuttunen.coffeeshop.ports.out.InMemoryPayments;
import com.arhohuttunen.coffeeshop.ports.out.Orders;
import com.arhohuttunen.coffeeshop.ports.out.Payments;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

/**
 * Configure test's Output ports
 */
@TestConfiguration
public class CoffeeShopMockConfig {
    @Bean
    Orders orders() {
        return new InMemoryOrders();
    }

    @Bean
    Payments payments() {
        return new InMemoryPayments();
    }

    @Bean
    OrderingCoffeeService orderingCoffeeService(Orders orders, Payments payments) {
        return new CoffeeShopUseCase(orders, payments);
    }

    @Bean
    PreparingCoffeeService preparingCoffeeService(Orders orders) {
        return new CoffeeMachineUseCase(orders);
    }
}
