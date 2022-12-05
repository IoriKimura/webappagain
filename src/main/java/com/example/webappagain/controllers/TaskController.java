package com.example.webappagain.controllers;


import com.example.webappagain.models.Employee;
import com.example.webappagain.models.Role;
import com.example.webappagain.models.Tasks;
import com.example.webappagain.repository.EmployeeRepo;
import com.example.webappagain.repository.TasksRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@PreAuthorize("hasAnyAuthority('MANAGER', 'WORKER')")
public class TaskController {
//ToDo Сделать репазиторий с заданиям и с контрактами.
//ToDo Проверка логина пользователя. Нахождение его в БД и доставление его индекса.
    @Autowired
    EmployeeRepo eRepo;

    @Autowired
    TasksRepo tRepo;

    Authentication auth;

    @GetMapping("/tasks")
    public String showTasks(Model model, Authentication auth){
        this.auth = auth;
        String workerEmail = auth.getName();
        Employee worker = eRepo.findByEmail(workerEmail);
        List<Tasks> workerTasks = null;

        if(auth.getAuthorities().contains(Role.MANAGER)) {
            workerTasks = tRepo.findByAuthor(worker.getEmployeeId());

        }
        else {
            workerTasks = tRepo.findByExecutor(worker.getEmployeeId());
        }
        if(workerTasks.isEmpty())
            model.addAttribute("notification", "Ура, никаких задач пока что нет!");
        else
            model.addAttribute("tasks", workerTasks);
        return "tasks";
    }
}