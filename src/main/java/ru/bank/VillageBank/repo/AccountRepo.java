package ru.bank.VillageBank.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.bank.VillageBank.entity.Account;
import ru.bank.VillageBank.entity.Client;

import java.util.List;

@Repository
public interface AccountRepo extends JpaRepository<Account, Long> {

    @Query("SELECT a from Account a WHERE a.number = :number")
    Account findByNumber(Integer number);

    List<Account> findAccountsByClient(Client client);

}
