package ru.bank.VillageBank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bank.VillageBank.entity.Account;
import ru.bank.VillageBank.entity.Client;
import ru.bank.VillageBank.entity.TransactionLog;
import ru.bank.VillageBank.repo.AccountRepo;

import java.sql.Timestamp;
import java.util.List;

@Service
public class AccountService {
    private AccountRepo accountRepo;
    private TransactionalLogService transactionalLogService;

    @Autowired
    public void setTransactionalLogService(TransactionalLogService transactionalLogService) {
        this.transactionalLogService = transactionalLogService;
    }

    @Autowired
    public void setAccountRepo(AccountRepo accountRepo) {
        this.accountRepo = accountRepo;
    }

    public Account getById(Long id){
        return accountRepo.getOne(id);
    }

    public Account getByNumber(Integer number){
        return accountRepo.findByNumber(number);
    }

    public List<Account> findAll(){
        return accountRepo.findAll();
    }

    public List<Account> getAccountsByClient(Client client){
        return accountRepo.findAccountsByClient(client);
    }

    @Transactional
    public boolean transaction(Account accountDonor, Account accountRecipient, Integer amount){
        Integer fundDonor = accountDonor.getFund();
        Integer fundRecipient = accountRecipient.getFund();
        if(fundDonor < amount){
            return false;
        }
        fundDonor -=amount;
        fundRecipient +=amount;
        accountDonor.setFund(fundDonor);
        accountRecipient.setFund(fundRecipient);
        TransactionLog transactionLog = new TransactionLog(accountDonor, accountRecipient, amount, new Timestamp(System.currentTimeMillis()));
        try{
            transactionalLogService.saveAndFlush(transactionLog);
        }catch (Exception e){
            e.printStackTrace();
        }
        return true;
    }

    public void saveAndFlush(Account account){
        accountRepo.saveAndFlush(account);
    }

    public boolean deleteAccount(Account account) {
        if(account.getFund() == 0){
            accountRepo.delete(account);
            return true;
        }else{
            return false;
        }
    }

    public Account generateNewAccount(Client client){
        Integer number = (int)(100_000 + Math.random()*899_999);
        while(true){
            if(getByNumber(number) != null){
                number = (int)(100_000 + Math.random()*899_999);
                continue;
            }
            break;
        }

        Account account = new Account(number, 0, client);
        return account;
    };

}
