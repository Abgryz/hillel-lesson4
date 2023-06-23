package ithillel.bank.controllers.rest;

import ithillel.bank.currency.converter.CurrencyConverter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Currency;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CurrencyController {
    private final CurrencyConverter currencyConverter;

    @GetMapping("/currencies")
    public double convert(@RequestParam("from") Currency from, @RequestParam("to") Currency to, @RequestParam("amount") double amount){
        log.info("convert: from={}, to={}, amount={}", from, to, amount);
        return currencyConverter.convert(from, to, amount);
    }
}
