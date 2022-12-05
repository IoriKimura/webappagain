package com.example.webappagain.controllers;

import com.example.webappagain.models.Employee;
import com.example.webappagain.repository.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@PreAuthorize("hasAuthority('MANAGER')")
public class WorkerController {
    @Autowired
    EmployeeRepo eRepo;

    @GetMapping("/workers")
    public String show(Model model){
        List<Employee> employee = eRepo.findAll();

        model.addAttribute("workers", employee);
        return "workers";
    }
}
