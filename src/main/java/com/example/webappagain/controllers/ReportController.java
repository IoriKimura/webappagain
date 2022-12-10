//package com.example.webappagain.controllers;
//
//
//import com.example.webappagain.models.Employee;
//import com.example.webappagain.models.Tasks;
//import com.example.webappagain.repository.EmployeeRepo;
//import com.example.webappagain.repository.TasksRepo;
//import com.google.gson.Gson;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//
//@RestController
//public class ReportController {
//
//    @Autowired
//    TasksRepo tRepo;
//
//    @Autowired
//    EmployeeRepo eRepo;
//
//
//    @PostMapping("download")
//    public String getReport(Model model, Long employeeId, String start, String end){
//        List<Tasks> tasks = tRepo.findAll();
//        String json = new Gson().toJson(tasks);
//        model.addAttribute("json", json) ;
//        return json;
//    }
//}
