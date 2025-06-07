package com.arhohuttunen.coffeeshop.adapter.in.rest;

import com.arhohuttunen.coffeeshop.domain.order.Order;
import com.arhohuttunen.coffeeshop.domain.shared.Location;

import java.util.List;

public record OrderRequest(Location location, List<LineItemRequest> items) {
    public Order toDomain() {
        return new Order(location, items.stream().map(LineItemRequest::toDomain).toList());
    }
}
