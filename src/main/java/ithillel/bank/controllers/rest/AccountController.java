package ithillel.bank.controllers.rest;

import ithillel.bank.tables.account.AccountDto;
import ithillel.bank.tables.account.AccountService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class AccountController {
    private final AccountService accountService;

    @GetMapping("/accounts")
    public List<AccountDto> getAll(){
        return accountService.getAll();
    }

    @GetMapping("/accounts/{uid}")
    public AccountDto getAccount(@PathVariable String uid){
        return accountService.getByUid(uid);
    }

    @PostMapping("/accounts")
    @Transactional
    public AccountDto create(@RequestParam String personUid, @RequestParam String iban){
        return accountService.create(iban, personUid);
    }

    @PutMapping("/accounts/{uid}")
    @Transactional
    public AccountDto update(@RequestBody AccountDto accountDto, @PathVariable String uid){
        return accountService.update(
                accountDto.toBuilder()
                        .uid(uid)
                        .build());
    }

    @DeleteMapping("/accounts/{uid}")
    @Transactional
    public void delete(@PathVariable String uid){
        accountService.deleteByUid(uid);
    }
}
