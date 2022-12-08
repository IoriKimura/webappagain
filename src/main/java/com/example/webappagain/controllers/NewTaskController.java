package com.example.webappagain.controllers;

import com.example.webappagain.models.Clients;
import com.example.webappagain.models.Contracts;
import com.example.webappagain.models.Employee;
import com.example.webappagain.models.Tasks;
import com.example.webappagain.repository.ClientsRepo;
import com.example.webappagain.repository.ContractRepo;
import com.example.webappagain.repository.EmployeeRepo;
import com.example.webappagain.repository.TasksRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@PreAuthorize("hasAuthority('MANAGER')")
public class NewTaskController {

    @Autowired
    ClientsRepo clientsRepo;
    @Autowired
    EmployeeRepo employeeRepo;
    @Autowired
    TasksRepo tasksRepo;

    @Autowired
    ContractRepo contractRepo;

    @GetMapping("/newTask")
    @PreAuthorize("hasAuthority('MANAGER')")
    public String showPage(Model model){
        Iterable<Clients> clients = clientsRepo.findAll();
        Iterable<Employee> employees = employeeRepo.findAllWorkers();
        model.addAttribute("customers", clients);
        model.addAttribute("workers", employees);
        return "newTask";
    }

    @PostMapping("add")
    @PreAuthorize("hasAuthority('MANAGER')")
    public String add(Model model, String customerID,
                      String workerID,
                      String goal,
                      String priority,
                      String deadline,
                      String equipmentID, Authentication auth){
        if(customerID.equals("Выберите заказчика") || workerID.equals("Выберите сотрудника")) {
            model.addAttribute("alert", "Проверьте данные");
            return "newTask";
        }
        String workerEmail = auth.getName();
        Employee worker = employeeRepo.findByEmail(workerEmail);
        Tasks newTask = new Tasks(Long.parseLong(customerID), worker.getEmployeeId(), Long.parseLong(workerID),
                goal, priority, deadline);

        if(!equipmentID.isEmpty()) {
            Contracts contract = new Contracts(tasksRepo.save(newTask).getTask_id(), equipmentID);
            contractRepo.save(contract);
        }
        else
            tasksRepo.save(newTask);
        return "redirect:/tasks";
    }
}
