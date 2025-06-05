package com.arhohuttunen.coffeeshop.in;

import com.arhohuttunen.coffeeshop.order.Order;
import com.arhohuttunen.coffeeshop.payment.CreditCard;
import com.arhohuttunen.coffeeshop.payment.Payment;
import com.arhohuttunen.coffeeshop.payment.Receipt;

import java.util.UUID;

public interface OrderingCoffee {
    Order placeOrder(Order order);
    Order updateOrder(UUID orderId, Order order);
    void cancelOrder(UUID orderId);
    Payment payOrder(UUID orderId, CreditCard creditCard);
    Receipt readReceipt(UUID orderId);
    Order takeOrder(UUID orderId);
}
