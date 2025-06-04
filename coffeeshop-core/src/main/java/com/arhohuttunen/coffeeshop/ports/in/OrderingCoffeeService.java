package com.arhohuttunen.coffeeshop.ports.in;

import com.arhohuttunen.coffeeshop.domain.Order;
import com.arhohuttunen.coffeeshop.domain.CreditCard;
import com.arhohuttunen.coffeeshop.domain.Payment;
import com.arhohuttunen.coffeeshop.domain.Receipt;

import java.util.UUID;

/**
 * Ordering coffee use-case
 */
public interface OrderingCoffeeService {
    Order placeOrder(Order order);
    Order updateOrder(UUID orderId, Order order);
    void cancelOrder(UUID orderId);
    Payment payOrder(UUID orderId, CreditCard creditCard);
    Receipt readReceipt(UUID orderId);
    Order takeOrder(UUID orderId);
}
