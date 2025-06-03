package com.arhohuttunen.coffeeshop.adapters.rest.payment;

import jakarta.validation.constraints.NotNull;

public record PaymentRequest(
        @NotNull String cardHolderName,
        @NotNull String cardNumber,
        @NotNull Integer expiryMonth,
        @NotNull Integer expiryYear) {}
