package com.example.webappagain.controllers;

import com.example.webappagain.models.Contracts;
import com.example.webappagain.models.Employee;
import com.example.webappagain.models.Role;
import com.example.webappagain.models.Tasks;
import com.example.webappagain.repository.ContractRepo;
import com.example.webappagain.repository.EmployeeRepo;
import com.example.webappagain.repository.TasksRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@PreAuthorize("hasAnyAuthority('MANAGER', 'WORKER')")
public class ContractController {
    @Autowired
    ContractRepo cRepo;
    @Autowired
    EmployeeRepo eRepo;
    @Autowired
    TasksRepo tRepo;

    @GetMapping("/contracts")
    public String show(Model model, Authentication auth){
        String workerEmail = auth.getName();
        Employee worker = eRepo.findByEmail(workerEmail);
        List<Tasks> workerTasks = null;
        ArrayList<Contracts> contractsList = new ArrayList<>();

        if(auth.getAuthorities().contains(Role.MANAGER)) {
            workerTasks = tRepo.findByAuthor(worker.getEmployeeId());

        }
        else {
            workerTasks = tRepo.findByExecutor(worker.getEmployeeId());
        }

        if(workerTasks.isEmpty()) {
            model.addAttribute("notification", "Контрактов нет!");
        }
        else {
            ArrayList<Contracts> temp = new ArrayList<>();
            for(int i = 0; i < workerTasks.size(); i++){
                Long taskID = workerTasks.get(i).getTask_id();
                if(cRepo.findByTaskId(taskID) == null)
                    continue;
                else
                    contractsList.add(cRepo.findByTaskId(taskID));
            }
            if(contractsList.isEmpty()) {
                model.addAttribute("notification", "Контрактов нет!");
            }
            else
                model.addAttribute("contracts", contractsList);
        }
        return "contracts";
    }
}
