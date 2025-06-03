package com.arhohuttunen.coffeeshop.adapters.rest.order;

import com.arhohuttunen.coffeeshop.domain.Order;
import com.arhohuttunen.coffeeshop.shared.Location;

import java.util.List;

public record OrderRequest(Location location, List<LineItemRequest> items) {
    public Order toDomain() {
        return new Order(location, items.stream().map(LineItemRequest::toDomain).toList());
    }
}
