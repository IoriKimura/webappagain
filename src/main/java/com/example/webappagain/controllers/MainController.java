package com.example.webappagain.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@PreAuthorize("hasAnyAuthority('WORKER', 'MANAGER')")
public class MainController {
    @GetMapping("/")
    public String main(Model model){
        return "main";
    }
}
