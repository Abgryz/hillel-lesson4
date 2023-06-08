package ithillel.bank.tables.account;

import ithillel.bank.tables.person.Person;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String uid;

    private String iban;

    private double balance;

    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;
}
