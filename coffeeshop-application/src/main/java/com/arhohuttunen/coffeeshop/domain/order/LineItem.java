package com.arhohuttunen.coffeeshop.domain.order;

import com.arhohuttunen.coffeeshop.domain.shared.Drink;
import com.arhohuttunen.coffeeshop.domain.shared.Milk;
import com.arhohuttunen.coffeeshop.domain.shared.Size;

import java.math.BigDecimal;

public record LineItem(Drink drink, Milk milk, Size size, int quantity) {
    // For simplicity every small drink costs 4.0 and large 5.0
    BigDecimal getCost() {
        var price = BigDecimal.valueOf(4.0);
        if (size == Size.LARGE) {
            price = price.add(BigDecimal.ONE);
        }
        return price.multiply(BigDecimal.valueOf(quantity));
    }
}
