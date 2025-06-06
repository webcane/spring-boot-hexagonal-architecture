package com.arhohuttunen.coffeeshop.adapter.in.rest.resource;

import com.arhohuttunen.coffeeshop.domain.order.Order;
import com.arhohuttunen.coffeeshop.domain.shared.Location;

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
