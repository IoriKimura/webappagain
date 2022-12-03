package com.example.webappagain.controllers;

import com.example.webappagain.models.Clients;
import com.example.webappagain.repository.ClientsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@PreAuthorize("hasAnyAuthority('MANAGER')")
public class ClientsController{
    @Autowired
    ClientsRepo cRepo;

    @GetMapping("/clients")
    public String show(Model model){
        this.cRepo = cRepo;
        List<Clients> clients = cRepo.findAll();
        model.addAttribute("clients", clients);

        return "clients";
    }
}
