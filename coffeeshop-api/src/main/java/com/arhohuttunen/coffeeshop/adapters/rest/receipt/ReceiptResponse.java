package com.arhohuttunen.coffeeshop.adapters.rest.receipt;

import com.arhohuttunen.coffeeshop.domain.Receipt;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ReceiptResponse(BigDecimal amount, LocalDate paid) {
    public static ReceiptResponse fromDomain(Receipt receipt) {
        return new ReceiptResponse(receipt.amount(), receipt.paid());
    }
}
