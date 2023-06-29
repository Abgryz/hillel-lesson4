package ithillel.bank.controllers.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import ithillel.bank.config.WireMockConfig;
import ithillel.bank.tables.transaction.Transaction;
import ithillel.bank.tables.transaction.TransactionAmount;
import ithillel.bank.tables.transaction.TransactionData;
import ithillel.bank.tables.account.Account;
import ithillel.bank.tables.account.AccountRepository;
import ithillel.bank.tables.person.Person;
import ithillel.bank.tables.person.PersonRepository;
import ithillel.bank.tables.transaction.TransactionRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class TransactionControllerIntegrationTest {
    @Autowired
    protected MockMvc mockMvc;
    @Autowired
    protected ObjectMapper objectMapper;
    @Autowired
    protected WireMockServer wireMockServer;

    @Autowired
    protected AccountRepository accountRepository;
    @Autowired
    protected PersonRepository personRepository;
    @Autowired
    protected TransactionRepository transactionRepository;

    @DynamicPropertySource
    public static void registerWiremockBaseUrl(DynamicPropertyRegistry registry) {
        registry.add("wiremock.baseurl", WireMockConfig.wireMockServer::baseUrl);
    }
    @Test
    void createTransactionWithoutConvertTest() throws Exception {
        Account fromAccount = accountRepository.saveAndFlush(
                Account.builder()
                        .iban("US1234567890123456789010")
                        .currency("USD")
                        .person(personRepository.saveAndFlush(
                                Person.builder()
                                        .name("John Smith")
                                        .uid("1f0e3dad99908345f7439f8ffabdffc0")
                                        .build()))
                        .balance(200)
                        .uid("1f0e3dad99908345f7439f8ffabdffc0")
                        .build());
        Account toAccount = accountRepository.saveAndFlush(
                Account.builder()
                        .iban("GB5432109876543210987650")
                        .currency("USD")
                        .person(personRepository.saveAndFlush(
                                Person.builder()
                                        .name("Emily Johnson")
                                        .uid("098f6bcd4621d373cade4e832627b4f0")
                                        .build()))
                        .balance(100)
                        .uid("098f6bcd4621d373cade4e832627b4f0")
                        .build());

        var requestBody = TransactionData.builder()
                .from(fromAccount.getIban())
                .to(toAccount.getIban())
                .amount(new TransactionAmount(200, "USD"))
                .build();
        var query = post("/api/transactions")
                .content(objectMapper.writeValueAsString(requestBody))
                .contentType(MediaType.APPLICATION_JSON);
        var response = mockMvc.perform(query)
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        var responseData = objectMapper.readValue(response, TransactionData.class);
        responseData.setCreatedAt(null);
        requestBody.setUid(responseData.getUid());
        assertEquals(responseData, requestBody);
        assertEquals(accountRepository.findAccountByIban("US1234567890123456789010").orElseThrow().getBalance(), 0);
        assertEquals(accountRepository.findAccountByIban("GB5432109876543210987650").orElseThrow().getBalance(), 300.0);

        Transaction transaction = transactionRepository.findByUid(responseData.getUid()).orElseThrow();
        assertEquals(transaction.getFromAccount().getIban(), responseData.getFrom());
        assertEquals(transaction.getToAccount().getIban(), responseData.getTo());
        assertEquals(transaction.getAmount(), responseData.getAmount().getValue());
    }
    @Test
    void createTransactionTest() throws Exception {
        Account fromAccount = accountRepository.saveAndFlush(
                Account.builder()
                        .iban("US1234567890123456789012")
                        .currency("USD")
                        .person(personRepository.saveAndFlush(
                                Person.builder()
                                        .name("John Smith")
                                        .uid("1f0e3dad99908345f7439f8ffabdffc5")
                                        .build()))
                        .balance(200)
                        .uid("1f0e3dad99908345f7439f8ffabdffc4")
                        .build());
        Account toAccount = accountRepository.saveAndFlush(
                Account.builder()
                        .iban("GB5432109876543210987654")
                        .currency("GBP")
                        .person(personRepository.saveAndFlush(
                                Person.builder()
                                        .name("Emily Johnson")
                                        .uid("098f6bcd4621d373cade4e832627b4f7")
                                        .build()))
                        .balance(100)
                        .uid("098f6bcd4621d373cade4e832627b4f6")
                        .build());

        wireMockServer.stubFor(WireMock.get(urlPathEqualTo("/v3/latest"))
                .withQueryParam("apikey", equalTo("1234567890"))
                .withQueryParam("base_currency", equalTo("USD"))
                .withQueryParam("currencies", equalTo("GBP"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody("""
                                {
                                    "meta": {
                                        "last_updated_at": "2023-06-17T23:59:59Z"
                                    },
                                    "data": {
                                        "GBP": {
                                            "code": "GBP",
                                            "value": 0.9
                                        }
                                    }
                                }
                                """))
        );
        var requestBody = TransactionData.builder()
                .from(fromAccount.getIban())
                .to(toAccount.getIban())
                .amount(new TransactionAmount(200, "USD"))
                .build();
        var query = post("/api/transactions")
                .content(objectMapper.writeValueAsString(requestBody))
                .contentType(MediaType.APPLICATION_JSON);

        var response = mockMvc.perform(query)
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        var responseData = objectMapper.readValue(response, TransactionData.class);
        responseData.setCreatedAt(null);
        requestBody.setUid(responseData.getUid());
        assertEquals(responseData, requestBody);
        assertEquals(accountRepository.findAccountByIban("US1234567890123456789012").orElseThrow().getBalance(), 0);
        assertEquals(accountRepository.findAccountByIban("GB5432109876543210987654").orElseThrow().getBalance(), 100 + 200*0.9);

        Transaction transaction = transactionRepository.findByUid(responseData.getUid()).orElseThrow();
        assertEquals(transaction.getFromAccount().getIban(), responseData.getFrom());
        assertEquals(transaction.getToAccount().getIban(), responseData.getTo());
        assertEquals(transaction.getAmount(), responseData.getAmount().getValue());
    }

    @AfterEach
    public void deleteTables(){
        transactionRepository.deleteAll();
        accountRepository.deleteAll();
        personRepository.deleteAll();
    }
}
