package com.arhohuttunen.coffeeshop.adapters.persistence;

import com.arhohuttunen.coffeeshop.domain.LineItem;
import com.arhohuttunen.coffeeshop.domain.Order;
import com.arhohuttunen.coffeeshop.ports.out.OrderNotFoundException;
import com.arhohuttunen.coffeeshop.ports.out.Orders;
import com.arhohuttunen.coffeeshop.shared.Drink;
import com.arhohuttunen.coffeeshop.shared.Location;
import com.arhohuttunen.coffeeshop.shared.Milk;
import com.arhohuttunen.coffeeshop.shared.Size;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DataJpaTest
@ComponentScan("com.arhohuttunen.coffeeshop.adapters.persistence")
public class OrdersJpaAdapterTest {
    @Autowired
    private Orders orders;

    @Autowired
    private OrderJpaRepository orderJpaRepository;

    @Test
    void creatingOrderReturnsPersistedOrder() {
        var order = new Order(Location.TAKE_AWAY, List.of(
                new LineItem(Drink.LATTE, Milk.WHOLE, Size.SMALL, 1)
        ));

        var persistedOrder = orders.save(order);

        assertThat(persistedOrder.getLocation()).isEqualTo(Location.TAKE_AWAY);
        assertThat(persistedOrder.getItems()).containsExactly(
                new LineItem(Drink.LATTE, Milk.WHOLE, Size.SMALL, 1)
        );
    }

    @Test
    @Sql("classpath:data/order.sql")
    void findingPreviouslyPersistedOrderReturnsDetails() {
        var order = orders.findOrderById(UUID.fromString("757d9c0f-400f-4137-9aea-83e64ba3efb2"));

        assertThat(order.getLocation()).isEqualTo(Location.IN_STORE);
        assertThat(order.getItems()).containsExactly(new LineItem(Drink.ESPRESSO, Milk.SKIMMED, Size.LARGE, 1));
    }

    @Test
    void findingNonExistingOrderThrowsException() {
        assertThatThrownBy(() -> orders.findOrderById(UUID.randomUUID())).isInstanceOf(OrderNotFoundException.class);
    }

    @Test
    @Sql("classpath:data/order.sql")
    void deletesPreviouslyPersistedOrder() {
        orders.deleteById(UUID.fromString("757d9c0f-400f-4137-9aea-83e64ba3efb2"));

        assertThat(orderJpaRepository.findById(UUID.fromString("757d9c0f-400f-4137-9aea-83e64ba3efb2"))).isEmpty();
    }
}
