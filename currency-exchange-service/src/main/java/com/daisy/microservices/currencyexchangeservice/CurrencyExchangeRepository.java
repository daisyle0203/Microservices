package com.daisy.microservices.currencyexchangeservice;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyExchangeRepository
        extends JpaRepository<CurrencyExchange, Long> {
    // Spring Data JPA will implement this method for us based on the name of the method
    CurrencyExchange findByFromAndTo(String from, String to);
}