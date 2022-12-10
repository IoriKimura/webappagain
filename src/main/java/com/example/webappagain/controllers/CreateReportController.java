package com.example.webappagain.controllers;


import com.example.webappagain.component.TaskReport;
import com.example.webappagain.models.Employee;
import com.example.webappagain.repository.EmployeeRepo;
import com.example.webappagain.repository.TasksRepo;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;

@Controller
@PreAuthorize("hasAuthority('MANAGER')")
public class CreateReportController {

    @Autowired
    EmployeeRepo eRepo;

    @Autowired
    TasksRepo tRepo;

    @RequestMapping("/report")
    public String showPage(Model model){
        Iterable<Employee> employees = eRepo.findAllWorkers();
        model.addAttribute("workers", employees);
        return "taskReport";
    }

    @RequestMapping(path = "/send/", method = RequestMethod.GET)
    @ResponseBody
    public String sending(@RequestParam(name = "workerID") String workerID,
                          @RequestParam(name = "startDate") String startDate,
                          @RequestParam(name = "endDate") String endDate){
        Integer worker = Integer.parseInt(workerID);
        TaskReport taskReport = new TaskReport();
        int cAllT = tRepo.getAllTasks(worker,
                Timestamp.valueOf(startDate.replace('T', ' ')+":00"),
                Timestamp.valueOf(endDate.replace('T', ' ')+":00"));
        String json = new Gson().toJson(cAllT);
        return json;
    }
}
