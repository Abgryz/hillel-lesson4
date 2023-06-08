package ithillel.bank.tables.person;

import lombok.Builder;

import java.util.List;

@Builder
public record PersonDto(
        String uuid,
        String name,
        List<Long> accounts
) {
}
