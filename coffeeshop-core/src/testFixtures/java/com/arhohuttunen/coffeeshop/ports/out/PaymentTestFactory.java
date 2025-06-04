package com.arhohuttunen.coffeeshop.ports.out;

import com.arhohuttunen.coffeeshop.domain.Order;
import com.arhohuttunen.coffeeshop.domain.Payment;

import java.time.LocalDate;

import static com.arhohuttunen.coffeeshop.ports.out.CreditCardTestFactory.aCreditCard;

public class PaymentTestFactory {
    public static Payment aPaymentForOrder(Order order) {
        return new Payment(order.getId(), aCreditCard(), LocalDate.now());
    }
}
