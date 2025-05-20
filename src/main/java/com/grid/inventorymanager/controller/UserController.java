package com.grid.inventorymanager.controller;

import com.grid.inventorymanager.dto.UserDTO;
import com.grid.inventorymanager.exceptions.EmployeeNotFoundException;
import com.grid.inventorymanager.exceptions.UserNotFoundException;
import com.grid.inventorymanager.model.AssetMovements;
import com.grid.inventorymanager.model.Employee;
import com.grid.inventorymanager.model.User;
import com.grid.inventorymanager.repository.EmployeeRepository;
import com.grid.inventorymanager.repository.UserRepository;
import com.grid.inventorymanager.service.EmployeeService;
import com.grid.inventorymanager.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@RestController
@RequestMapping("/v1/users")
public class UserController {
    private final UserService userService;
    private final EmployeeService employeeService;

    @GetMapping
    public List<User> retrieveAllUsers() {
        return userService.findAll();
    }

    @GetMapping(path = "/{id}")
    public User retrieveOneUser(@PathVariable Long id) {
        return userService.findById(id)
                .orElseThrow(() -> new UserNotFoundException("id: " + id));
    }

    @PostMapping("/{employeeId}")
    public ResponseEntity<User> createUser(@Valid @RequestBody UserDTO userDTO, @PathVariable Long employeeId) {
        Employee employee = employeeService.findById(employeeId).orElseThrow(() -> new EmployeeNotFoundException("id: " + employeeId));

        //conversion de DTO a User
        User user = User.builder()
                .username(userDTO.getUsername())
                .password(userDTO.getPassword())
                .role(userDTO.getRole())
                .build();

        employee.addUser(user);
        employeeService.update(employee);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping(path = "/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteById(id);
    }


}
