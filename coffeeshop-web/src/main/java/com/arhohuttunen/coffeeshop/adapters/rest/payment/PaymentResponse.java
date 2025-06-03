package com.arhohuttunen.coffeeshop.adapters.rest.payment;

import com.arhohuttunen.coffeeshop.domain.Payment;

public record PaymentResponse(String cardHolderName, String cardNumber, Integer expiryMonth, Integer expiryYear) {
    public static PaymentResponse fromDomain(Payment payment) {
        var creditCard = payment.creditCard();
        return new PaymentResponse(
                creditCard.cardHolderName(),
                creditCard.cardNumber(),
                creditCard.expiryMonth().getValue(),
                creditCard.expiryYear().getValue()
        );
    }
}
