package ithillel.bank.tables.account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findAccountByUid(String uid);
    Optional<Account> findAccountByIban(String iban);
    void deleteAccountByUid(String uid);

    @Query(value = """
        select * from account
        order by id desc
        limit 1
    """, nativeQuery = true)
    Optional<Account> findLatest();
}
