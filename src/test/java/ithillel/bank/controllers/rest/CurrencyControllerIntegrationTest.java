package ithillel.bank.controllers.rest;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import ithillel.bank.config.WireMockConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CurrencyControllerIntegrationTest {
    @Autowired
    protected MockMvc mockMvc;
    @Autowired
    protected WireMockServer wireMockServer;

    @DynamicPropertySource
    public static void registerWiremockBaseUrl(DynamicPropertyRegistry registry) {
        registry.add("wiremock.baseurl", WireMockConfig.wireMockServer::baseUrl);
    }

    @Test
    public void shouldConvert() throws Exception {
        wireMockServer.stubFor(WireMock.get(urlPathEqualTo("/v3/latest"))
                .withQueryParam("apikey", equalTo("1234567890"))
                .withQueryParam("base_currency", equalTo("UAH"))
                .withQueryParam("currencies", equalTo("USD"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody("""
                                {
                                    "meta": {
                                        "last_updated_at": "2023-06-17T23:59:59Z"
                                    },
                                    "data": {
                                        "USD": {
                                            "code": "USD",
                                            "value": 0.025
                                        }
                                    }
                                }
                                """))
        );

        var query = get("/api/currencies")
                .queryParam("from", "UAH")
                .queryParam("to", "USD")
                .queryParam("amount", "1000");
        var response = mockMvc.perform(query)
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
        assertEquals("25.0", response);
    }
}