package ithillel.bank.currency.service;

import ithillel.bank.currency.converter.CurrencyConverter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Currency;

import static org.junit.jupiter.api.Assertions.*;

class PersonOperationsServiceTest {
    private CurrencyConverter currencyConverter;
    private PersonOperationsService personOperationsService;
    @BeforeEach
    public void setup(){
        currencyConverter = Mockito.mock(CurrencyConverter.class);
        personOperationsService = new PersonOperationsService(currencyConverter);
    }
    @Test
    public void testConvert() {
        Currency from = Currency.getInstance("UAH");
        Currency to = Currency.getInstance("USD");
        double amount = 1000;
        Mockito.when(currencyConverter.convert(from, to, amount))
                .thenReturn(25.0);

        assertEquals(25.0, personOperationsService.convert(from, to, amount));
    }
}