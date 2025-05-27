package com.grid.inventorymanager.controller;

import com.grid.inventorymanager.dto.PagedResponse;
import com.grid.inventorymanager.dto.UserDTO;
import com.grid.inventorymanager.exceptions.EmployeeNotFoundException;
import com.grid.inventorymanager.exceptions.UserNotFoundException;
import com.grid.inventorymanager.model.AssetMovements;
import com.grid.inventorymanager.model.Employee;
import com.grid.inventorymanager.model.User;
import com.grid.inventorymanager.model.Vendor;
import com.grid.inventorymanager.repository.EmployeeRepository;
import com.grid.inventorymanager.repository.UserRepository;
import com.grid.inventorymanager.service.EmployeeService;
import com.grid.inventorymanager.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    public ResponseEntity<PagedResponse<UserDTO>> retrieveAllUsers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "username") String sortBy,
            @RequestParam(defaultValue = "asc") String direction) {

        Sort sort = direction.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);
        Page<User> usersPage = userService.findAll(pageable);


        List<UserDTO> content = usersPage.getContent().stream()
                .map(user -> {
                    UserDTO dto = new UserDTO();
                    dto.setUsername(user.getUsername());
                    dto.setPassword(user.getPassword());
                    dto.setRole(user.getRole());
                    return dto;
                })
                .toList();

        PagedResponse<UserDTO> response = new PagedResponse<>(
                content,
                usersPage.getNumber(),
                usersPage.getSize(),
                usersPage.getTotalElements(),
                usersPage.getTotalPages(),
                usersPage.isLast()
        );

        return ResponseEntity.ok(response); // retorno corregido
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


    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @Valid @RequestBody UserDTO userDTO) {

        User updatedUser = userService.update(id, userDTO);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteById(id);
    }


}