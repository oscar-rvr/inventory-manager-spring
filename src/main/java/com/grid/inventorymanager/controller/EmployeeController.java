package com.grid.inventorymanager.controller;

import com.grid.inventorymanager.model.Employee;
import com.grid.inventorymanager.repository.EmployeeRepository;
import com.grid.inventorymanager.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService){
        this.employeeService=employeeService;
    }

    @GetMapping("/employees")
    public String showEmployees(Model model){
        List<Employee> employees= employeeService.findAll();
        model.addAttribute("employees",employees);
        return "employees";
    }
}
