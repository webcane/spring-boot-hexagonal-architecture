package com.arhohuttunen.coffeeshop.port.out;

import com.arhohuttunen.coffeeshop.domain.payment.Payment;

import java.util.UUID;

public interface Payments {
    Payment findPaymentByOrderId(UUID orderId);
    Payment save(Payment payment);
}
