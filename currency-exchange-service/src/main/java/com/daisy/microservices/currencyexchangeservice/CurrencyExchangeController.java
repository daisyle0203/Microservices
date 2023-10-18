package com.daisy.microservices.currencyexchangeservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyExchangeController {

    private Logger logger = LoggerFactory.getLogger(CurrencyExchangeController.class);
    @Autowired
    private CurrencyExchangeRepository repository;
    // Get the value of the port
    @Autowired
    private Environment environment;
    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public CurrencyExchange retrieveExchangeValue(
            @PathVariable String from,
            @PathVariable String to
    ) {
        logger.info("retrieveExchangeValue called with {} to {}", from, to);

        // CurrencyExchange currencyExchange = new CurrencyExchange(1000L, "USD", "INR", BigDecimal.valueOf(50));
        // Instead of hardcoding the values, we will get the values from the database using JPA
        CurrencyExchange currencyExchange = repository.findByFromAndTo(from, to);
        if(currencyExchange == null) {
            throw new RuntimeException("Unable to find data for " + from + " to " + to);
        }
        // Extract the port from the environment and set it into the response
        String port = environment.getProperty("local.server.port");
        currencyExchange.setEnvironment(port);

        return currencyExchange;
    }
}
