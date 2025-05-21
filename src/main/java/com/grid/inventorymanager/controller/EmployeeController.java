package com.grid.inventorymanager.controller;

import com.grid.inventorymanager.dto.EmployeeDTO;
import com.grid.inventorymanager.dto.EmployeePatchDTO;
import com.grid.inventorymanager.exceptions.EmployeeNotFoundException;
import com.grid.inventorymanager.model.AssetMovements;
import com.grid.inventorymanager.model.Employee;
import com.grid.inventorymanager.model.User;
import com.grid.inventorymanager.repository.EmployeeRepository;
import com.grid.inventorymanager.service.EmployeeService;
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

    @GetMapping(path = "/{id}/movements")
    public Set<AssetMovements> retrieveAll(@PathVariable Long id) {
        return employeeService.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("id: " + id))
                .getAssets();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @Valid @RequestBody EmployeePatchDTO employeePatchDTO) {
       employeeService.update(id, employeePatchDTO);
       return ResponseEntity.ok().build();
    }


}
