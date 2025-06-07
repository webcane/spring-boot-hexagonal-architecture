package com.arhohuttunen.coffeeshop.port.out;

import com.arhohuttunen.coffeeshop.domain.payment.Payment;
import org.jmolecules.architecture.hexagonal.SecondaryPort;

import java.util.UUID;

@SecondaryPort
public interface Payments {
    Payment findPaymentByOrderId(UUID orderId);
    Payment save(Payment payment);
}
