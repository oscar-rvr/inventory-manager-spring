package com.grid.inventorymanager.bootstrap;

import com.grid.inventorymanager.model.Employee;
import com.grid.inventorymanager.model.Role;
import com.grid.inventorymanager.model.User;
import com.grid.inventorymanager.repository.EmployeeRepository;
import com.grid.inventorymanager.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import com.grid.inventorymanager.service.EmployeeService;
@Component
@RequiredArgsConstructor
public class BootStrapData implements CommandLineRunner {

    private final EmployeeService employeeService;

    @Override
    public void run(String... args){
        //crear un empleado
        Employee emp = Employee.builder().name("Daniel").mail("testestoggkkkkss2@testeo").build();
        Employee saved = employeeService.create(emp);
        System.out.println("Empleado guardado " + saved);

    }

}
