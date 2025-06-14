package com.arhohuttunen.coffeeshop.adapters.rest.receipt;

import com.arhohuttunen.coffeeshop.CoffeeShopMockConfig;
import com.arhohuttunen.coffeeshop.ports.out.Orders;
import com.arhohuttunen.coffeeshop.ports.out.Payments;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import static com.arhohuttunen.coffeeshop.ports.out.OrderTestFactory.aReadyOrder;
import static com.arhohuttunen.coffeeshop.ports.out.OrderTestFactory.anOrder;
import static com.arhohuttunen.coffeeshop.ports.out.PaymentTestFactory.aPaymentForOrder;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
@Import(CoffeeShopMockConfig.class)
public class ReceiptControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Orders orders;

    @Autowired
    private Payments payments;

    @Test
    void readReceipt() throws Exception {
        var order = orders.save(anOrder());
        payments.save(aPaymentForOrder(order));

        mockMvc.perform(get("/receipt/{id}", order.getId()))
                .andExpect(status().isOk());
    }

    @Test
    void completeOrder() throws Exception {
        var order = orders.save(aReadyOrder());

        mockMvc.perform(delete("/receipt/{id}", order.getId()))
                .andExpect(status().isOk());
    }
}
