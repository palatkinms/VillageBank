package ru.bank.VillageBank.repo;

import org.hibernate.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.bank.VillageBank.entity.Account;
import ru.bank.VillageBank.entity.TransactionLog;

import java.util.List;

@Repository
public interface TransactionLogRepo extends JpaRepository<TransactionLog, Long> {

    List<TransactionLog> findAllTransactionByAccountDonor(Account account);

    List<TransactionLog> findAllTransactionByAccountRecipient(Account account);
    
}
