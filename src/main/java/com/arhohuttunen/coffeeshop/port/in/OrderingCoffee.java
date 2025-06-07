package com.arhohuttunen.coffeeshop.port.in;

import com.arhohuttunen.coffeeshop.domain.order.Order;
import com.arhohuttunen.coffeeshop.domain.payment.CreditCard;
import com.arhohuttunen.coffeeshop.domain.payment.Payment;
import com.arhohuttunen.coffeeshop.domain.payment.Receipt;
import org.jmolecules.architecture.hexagonal.PrimaryPort;

import java.util.UUID;

@PrimaryPort
public interface OrderingCoffee {
    Order placeOrder(Order order);
    Order updateOrder(UUID orderId, Order order);
    void cancelOrder(UUID orderId);
    Payment payOrder(UUID orderId, CreditCard creditCard);
    Receipt readReceipt(UUID orderId);
    Order takeOrder(UUID orderId);
}
