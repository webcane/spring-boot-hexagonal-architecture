package com.arhohuttunen.coffeeshop.adapter.in.rest;

import com.arhohuttunen.coffeeshop.domain.order.LineItem;
import com.arhohuttunen.coffeeshop.domain.shared.Drink;
import com.arhohuttunen.coffeeshop.domain.shared.Milk;
import com.arhohuttunen.coffeeshop.domain.shared.Size;

public record LineItemRequest(Drink drink, Milk milk, Size size, Integer quantity) {
    public LineItem toDomain() {
        return new LineItem(drink, milk, size, quantity);
    }
}
