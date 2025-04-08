package com.grid.inventorymanager.bootstrap;


import com.grid.inventorymanager.model.Employee;
import com.grid.inventorymanager.model.Role;
import com.grid.inventorymanager.model.User;
import com.grid.inventorymanager.repository.EmployeeRepository;
import com.grid.inventorymanager.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import com.grid.inventorymanager.service.EmployeeService;
@Component
public class BootStrapData implements CommandLineRunner {

    private final EmployeeRepository employeeRepository;
private final UserRepository userRepository;
    public BootStrapData(EmployeeRepository employeeRepository, UserRepository userRepository){
        this.employeeRepository=employeeRepository;
        this.userRepository=userRepository;
    }

    @Override
    public void run(String... args){
        //crear un empleado
        Employee emp = Employee.builder().name("Daniel").mail("danieqqqqAASDAl@ti2.com").build();
        Employee saved = employeeService.create(emp);
        System.out.println("EMpleado guardado " + saved);

    }



}
