package com.grid.employees.controller;

import com.grid.employees.dto.EmployeeDTO;
import com.grid.employees.dto.EmployeePatchDTO;
import com.grid.employees.exceptions.EmployeeNotFoundException;
import com.grid.employees.model.Employee;
import com.grid.employees.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping
    public List<Employee> retrieveAllEmployees() {
        return employeeService.findAll();
    }

    @GetMapping(path = "/{id}")
    public Employee retrieveOneEmployee(@PathVariable Long id) {
        return employeeService.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("id: " + id));
    }

    @PostMapping
    public ResponseEntity<Employee> createEmployee(@Valid @RequestBody EmployeeDTO employeeDTO) {

        //conversion de DTO a employee
        Employee employee = Employee.builder()
                .name(employeeDTO.getName())
                .mail(employeeDTO.getMail())
                .build();

        Employee saved = employeeService.create(employee);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(saved.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping(path = "/{id}")
    public void deleteEmployee(@PathVariable Long id) {
        employeeService.deletedById(id);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @Valid @RequestBody EmployeePatchDTO employeePatchDTO) {
        employeeService.update(id, employeePatchDTO);
        return ResponseEntity.ok().build();
    }
}
