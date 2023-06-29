package ithillel.bank.currency.converter;

import com.fasterxml.jackson.databind.ObjectMapper;
import ithillel.bank.currency.config.CurrenciesProperties;
import ithillel.bank.currency.model.data.CurrencyData;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Currency;
import java.util.Map;

@RequiredArgsConstructor
public class CurrencyApiConverter implements CurrencyConverter {
    private final ObjectMapper objectMapper;
    private final WebClient webClient;
    private final CurrenciesProperties currenciesProperties;
    @Override
    public double convert(Currency from, Currency to, double amount) {
        URI uri = UriComponentsBuilder.fromHttpUrl(currenciesProperties.getUrl())
                .queryParam("apikey", currenciesProperties.getApiKey())
                .queryParam("base_currency", from.getCurrencyCode())
                .queryParam("currencies", to.getCurrencyCode())
                .build()
                .toUri();
        Map response = webClient.method(HttpMethod.GET)
                .uri(uri)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(Map.class)
                .block();
        Map data = (Map) response.get("data");
        CurrencyData currencyData = objectMapper.convertValue(data.get(to.getCurrencyCode()), CurrencyData.class);

        return currencyData.getValue() * amount;
    }
}
