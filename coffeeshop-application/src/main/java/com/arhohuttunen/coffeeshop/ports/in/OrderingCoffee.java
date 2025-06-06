package com.arhohuttunen.coffeeshop.ports.in;

import com.arhohuttunen.coffeeshop.domain.order.Order;
import com.arhohuttunen.coffeeshop.domain.payment.CreditCard;
import com.arhohuttunen.coffeeshop.domain.payment.Payment;
import com.arhohuttunen.coffeeshop.domain.payment.Receipt;

import java.util.UUID;

public interface OrderingCoffee {
    Order placeOrder(Order order);
    Order updateOrder(UUID orderId, Order order);
    void cancelOrder(UUID orderId);
    Payment payOrder(UUID orderId, CreditCard creditCard);
    Receipt readReceipt(UUID orderId);
    Order takeOrder(UUID orderId);
}
