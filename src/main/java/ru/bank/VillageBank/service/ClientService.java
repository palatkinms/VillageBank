package ru.bank.VillageBank.service;    

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.bank.VillageBank.entity.Account;
import ru.bank.VillageBank.entity.Client;
import ru.bank.VillageBank.repo.ClientRepo;

import java.util.List;

@Service
public class ClientService {
    private ClientRepo clientRepo;

    @Autowired
    public void setClientRepo(ClientRepo clientRepo) {
        this.clientRepo = clientRepo;
    }

    public Client getById(Long id){
        return clientRepo.getOne(id);
    }


    public List<Client> getAllClients(){
        return clientRepo.findAll();
    }


    public void saveAndFlush(Client client){
        clientRepo.saveAndFlush(client);
    }


}
