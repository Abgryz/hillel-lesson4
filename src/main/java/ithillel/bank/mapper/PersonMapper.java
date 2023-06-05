package ithillel.bank.mapper;

import ithillel.bank.tables.account.Account;
import ithillel.bank.tables.account.AccountRepository;
import ithillel.bank.tables.person.Person;
import ithillel.bank.tables.person.PersonDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class PersonMapper implements Mapper<PersonDto, Person>{
    private final AccountRepository accountRepository;

    @Override
    public PersonDto toDto(Person person) {
        return PersonDto.builder()
                .name(person.getName())
                .accounts(person.getAccounts().stream()
                        .map(Account::getId)
                        .toList())
                .uuid(person.getUid())
                .build();
    }

    @Override
    public Person toEntity(PersonDto personDto) {
        return Person.builder()
                .uid(personDto.uuid())
                .name(personDto.name())
                .updatedAt(LocalDateTime.now())
                .accounts(personDto.accounts().stream()
                        .map(accountId -> accountRepository.findById(accountId).orElseThrow())
                        .toList())
                .build();
    }
}
