package com.grid.inventorymanager.services;

import com.grid.inventorymanager.model.Employee;
import com.grid.inventorymanager.model.Role;
import com.grid.inventorymanager.model.User;
import com.grid.inventorymanager.repository.EmployeeRepository;
import com.grid.inventorymanager.repository.UserRepository;
import com.grid.inventorymanager.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
@Rollback
class UserServiceIT {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    private Employee employee;
    private User user;

    @BeforeEach
    void setUp() {
        userRepository.deleteAll();
        employeeRepository.deleteAll();

        employee = employeeRepository.save(Employee.builder()
                .name("Oscar Dev")
                .mail("oscar@example.com")
                .build());

        user = User.builder()
                .username("oscardev")
                .password("securepass")
                .role(Role.ADMIN)
                .employee(employee)
                .build();
    }

    @Test
    void whenCreate_thenUserIsSaved() {
        User saved = userService.create(user);
        assertThat(saved.getId()).isNotNull();
        assertThat(userRepository.findById(saved.getId())).isPresent();
    }

    @Test
    void whenFindById_thenReturnsUser() {
        User saved = userService.create(user);
        Optional<User> found = userService.findById(saved.getId());
        assertThat(found).isPresent().contains(saved);
    }

    @Test
    void whenFindAll_thenReturnsList() {
        userService.create(user);
        List<User> all = userService.findAll();
        assertThat(all).hasSize(1);
    }

    @Test
    void whenUpdate_thenChangesAreSaved() {
        User saved = userService.create(user);
        saved.setPassword("updatedpass");
        userService.update(saved);

        User updated = userRepository.findById(saved.getId()).orElseThrow();
        assertThat(updated.getPassword()).isEqualTo("updatedpass");
    }

    @Test
    void whenDelete_thenUserIsRemoved() {
        User saved = userService.create(user);
        userService.deleteById(saved.getId());
        assertThat(userRepository.findById(saved.getId())).isEmpty();
    }
}
