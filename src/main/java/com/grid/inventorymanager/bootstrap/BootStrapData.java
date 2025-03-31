package com.grid.inventorymanager.bootstrap;


import com.grid.inventorymanager.model.Employee;
import com.grid.inventorymanager.repository.EmployeeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import com.grid.inventorymanager.service.EmployeeService;
@Component
public class BootStrapData implements CommandLineRunner {

    private final EmployeeRepository employeeRepository;

    public BootStrapData(EmployeeRepository employeeRepository){
        this.employeeRepository=employeeRepository;
    }

    @Override
    public void run(String... args){
        //crear un empleado
        Employee emp = Employee.builder().name("Daniel").mail("daniel@ti2.com").build();
        Employee saved = employeeRepository.save(emp);
        System.out.println("EMpleado guardado " + saved);

    }



}
