package com.arhohuttunen.coffeeshop.adapters.rest.order;

import com.arhohuttunen.coffeeshop.domain.Order;
import com.arhohuttunen.coffeeshop.shared.Location;

import java.math.BigDecimal;
import java.util.List;

public record OrderResponse(Location location, List<LineItemResponse> items, BigDecimal cost) {
    public static OrderResponse fromDomain(Order order) {
        return new OrderResponse(
                order.getLocation(),
                order.getItems().stream().map(LineItemResponse::fromDomain).toList(),
                order.getCost()
        );
    }
}
