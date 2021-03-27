package ru.bank.VillageBank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.bank.VillageBank.entity.Client;
import ru.bank.VillageBank.service.ClientService;

import java.util.List;

@Controller
@RequestMapping("/")
public class MainController {
    private ClientService clientService;

    @Autowired
    public void setClientService(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/")
    public String index(Model model){
        return "index";
    }



}
