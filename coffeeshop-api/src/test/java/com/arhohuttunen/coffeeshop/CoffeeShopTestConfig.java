package com.arhohuttunen.coffeeshop;

import com.arhohuttunen.coffeeshop.ports.out.InMemoryOrders;
import com.arhohuttunen.coffeeshop.ports.out.InMemoryPayments;
import com.arhohuttunen.coffeeshop.ports.out.Orders;
import com.arhohuttunen.coffeeshop.ports.out.Payments;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

/**
 * Configure test's Output ports
 */
@TestConfiguration
@Import(CoffeeShopConfig.class)
public class CoffeeShopTestConfig {
    @Bean
    Orders orders() {
        return new InMemoryOrders();
    }

    @Bean
    Payments payments() {
        return new InMemoryPayments();
    }
}
