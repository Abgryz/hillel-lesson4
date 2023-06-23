package ithillel.bank.currency.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import ithillel.bank.currency.converter.CurrencyApiConverter;
import ithillel.bank.currency.converter.DummyCurrencyConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class CurrencyConverterConfig {

    @Bean
    public WebClient webClient(){
        return WebClient.create();
    }

    @Configuration
    @RequiredArgsConstructor
    @ConditionalOnProperty(name = "currency.convert.provider", havingValue = "currency-api", matchIfMissing = true)
    public static class CurrencyApiConverterConfig{
        private final CurrenciesProperties currenciesProperties;
        private final ObjectMapper objectMapper;
        private final WebClient webClient;
        @Bean
        public CurrencyApiConverter currencyApiConverter(){
            return new CurrencyApiConverter(objectMapper, webClient, currenciesProperties);
        }
    }

    @Configuration
    @ConditionalOnProperty(name = "currency.convert.provider", havingValue = "dummy")
    public static class DummyCurrencyConverterConfig{
        @Bean
        public DummyCurrencyConverter dummyCurrencyConverter(){
            return new DummyCurrencyConverter();
        }
    }
}
