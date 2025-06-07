package com.arhohuttunen.coffeeshop;

import com.arhohuttunen.coffeeshop.port.out.Orders;
import com.arhohuttunen.coffeeshop.port.out.Payments;
import com.arhohuttunen.coffeeshop.port.out.stub.InMemoryOrders;
import com.arhohuttunen.coffeeshop.port.out.stub.InMemoryPayments;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

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
