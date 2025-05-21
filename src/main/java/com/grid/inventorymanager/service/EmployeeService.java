package com.grid.inventorymanager.service;

import com.grid.inventorymanager.dto.EmployeePatchDTO;
import com.grid.inventorymanager.exceptions.EmployeeNotFoundException;
import com.grid.inventorymanager.model.Employee;
import com.grid.inventorymanager.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public Employee create(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Optional<Employee> findById(Long id) {
        return employeeRepository.findById(id);
    }

    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    public Employee update(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Employee update(Long id, EmployeePatchDTO employeePatchDTO) {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new EmployeeNotFoundException("id : "+id));

        if(employeePatchDTO.getMail() != null) {
            employee.setMail(employeePatchDTO.getMail());
        }

        if(employeePatchDTO.getName() != null) {
            employee.setName(employeePatchDTO.getName());
        }

        return employeeRepository.save(employee);
    }

    public void deletedById(Long id) {
        employeeRepository.deleteById(id);
    }
}
