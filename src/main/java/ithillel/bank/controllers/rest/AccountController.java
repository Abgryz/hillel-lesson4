package ithillel.bank.controllers.rest;

import ithillel.bank.tables.account.AccountDto;
import ithillel.bank.tables.account.AccountService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class AccountController {
    private final AccountService accountService;

    @GetMapping("/accounts")
    public List<AccountDto> getAll(){
        log.info("getAllAccounts");
        return accountService.getAll();
    }

    @GetMapping("/accounts/{uid}")
    public AccountDto getAccount(@PathVariable String uid){
        log.info("getAccount: uid={}", uid);
        return accountService.getByUid(uid);
    }

    @PostMapping("/accounts")
    @Transactional
    public AccountDto create(@RequestParam String personUid, @RequestParam String iban, @RequestParam String currency){
        log.info("createAccount: personUid={}, iban={}, currency={}", personUid, iban, currency);
        return accountService.create(iban, personUid, currency);
    }

    @PutMapping("/accounts/{uid}")
    @Transactional
    public AccountDto update(@RequestBody AccountDto accountDto, @PathVariable String uid){
        log.info("updateAccount: accountDto={}, uid={}", accountDto, uid);
        return accountService.update(
                accountDto.toBuilder()
                        .uid(uid)
                        .build());
    }

    @DeleteMapping("/accounts/{uid}")
    @Transactional
    public void delete(@PathVariable String uid){
        log.info("deleteAccount: uid={}", uid);
        accountService.deleteByUid(uid);
    }
}
