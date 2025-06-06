package com.arhohuttunen.coffeeshop.domain.payment;

import java.math.BigDecimal;
import java.time.LocalDate;

public record Receipt(BigDecimal amount, LocalDate paid) { }
