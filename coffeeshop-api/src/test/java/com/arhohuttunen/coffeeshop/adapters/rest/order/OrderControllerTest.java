package com.arhohuttunen.coffeeshop.adapters.rest.order;

import com.arhohuttunen.coffeeshop.DomainTestConfig;
import com.arhohuttunen.coffeeshop.ports.out.Orders;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static com.arhohuttunen.coffeeshop.ports.out.OrderTestFactory.anOrder;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
@Import(DomainTestConfig.class)
public class OrderControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Orders orders;

    private final String orderJson = """
                        {
                            "location": "IN_STORE",
                            "items": [{
                                "drink": "LATTE",
                                "quantity": 1,
                                "milk": "WHOLE",
                                "size": "LARGE"
                            }]
                        }
                        """;

    @Test
    void createOrder() throws Exception {
        mockMvc.perform(post("/order")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(orderJson))
                .andExpect(status().isCreated());
    }

    @Test
    void updateOrder() throws Exception {
        var order = orders.save(anOrder());

        mockMvc.perform(post("/order/{id}", order.getId())
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(orderJson))
                .andExpect(status().isOk());
    }

    @Test
    void cancelOrder() throws Exception {
        var order = orders.save(anOrder());

        mockMvc.perform(delete("/order/{id}", order.getId()))
                .andExpect(status().isNoContent());
    }
}