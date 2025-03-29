package com.grid.inventorymanager.service;

import com.grid.inventorymanager.model.Employee;
import com.grid.inventorymanager.repository.EmployeeRepository;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    private EmployeeRepository(EmployeeRepository employeeRepository){
        this.employeeRepository=employeeRepository;
    }

    public Employee create(Employee employee){
        return employeeRepository.save(employee);
    }

    public Optional<Employee> findBy

}
