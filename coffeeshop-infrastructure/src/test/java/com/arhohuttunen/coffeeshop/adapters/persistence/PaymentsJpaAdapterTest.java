package com.arhohuttunen.coffeeshop.adapters.persistence;

import com.arhohuttunen.coffeeshop.ports.out.Payments;
import com.arhohuttunen.coffeeshop.domain.CreditCard;
import com.arhohuttunen.coffeeshop.domain.Payment;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.NoSuchElementException;
import java.util.UUID;

import static com.arhohuttunen.coffeeshop.ports.out.CreditCardTestFactory.aCreditCard;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DataJpaTest
@ComponentScan("com.arhohuttunen.coffeeshop.adapters.persistence")
public class PaymentsJpaAdapterTest {
    @Autowired
    private Payments payments;

    @Test
    void creatingPaymentReturnsPersistedPayment() {
        var now = LocalDate.now();
        var creditCard = aCreditCard();
        var payment = new Payment(UUID.randomUUID(), creditCard, now);

        var persistedPayment = payments.save(payment);

        assertThat(persistedPayment.creditCard()).isEqualTo(creditCard);
        assertThat(persistedPayment.paid()).isEqualTo(now);
    }

    @Test
    @Sql("classpath:data/payment.sql")
    void findingPreviouslyPersistedPaymentReturnsDetails() {
        var payment = payments.findPaymentByOrderId(UUID.fromString("a41c9394-3aa6-4484-b0b4-87de55fa2cf4"));

        var expectedCreditCard = new CreditCard("Michael Faraday", "11223344", Month.JANUARY, Year.of(2023));

        assertThat(payment.creditCard()).isEqualTo(expectedCreditCard);
    }

    @Test
    void findingNonExistingPaymentThrowsException() {
        assertThatThrownBy(() -> payments.findPaymentByOrderId(UUID.randomUUID())).isInstanceOf(NoSuchElementException.class);
    }
}
