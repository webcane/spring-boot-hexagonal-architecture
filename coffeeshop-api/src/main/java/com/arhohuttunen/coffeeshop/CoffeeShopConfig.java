package com.arhohuttunen.coffeeshop;

import com.arhohuttunen.UseCase;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Add UsesCases into spring application context
 */
@Configuration
@ComponentScan(basePackages = "com.arhohuttunen.coffeeshop",
        includeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, value = UseCase.class))
public class CoffeeShopConfig {
}
