package com.arhohuttunen.coffeeshop.domain.order;

import com.arhohuttunen.coffeeshop.domain.shared.Drink;
import com.arhohuttunen.coffeeshop.domain.shared.Location;
import com.arhohuttunen.coffeeshop.domain.shared.Milk;
import com.arhohuttunen.coffeeshop.domain.shared.Size;

import java.util.List;

public class OrderTestFactory {
    public static Order anOrder() {
        return new Order(Location.TAKE_AWAY, List.of(new LineItem(Drink.LATTE, Milk.WHOLE, Size.LARGE, 1)));
    }

    public static Order aPaidOrder() {
        return anOrder()
                .markPaid();
    }

    public static Order anOrderInPreparation() {
        return aPaidOrder()
                .markBeingPrepared();
    }

    public static Order aReadyOrder() {
        return anOrderInPreparation()
                .markPrepared();
    }
}
