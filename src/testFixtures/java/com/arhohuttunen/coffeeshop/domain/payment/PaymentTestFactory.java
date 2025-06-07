package com.arhohuttunen.coffeeshop.domain.payment;

import com.arhohuttunen.coffeeshop.domain.order.Order;

import java.time.LocalDate;

import static com.arhohuttunen.coffeeshop.domain.payment.CreditCardTestFactory.aCreditCard;

public class PaymentTestFactory {
    public static Payment aPaymentForOrder(Order order) {
        return new Payment(order.getId(), aCreditCard(), LocalDate.now());
    }
}
