package ru.bank.VillageBank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.bank.VillageBank.entity.Account;
import ru.bank.VillageBank.entity.Client;
import ru.bank.VillageBank.entity.TransactionLog;
import ru.bank.VillageBank.service.AccountService;
import ru.bank.VillageBank.service.ClientService;
import ru.bank.VillageBank.service.TransactionalLogService;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/client")
public class ClientController {
    private ClientService clientService;
    private AccountService accountService;
    private TransactionalLogService transactionalLogService;

    @Autowired
    public void setTransactionalLogService(TransactionalLogService transactionalLogService) {
        this.transactionalLogService = transactionalLogService;
    }

    @Autowired
    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }

    @Autowired
    public void setClientService(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    public String ourClient(Model model){
        List<Client> clientList = clientService.getAllClients();
        model.addAttribute(clientList);
        return "our-client";
    }

    @GetMapping("/new")
    public String showFormAddNewClient(Model model){
        Client client = new Client();
        model.addAttribute(client);
        return "new-clients-form";
    }

    @PostMapping("/new")
    public String addNewClient(@ModelAttribute(value = "client") Client client){
        Client clientResult = new Client(client.getName(), client.getLastname());
        clientService.saveAndFlush(clientResult);
        return "redirect:/";
    }

    @GetMapping("/{id}")
    public String detailClient(Model model, @PathVariable(value = "id") Long id){
        Client client = clientService.getById(id);
        List<Account> accountList = accountService.getAccountsByClient(client).stream().sorted(Comparator.comparingInt(Account::getNumber)).collect(Collectors.toList());
        model.addAttribute(client);
        model.addAttribute(accountList);
        return "client-detail";
    }

    @PostMapping("/transfer")
    public String transferMoney(@ModelAttribute(value = "moneyDonor")Long id,
    @ModelAttribute(value = "moneyRecipient") Integer number,
    @ModelAttribute(value = "amountTransfer") Integer amount){
        Account accountDonor = accountService.getById(id);
        Account accountRecipient = accountService.getByNumber(number);
        accountService.transaction(accountDonor, accountRecipient, amount);
        return "redirect:/client/"+accountDonor.getClient().getId();
    }

    @GetMapping("/{id}/show")
    public String accountDetail(Model model, @PathVariable(value = "id")Long id){
        Client client = clientService.getById(id);
        List<Account> accountList = accountService.getAccountsByClient(client).stream().sorted(Comparator.comparingInt(Account::getNumber)).collect(Collectors.toList());
        model.addAttribute(client);
        model.addAttribute(accountList);
        return "account-detail";
    }

    @GetMapping("/{id}/new")
    public String addNewAccount(Model model, @PathVariable(value = "id")Long id){
        Client client = clientService.getById(id);
        Account account = accountService.generateNewAccount(client);
        accountService.saveAndFlush(account);
        List<Account> accountList = accountService.getAccountsByClient(client);
        model.addAttribute(client);
        model.addAttribute(accountList);
        return "account-detail";
    }

    @GetMapping("/account/{number}")
    public String showAllTransaction(Model model, @PathVariable(value = "number")Integer number){

        Account account = accountService.getByNumber(number);
        List<TransactionLog> transactionLogs = transactionalLogService.getAllTransaction(account);
        transactionLogs = transactionLogs.stream().sorted().collect(Collectors.toList());

        model.addAttribute(account);
        model.addAttribute(transactionLogs);

        return "transaction-detail";
    }

    @GetMapping("/{id}/show/{number}/del")
    public String accountDetail(Model model, @PathVariable(value = "id")Long id,
                                @PathVariable(value = "number")Integer number){
        Client client = clientService.getById(id);
        Account account = accountService.getByNumber(number);
        String message = "";
        if(!accountService.deleteAccount(account)){
            message = "To delete, your account must be empty!";
        }

        List<Account> accountList = accountService.getAccountsByClient(client).stream().sorted(Comparator.comparingInt(Account::getNumber)).collect(Collectors.toList());
        model.addAttribute(message);
        model.addAttribute(client);
        model.addAttribute(accountList);

        return "account-detail";
    }






}
