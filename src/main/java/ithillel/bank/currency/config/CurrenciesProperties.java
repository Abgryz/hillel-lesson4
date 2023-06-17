package ithillel.bank.currency.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "currency")
public class CurrenciesProperties {
    private String url;
    private String apiKey;
}
