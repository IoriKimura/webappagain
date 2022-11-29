package com.example.webappagain.controllers;

import com.example.webappagain.models.Employee;
import com.example.webappagain.models.Role;
import com.example.webappagain.repository.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

@Controller
public class AuthController {
    @Autowired
    EmployeeRepo eRepo;

    @Autowired
    PasswordEncoder passwordEncoder;

   @GetMapping("/registration")
   public String registration(Model model){
       return "registration";
   }

   @PostMapping("/registration")
    public String registration(Employee employee,
                               Model model){
       Employee employeeFromDb = eRepo.findByEmail(employee.getEmail());
       if(employeeFromDb != null) {
           model.addAttribute("notification", "Такой пользователь уже есть!");
           return "registration";
       }
       else {
           if(employee.getPosition().equals("worker")) {
               employee.setRoles(Collections.singleton(Role.WORKER));
           }
           else {
               employee.setRoles(Collections.singleton(Role.MANAGER));
           }
           employee.setPassword(passwordEncoder.encode(employee.getPassword()));
           eRepo.save(employee);

       }
       return "redirect:/login";
   }

   @GetMapping("/login")
    public String login(Model model){
       return "login";
   }

}
