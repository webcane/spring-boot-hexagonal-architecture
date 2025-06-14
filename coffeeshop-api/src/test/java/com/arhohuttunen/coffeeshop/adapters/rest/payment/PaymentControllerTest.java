package com.arhohuttunen.coffeeshop.adapters.rest.payment;

import com.arhohuttunen.coffeeshop.CoffeeShopMockConfig;
import com.arhohuttunen.coffeeshop.ports.out.Orders;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static com.arhohuttunen.coffeeshop.ports.out.OrderTestFactory.anOrder;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
@Import(CoffeeShopMockConfig.class)
public class PaymentControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Orders orders;

    @Test
    void payOrder() throws Exception {
        var order = orders.save(anOrder());

        mockMvc.perform(put("/payment/{id}", order.getId())
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content("""
                        {
                            "cardHolderName": "Michael Faraday",
                            "cardNumber": "11223344",
                            "expiryMonth": 12,
                            "expiryYear": 2023
                        }
                        """))
                .andExpect(status().isOk());
    }
}
