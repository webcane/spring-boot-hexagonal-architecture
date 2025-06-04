package com.arhohuttunen.coffeeshop;

import com.arhohuttunen.UseCase;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(basePackages = "com.arhohuttunen.coffeeshop",
        includeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, value = UseCase.class))
public class CoffeeShopConfig {
}
