package ithillel.bank.currency.converter;

import java.util.Currency;

public class DummyCurrencyConverter implements CurrencyConverter{
    @Override
    public double convert(Currency from, Currency to, double amount) {
        return 100 * amount;
    }
}
