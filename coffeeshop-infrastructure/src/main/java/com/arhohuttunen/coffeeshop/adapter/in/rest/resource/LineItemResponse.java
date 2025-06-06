package com.arhohuttunen.coffeeshop.adapter.in.rest.resource;

import com.arhohuttunen.coffeeshop.domain.order.LineItem;
import com.arhohuttunen.coffeeshop.domain.shared.Drink;
import com.arhohuttunen.coffeeshop.domain.shared.Milk;
import com.arhohuttunen.coffeeshop.domain.shared.Size;

public record LineItemResponse(Drink drink, Milk milk, Size size, Integer quantity) {
    public static LineItemResponse fromDomain(LineItem lineItem) {
        return new LineItemResponse(
                lineItem.drink(),
                lineItem.milk(), lineItem.size(), lineItem.quantity()
        );
    }
}
