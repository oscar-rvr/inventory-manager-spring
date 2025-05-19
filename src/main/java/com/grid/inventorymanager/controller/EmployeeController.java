package com.grid.inventorymanager.controller;

import com.grid.inventorymanager.exceptions.EmployeeNotFoundException;
import com.grid.inventorymanager.model.AssetMovements;
import com.grid.inventorymanager.model.Employee;
import com.grid.inventorymanager.repository.EmployeeRepository;
import com.grid.inventorymanager.service.EmployeeService;
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
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
        Employee saved = employeeService.create(employee);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(saved.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping(path = "/{id}")
    public void deleteEmployee(@PathVariable Long id) {
        employeeService.deletedById(id);
    }

    @GetMapping(path = "/{id}/movements")
    public Set<AssetMovements> retrieveAll(@PathVariable Long id) {
        return employeeService.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("id: " + id))
                .getAssets();
    }
}
