package ithillel.bank.controllers.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import ithillel.bank.tables.account.Account;
import ithillel.bank.tables.account.AccountDto;
import ithillel.bank.tables.account.AccountRepository;
import ithillel.bank.tables.person.Person;
import ithillel.bank.tables.person.PersonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class AccountControllerIntegrationTest {

    @Autowired
    protected AccountRepository accountRepository;
    @Autowired
    protected PersonRepository personRepository;
    @Autowired
    protected MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();
    private static final List<Person> PERSONS = List.of(
            Person.builder()
                    .id(1L)
                    .name("John Smith")
                    .uid("1f0e3dad99908345f7439f8ffabdffc4")
                    .build(),
            Person.builder()
                    .id(2L)
                    .name("Emily Johnson")
                    .uid("098f6bcd4621d373cade4e832627b4f6")
                    .build(),
            Person.builder()
                    .id(3L)
                    .name("William Brown")
                    .uid("0987f6bcd4621d373cade4e832627b4f6")
                    .build(),
            Person.builder()
                    .id(4L)
                    .name("Emma Davis")
                    .uid("875f9e794323b453885f5181f1b624d0b")
                    .build(),
            Person.builder()
                    .id(5L)
                    .name("Daniel Wilson")
                    .uid("d3d9446802a44259755d38e6d163e820")
                    .build(),
            Person.builder()
                    .id(6L)
                    .name("Olivia Taylor")
                    .uid("6512bd43d9caa6e02c990b0a82652dca")
                    .build(),
            Person.builder()
                    .id(7L)
                    .name("Sophia Miller")
                    .uid("32561bd43d9caa6e02c990b0a82652dca")
                    .build()
    );
    private static final List<Account> ACCOUNTS = List.of(
            Account.builder()
                    .iban("ES1234567890123456789012")
                    .uid("1f0e3dad99908345f7439f8ffabdffc4")
                    .balance(1000.00)
                    .person(PERSONS.get(0))
                    .currency("EUR")
                    .build(),
            Account.builder()
                    .iban("DE9876543210987654321098")
                    .uid("098f6bcd4621d373cade4e832627b4f6")
                    .balance(750.50)
                    .person(PERSONS.get(1))
                    .currency("EUR")
                    .build(),
            Account.builder()
                    .iban("FR6543210987654321098765")
                    .uid("875f9e794323b453885f5181f1b624d0b")
                    .balance(1000.00)
                    .person(PERSONS.get(2))
                    .currency("EUR")
                    .build(),
            Account.builder()
                    .iban("IT9876543210987654321098")
                    .uid("d3d9446802a44259755d38e6d163e820")
                    .balance(1500.25)
                    .person(PERSONS.get(3))
                    .currency("EUR")
                    .build(),
            Account.builder()
                    .iban("GB5432109876543210987654")
                    .uid("6512bd43d9caa6e02c990b0a82652dca")
                    .balance(2000.00)
                    .person(PERSONS.get(4))
                    .currency("GBP")
                    .build(),
            Account.builder()
                    .iban("US2109876543210987654321")
                    .uid("32561bd43d9caa6e02c990b0a82652dca")
                    .balance(300.75)
                    .person(PERSONS.get(0))
                    .currency("EUR")
                    .build()
    );
    private static final AccountDto EXPECTED_DTO =
            AccountDto.builder()
                    .iban("ES1234567890123456789012")
                    .uid("1f0e3dad99908345f7439f8ffabdffc4")
                    .balance(1000.00)
                    .personUid(PERSONS.get(0).getUid())
                    .currency("EUR")
                    .build();

    @BeforeEach
    public void fillDB(){
        personRepository.saveAll(PERSONS);
        accountRepository.saveAll(ACCOUNTS);
    }

    @Test
    public void getContext(){    }

    @Test
    public void shouldGetAll() throws Exception {
        var query = get("/api/accounts");
        mockMvc.perform(query)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(6)));
    }

    @Test
    public void shouldGetByUid() throws Exception {
        var query = get("/api/accounts/1f0e3dad99908345f7439f8ffabdffc4");
        mockMvc.perform(query)
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(EXPECTED_DTO)));
    }

    @Test
    @Transactional
    public void shouldCreateAccount() throws Exception {
        var query = post("/api/accounts")
                .param("personUid", "1f0e3dad99908345f7439f8ffabdffc4")
                .param("iban", "US5432109876543210987654")
                .param("currency", "USD");
        var response = mockMvc.perform(query)
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
        Account expectedAccount = accountRepository.findLatest().orElseThrow();
        assertEquals(response, objectMapper.writeValueAsString(
            AccountDto.builder()
                    .iban(expectedAccount.getIban())
                    .personUid(expectedAccount.getPerson().getUid())
                    .balance(expectedAccount.getBalance())
                    .uid(expectedAccount.getUid())
                    .currency(expectedAccount.getCurrency())
                    .build()
        ));
    }

    @Test
    @Transactional
    public void shouldUpdateAccount() throws Exception {
        var query = put("/api/accounts/1f0e3dad99908345f7439f8ffabdffc4")
                .content(objectMapper.writeValueAsString(
                        AccountDto.builder()
                                .balance(200)
                                .iban(ACCOUNTS.get(0).getIban())
                                .personUid(PERSONS.get(0).getUid())
                                .currency(ACCOUNTS.get(0).getCurrency())
                                .build()
                ))
                .contentType(MediaType.APPLICATION_JSON);
        var response = mockMvc.perform(query)
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
        Account expectedAccount = accountRepository.findAccountByUid("1f0e3dad99908345f7439f8ffabdffc4").orElseThrow();
        assertEquals(response, objectMapper.writeValueAsString(
                AccountDto.builder()
                        .iban(expectedAccount.getIban())
                        .personUid(expectedAccount.getPerson().getUid())
                        .balance(200)
                        .uid(expectedAccount.getUid())
                        .currency(expectedAccount.getCurrency())
                        .build()
        ));
    }

    @Test
    @Transactional
    public void shouldDeleteAccount() throws Exception {
        var query = delete("/api/accounts/1f0e3dad99908345f7439f8ffabdffc4");
        mockMvc.perform(query)
                .andExpect(status().isOk());
        assertNull(accountRepository.findAccountByUid("1f0e3dad99908345f7439f8ffabdffc4").orElse(null));
    }
}