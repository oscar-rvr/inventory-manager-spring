package com.grid.users.controller;


import com.grid.common.dto.PagedResponse;
import com.grid.users.dto.UserDTO;
import com.grid.users.exceptions.UserNotFoundException;
import com.grid.users.model.Role;
import com.grid.users.model.User;
import com.grid.users.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/v1/users")
public class UserController {
    private final UserService userService;

    @GetMapping
    public ResponseEntity<PagedResponse<UserDTO>> retrieveAllUsers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "username") List<String> sortBy,
            @RequestParam(defaultValue = "asc") String direction,
            @RequestParam(required = false) String username,
            @RequestParam(required = false) Role role) {

        PagedResponse<UserDTO> response = userService.getAllUsers(page, size, sortBy, direction, username, role);
        return ResponseEntity.ok(response); // retorno corregido
    }

    @GetMapping(path = "/{id}")
    public User retrieveOneUser(@PathVariable Long id) {
        return userService.findById(id)
                .orElseThrow(() -> new UserNotFoundException("id: " + id));
    }

    @PostMapping("/{employeeId}")
    public ResponseEntity<User> createUser(@Valid @RequestBody UserDTO userDTO, @PathVariable Long employeeId) {

        userService.validateEmployeeExists(employeeId);

        User user = User.builder()
                .username(userDTO.getUsername())
                .password(userDTO.getPassword())
                .role(userDTO.getRole())
                .employeeId(employeeId)
                .build();

        userService.create(user);

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