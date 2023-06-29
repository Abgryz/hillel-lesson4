package ithillel.bank.tables.transaction;

import ithillel.bank.currency.converter.CurrencyConverter;
import ithillel.bank.currency.model.exception.ValidationException;
import ithillel.bank.tables.account.Account;
import ithillel.bank.tables.account.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Currency;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TransactionService {
    private final AccountRepository accountRepository;
    private final CurrencyConverter currencyConverter;
    private final TransactionRepository transactionRepository;

    @Transactional
    public TransactionData createTransaction(TransactionData data){
        Account to = accountRepository.findAccountByIban(data.getTo()).orElseThrow();
        Account from = accountRepository.findAccountByIban(data.getFrom()).orElseThrow();
        TransactionAmount amount = data.getAmount();

        if (!Objects.equals(amount.getCurrency(), from.getCurrency())){
            throw new ValidationException("Currencies don`t match!");
        }
        if (from.getBalance() < amount.getValue()){
            throw new ValidationException("Not enough balance in account!");
        }

        double convertedValue;
        if (!Objects.equals(from.getCurrency(), to.getCurrency())){
            convertedValue = currencyConverter.convert(Currency.getInstance(from.getCurrency()), Currency.getInstance(to.getCurrency()), amount.getValue());
        } else {
            convertedValue = amount.getValue();
        }

        from.setBalance(from.getBalance() - amount.getValue());
        accountRepository.save(from);

        to.setBalance(to.getBalance() + convertedValue);
        accountRepository.save(to);

        Transaction transaction = transactionRepository.saveAndFlush(Transaction.builder()
                        .amount(data.getAmount().getValue())
                        .currency(data.getAmount().getCurrency())
                        .uid(UUID.randomUUID().toString().replace("-", ""))
                        .toAccount(to)
                        .fromAccount(from)
                .build());
        data.setCreatedAt(transaction.getCreatedAt());
        data.setUid(transaction.getUid());
        return data;
    }

    public TransactionData getTransactionByUid(String uid){
        Transaction transaction = transactionRepository.findByUid(uid).orElseThrow();
        return TransactionData.builder()
                .createdAt(transaction.getCreatedAt())
                .uid(transaction.getUid())
                .to(transaction.getToAccount().getIban())
                .from(transaction.getFromAccount().getIban())
                .amount(new TransactionAmount(transaction.getAmount(), transaction.getCurrency()))
                .build();
    }
}
