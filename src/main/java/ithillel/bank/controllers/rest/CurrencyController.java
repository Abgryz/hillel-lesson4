package ithillel.bank.controllers.rest;

import ithillel.bank.currency.converter.CurrencyConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Currency;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CurrencyController {
    private final CurrencyConverter currencyConverter;

    @GetMapping("/currencies")
    public double convert(@RequestParam("from") String from, @RequestParam("to")String to, @RequestParam("amount") double amount){
        return currencyConverter.convert(Currency.getInstance(from), Currency.getInstance(to), amount);
    }
}
