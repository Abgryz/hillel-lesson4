package ithillel.bank.currency.service;

import ithillel.bank.currency.converter.CurrencyConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Currency;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class PersonOperationsService {
    private final CurrencyConverter currencyConverter;

    public double convert(Currency from, Currency to, double amount){
        return CompletableFuture.supplyAsync(() -> currencyConverter.convert(from, to, amount)).join();
    }
}
