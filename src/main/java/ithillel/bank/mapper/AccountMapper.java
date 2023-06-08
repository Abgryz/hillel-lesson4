package ithillel.bank.mapper;

import ithillel.bank.tables.account.Account;
import ithillel.bank.tables.account.AccountDto;
import ithillel.bank.tables.person.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AccountMapper implements Mapper<AccountDto, Account>{
    private final PersonRepository personRepository;

    @Override
    public AccountDto toDto(Account account) {
        return AccountDto.builder()
                .balance(account.getBalance())
                .iban(account.getIban())
                .personUid(account.getPerson().getUid())
                .uid(account.getUid())
                .build();
    }

    @Override
    public Account toEntity(AccountDto accountDto) {
        return Account.builder()
                .uid(accountDto.uid())
                .balance(accountDto.balance())
                .iban(accountDto.iban())
                .person(personRepository.findByUid(accountDto.personUid()).orElseThrow())
                .build();
    }
}
