package com.grid.inventorymanager.service;

import java.util.Optional;
import com.grid.inventorymanager.model.Employee;
import com.grid.inventorymanager.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class EmployeeService{
    private final EmployeeRepository employeeRepository;

    public Employee create(Employee employee){
        return employeeRepository.save(employee);
    }

    public Optional<Employee> findById(Integer id){
        return employeeRepository.findById(id);
    }

    public List<Employee> findAll(){
        return employeeRepository.findAll();

    }

    public Employee update(Employee employee){
        return employeeRepository.save(employee);
    }

    public void deletedById(Integer id){
        employeeRepository.deleteById(id);
    }



}
