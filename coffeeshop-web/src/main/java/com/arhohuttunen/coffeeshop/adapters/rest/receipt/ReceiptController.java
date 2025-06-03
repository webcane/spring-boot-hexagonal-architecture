package com.arhohuttunen.coffeeshop.adapters.rest.receipt;

import com.arhohuttunen.coffeeshop.ports.in.OrderingCoffeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class ReceiptController {
    private final OrderingCoffeeService orderingCoffee;

    @GetMapping("/receipt/{id}")
    ResponseEntity<ReceiptResponse> readReceipt(@PathVariable UUID id) {
        var receipt = orderingCoffee.readReceipt(id);
        return ResponseEntity.ok(ReceiptResponse.fromDomain(receipt));
    }
}
