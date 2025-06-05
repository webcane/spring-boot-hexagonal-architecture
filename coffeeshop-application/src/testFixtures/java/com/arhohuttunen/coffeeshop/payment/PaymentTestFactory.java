package com.arhohuttunen.coffeeshop.payment;

import com.arhohuttunen.coffeeshop.order.Order;

import java.time.LocalDate;

import static com.arhohuttunen.coffeeshop.payment.CreditCardTestFactory.aCreditCard;

public class PaymentTestFactory {
    public static Payment aPaymentForOrder(Order order) {
        return new Payment(order.getId(), aCreditCard(), LocalDate.now());
    }
}
