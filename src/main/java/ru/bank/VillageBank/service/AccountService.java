package ru.bank.VillageBank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bank.VillageBank.entity.Account;
import ru.bank.VillageBank.entity.Client;
import ru.bank.VillageBank.repo.AccountRepo;

import java.util.List;

@Service
public class AccountService {
    private AccountRepo accountRepo;

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

        return true;
    }

}
