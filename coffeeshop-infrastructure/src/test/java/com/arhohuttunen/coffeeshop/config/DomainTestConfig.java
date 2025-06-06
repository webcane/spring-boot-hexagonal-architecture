package com.arhohuttunen.coffeeshop.config;

import com.arhohuttunen.coffeeshop.ports.out.Orders;
import com.arhohuttunen.coffeeshop.ports.out.Payments;
import com.arhohuttunen.coffeeshop.ports.out.stub.InMemoryOrders;
import com.arhohuttunen.coffeeshop.ports.out.stub.InMemoryPayments;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

@TestConfiguration
@Import(DomainConfig.class)
public class DomainTestConfig {
    @Bean
    Orders orders() {
        return new InMemoryOrders();
    }

    @Bean
    Payments payments() {
        return new InMemoryPayments();
    }
}
