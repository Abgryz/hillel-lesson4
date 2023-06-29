package ithillel.bank.tables.account;

import ithillel.bank.mapper.AccountMapper;
import ithillel.bank.tables.person.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;
    private final PersonRepository personRepository;
    private final AccountMapper accountMapper;

    public AccountDto getByUid(String uid){
        return accountRepository.findAccountByUid(uid)
                .map(accountMapper::toDto)
                .orElse(null);
    }

    public List<AccountDto> getAll(){
        return accountMapper.toDto(accountRepository.findAll());
    }

    public AccountDto create(String iban, String personUid, String currency){
        String uid = UUID.randomUUID().toString().replace("-", "");
        return accountMapper.toDto(accountRepository.saveAndFlush(
                Account.builder()
                        .iban(iban)
                        .person(personRepository.findByUid(personUid).orElseThrow())
                        .uid(uid)
                        .currency(currency)
                        .balance(0)
                        .build()
        ));
    }

    public AccountDto update(AccountDto accountDto){
        return accountMapper.toDto(accountRepository.saveAndFlush(
                accountRepository.findAccountByUid(accountDto.uid()).orElseThrow().toBuilder()
                        .balance(accountDto.balance())
                        .iban(accountDto.iban())
                        .person(personRepository.findByUid(accountDto.personUid()).orElseThrow())
                        .currency(accountDto.currency())
                        .build())
        );
    }

    public void deleteByUid(String uid){
        accountRepository.deleteAccountByUid(uid);
    }
}
