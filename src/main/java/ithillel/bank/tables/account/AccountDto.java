package ithillel.bank.tables.account;

import lombok.Builder;

@Builder(toBuilder = true)
public record AccountDto(
        String uid,
        String iban,
        double balance,
        String personUid
) {
}
