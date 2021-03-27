package ru.bank.VillageBank.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.bank.VillageBank.entity.Account;
import ru.bank.VillageBank.entity.Client;

import java.util.List;

@Repository
public interface ClientRepo extends JpaRepository<Client, Long> {



}
