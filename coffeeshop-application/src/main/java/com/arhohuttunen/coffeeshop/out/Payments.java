package com.arhohuttunen.coffeeshop.out;

import com.arhohuttunen.coffeeshop.payment.Payment;

import java.util.UUID;

public interface Payments {
    Payment findPaymentByOrderId(UUID orderId);
    Payment save(Payment payment);
}
