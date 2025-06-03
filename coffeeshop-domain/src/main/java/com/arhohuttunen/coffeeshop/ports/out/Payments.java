package com.arhohuttunen.coffeeshop.ports.out;

import com.arhohuttunen.coffeeshop.domain.Payment;

import java.util.UUID;

public interface Payments {
    Payment findPaymentByOrderId(UUID orderId);
    Payment save(Payment payment);
}
