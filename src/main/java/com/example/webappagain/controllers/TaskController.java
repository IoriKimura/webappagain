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
import org.springframework.web.bind.annotation.PostMapping;

import java.sql.Timestamp;
import java.util.List;

@Controller
@PreAuthorize("hasAnyAuthority('MANAGER', 'WORKER')")
public class TaskController {
    @Autowired
    EmployeeRepo eRepo;

    @Autowired
    TasksRepo tRepo;
    @Autowired
    ContractRepo cRepo;

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

    @PostMapping("complete")
    @PreAuthorize("hasAuthority('WORKER')")
    public String complete(Model model, Long taskID, Authentication auth){
        this.auth = auth;
        String workerEmail = auth.getName();
        Employee worker = eRepo.findByEmail(workerEmail);
        List<Tasks> workerTasks = null;
        if(taskID == null)
            model.addAttribute("alert", "Не указан номер задачи");
        else{
            Tasks task = tRepo.findTaskByTaskIDExecutorID(taskID, worker.getEmployeeId());
            if(task != null){
                task.setFinaltime(new Timestamp(System.currentTimeMillis()));
                tRepo.save(task);
            }
            else
                model.addAttribute("alert", "Такой задачи не существует или эта задача не ваша");
        }

        if(this.auth.getAuthorities().contains(Role.MANAGER)) {
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

    @GetMapping("editing")
    @PreAuthorize("hasAuthority('MANAGER')")
    public String edit(Model model, Long taskID, Authentication auth){
        if(taskID == null) {
            return "redirect:/tasks";
        }
        this.auth = auth;
        String workerEmail = auth.getName();
        Employee worker = eRepo.findByEmail(workerEmail);
        Tasks task = tRepo.findByTaskIDAuthorID(taskID, worker.getEmployeeId());
        Contracts contract = cRepo.findByTaskId(taskID);
        if(task == null) {
            return "redirect:/tasks";
        }
        model.addAttribute("contract", contract);
        model.addAttribute("task", task);
        return "editing";
    }

    @PostMapping("saved")
    @PreAuthorize("hasAuthority('MANAGER')")
    public String saving(Model model, Long task_id,
                          Long customer_id, Long author_id,
                          Long executor_id,
                          String goal,
                          String priority,
                         String deadline,
                         String finaltime,
                         String equipmentID, Authentication auth){
        this.auth = auth;
        if(customer_id == null)
            return "redirect:/tasks";
        String workerEmail = auth.getName();
        Employee worker = eRepo.findByEmail(workerEmail);
        Tasks taskFromDb = tRepo.findByTaskIDAuthorID(task_id, worker.getEmployeeId());
        Contracts contractsFromDb = cRepo.findByTaskId(task_id);
        taskFromDb.setCustomer_id(customer_id);
        taskFromDb.setAuthor_id(author_id);
        taskFromDb.setExecutor_id(executor_id);
        taskFromDb.setGoal(goal);
        taskFromDb.setPriority(priority);
        if(!equipmentID.equals(contractsFromDb.getEquipment_id()))
            contractsFromDb.setEquipment_id(equipmentID);
        if(!deadline.isEmpty()) {
            taskFromDb.setDeadline(Timestamp.valueOf(deadline.replace('T', ' ') + ":00"));
        }
        if(!finaltime.isEmpty())
            taskFromDb.setFinaltime(Timestamp.valueOf(finaltime.replace('T', ' ') + "00"));
        tRepo.save(taskFromDb);
        cRepo.save(contractsFromDb);
        return "redirect:/tasks";
    }
}
