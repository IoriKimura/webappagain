package com.example.webappagain.controllers;

import com.example.webappagain.models.Employee;
import com.example.webappagain.repository.EmployeeRepo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    EmployeeRepo eRepo;

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
       else
           eRepo.save(employee);
       return "redirect:/login";
   }

   @GetMapping("/login")
    public String login(Model model){
       return "login";
   }

}
