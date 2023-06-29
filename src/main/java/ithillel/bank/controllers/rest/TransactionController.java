package ithillel.bank.controllers.rest;

import ithillel.bank.tables.transaction.TransactionData;
import ithillel.bank.tables.transaction.TransactionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/transactions")
public class TransactionController {
    private final TransactionService transactionService;

    @PostMapping
    public TransactionData createTransaction(@RequestBody TransactionData data){
        data = transactionService.createTransaction(data);
        log.info("transaction created: {}", data);
        return data;
    }

    @GetMapping("/{uid}")
    public TransactionData getTransaction(@PathVariable String uid){
        return transactionService.getTransactionByUid(uid);
    }
}
