package com.daisy.microservices.currencyconversionservice;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
// this is the interface that will be used to call the currency exchange service using feign
//@FeignClient(name = "currency-exchange", url="localhost:8000")
// make it load balance between multiple instances of currency exchange service
// Feign Client talks to Eureka and pick up the instances of currency exchange service and do load balancing between them.
@FeignClient(name = "currency-exchange")
public interface CurrencyExchangeProxy {
    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public CurrencyConversion retrieveExchangeValue(
            @PathVariable String from,
            @PathVariable String to);
}
