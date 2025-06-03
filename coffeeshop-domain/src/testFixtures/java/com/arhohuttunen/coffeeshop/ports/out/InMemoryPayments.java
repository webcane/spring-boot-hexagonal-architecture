package com.arhohuttunen.coffeeshop.ports.out;

import com.arhohuttunen.coffeeshop.domain.Payment;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class InMemoryPayments implements Payments {
    private final Map<UUID, Payment> entities = new HashMap<>();

    @Override
    public Payment findPaymentByOrderId(UUID orderId) {
        return entities.get(orderId);
    }

    @Override
    public Payment save(Payment payment) {
        entities.put(payment.orderId(), payment);
        return payment;
    }
}
