package ithillel.bank.tables.transaction;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionData {
    private String uid;
    private String from;
    private String to;
    private LocalDateTime createdAt;
    private TransactionAmount amount;
}

