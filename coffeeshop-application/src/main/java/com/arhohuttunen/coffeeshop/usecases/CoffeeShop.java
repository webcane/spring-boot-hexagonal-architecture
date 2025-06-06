package com.arhohuttunen.coffeeshop.usecases;

import com.arhohuttunen.architecture.UseCase;
import com.arhohuttunen.coffeeshop.ports.in.OrderingCoffee;
import com.arhohuttunen.coffeeshop.domain.order.Order;
import com.arhohuttunen.coffeeshop.ports.out.Orders;
import com.arhohuttunen.coffeeshop.ports.out.Payments;
import com.arhohuttunen.coffeeshop.domain.payment.CreditCard;
import com.arhohuttunen.coffeeshop.domain.payment.Payment;
import com.arhohuttunen.coffeeshop.domain.payment.Receipt;

import java.time.LocalDate;
import java.util.UUID;

@UseCase
class CoffeeShop implements OrderingCoffee {
    private final Orders orders;
    private final Payments payments;

    public CoffeeShop(Orders orders, Payments payments) {
        this.orders = orders;
        this.payments = payments;
    }

    @Override
    public Order placeOrder(Order order) {
        return orders.save(order);
    }

    @Override
    public Order updateOrder(UUID orderId, Order order) {
        var existingOrder = orders.findOrderById(orderId);

        return orders.save(existingOrder.update(order));
    }

    @Override
    public void cancelOrder(UUID orderId) {
        var order = orders.findOrderById(orderId);

        if (!order.canBeCancelled()) {
            throw new IllegalStateException("Order is already paid");
        }

        orders.deleteById(orderId);
    }

    @Override
    public Payment payOrder(UUID orderId, CreditCard creditCard) {
        var order = orders.findOrderById(orderId);

        orders.save(order.markPaid());

        return payments.save(new Payment(orderId, creditCard, LocalDate.now()));
    }

    @Override
    public Receipt readReceipt(UUID orderId) {
        var order = orders.findOrderById(orderId);
        var payment = payments.findPaymentByOrderId(orderId);

        return new Receipt(order.getCost(), payment.paid());
    }

    @Override
    public Order takeOrder(UUID orderId) {
        var order = orders.findOrderById(orderId);

        return orders.save(order.markTaken());
    }
}
