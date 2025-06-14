package com.arhohuttunen.coffeeshop;

import com.arhohuttunen.coffeeshop.adapters.rest.order.OrderController;
import com.arhohuttunen.coffeeshop.adapters.rest.payment.PaymentController;
import com.arhohuttunen.coffeeshop.adapters.rest.receipt.ReceiptController;
import com.arhohuttunen.coffeeshop.ports.in.OrderingCoffeeService;
import com.arhohuttunen.coffeeshop.ports.in.PreparingCoffeeService;
import com.arhohuttunen.coffeeshop.ports.out.InMemoryOrders;
import com.arhohuttunen.coffeeshop.ports.out.InMemoryPayments;
import com.arhohuttunen.coffeeshop.ports.out.Orders;
import com.arhohuttunen.coffeeshop.ports.out.Payments;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@WebMvcTest({OrderController.class, PaymentController.class, ReceiptController.class})
@Import(CoffeeShopMockConfig.class)
class CoffeeShopUseCasesTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private PreparingCoffeeService coffeeMachine;

    @Test
    void processNewOrder() throws Exception {
        var orderId = placeOrder();
        payOrder(orderId);
        prepareOrder(orderId);
        readReceipt(orderId);
        takeOrder(orderId);
    }

    @Test
    void cancelOrderBeforePayment() throws Exception {
        var orderId = placeOrder();
        cancelOrder(orderId);
    }

    private UUID placeOrder() throws Exception {
        var testOrder = """
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
        var location = mockMvc.perform(post("/order")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(testOrder))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getHeader(HttpHeaders.LOCATION);

        return location != null ? UUID.fromString(location.substring(location.lastIndexOf("/") + 1)) : null;
    }

    private void cancelOrder(UUID orderId) throws Exception {
        mockMvc.perform(delete("/order/{id}", orderId))
                .andExpect(status().isNoContent());
    }

    private void payOrder(UUID orderId) throws Exception {
        var testPayment = """
                {
                    "cardHolderName": "Michael Faraday",
                    "cardNumber": "11223344",
                    "expiryMonth": 12,
                    "expiryYear": 2023
                }
                """;
        mockMvc.perform(put("/payment/{id}", orderId)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(testPayment))
                .andExpect(status().isOk());
    }

    private void prepareOrder(UUID orderId) {
        coffeeMachine.startPreparingOrder(orderId);
        coffeeMachine.finishPreparingOrder(orderId);
    }

    private void readReceipt(UUID orderId) throws Exception {
        mockMvc.perform(get("/receipt/{id}", orderId))
                .andExpect(status().isOk());
    }

    private void takeOrder(UUID orderId) throws Exception {
        mockMvc.perform(delete("/receipt/{id}", orderId))
                .andExpect(status().isOk());
    }
}

