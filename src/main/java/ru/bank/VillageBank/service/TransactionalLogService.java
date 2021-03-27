package ru.bank.VillageBank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.bank.VillageBank.entity.Account;
import ru.bank.VillageBank.entity.TransactionLog;
import ru.bank.VillageBank.repo.TransactionLogRepo;

import java.util.List;


@Service
public class TransactionalLogService {
    private TransactionLogRepo transactionLogRepo;

    @Autowired
    public void setTransactionLogRepo(TransactionLogRepo transactionLogRepo) {
        this.transactionLogRepo = transactionLogRepo;
    }

    public TransactionLog getById(Long id){
        return transactionLogRepo.getOne(id);
    }

    public List<TransactionLog> getAllTransactionByAccountDonor(Account account){
        return transactionLogRepo.findAllTransactionByAccountDonor(account);
    }

    public List<TransactionLog> getAllTransactionByAccountRecipient(Account account){
        return transactionLogRepo.findAllTransactionByAccountRecipient(account);
    }

    public List<TransactionLog> getAllTransaction(Account account){
        List<TransactionLog> transactionLogList = getAllTransactionByAccountDonor(account);
        int temp = 0;
        for(int i = 0; i < transactionLogList.size(); i++){
            temp = transactionLogList.get(i).getFund();
            transactionLogList.get(i).setFund(-temp);
        }
        transactionLogList.addAll(getAllTransactionByAccountRecipient(account));
        return transactionLogList;
    }

    public void saveAndFlush(TransactionLog transactionLog){
        transactionLogRepo.saveAndFlush(transactionLog);
    }


}
