package com.grid.inventorymanager.controllers;

import com.grid.inventorymanager.model.Employee;
import com.grid.inventorymanager.model.Role;
import com.grid.inventorymanager.model.User;
import com.grid.inventorymanager.repository.EmployeeRepository;
import com.grid.inventorymanager.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @BeforeEach
    void setup() {
        userRepository.deleteAll();
        employeeRepository.deleteAll();

        Employee employee = employeeRepository.save(Employee.builder()
                .name("Oscar Dev")
                .mail("oscar@example.com")
                .build());

        userRepository.save(User.builder()
                .username("oscardev")
                .password("1234")
                .role(Role.ADMIN)
                .employee(employee) // relación requerida
                .build());
    }

    @Test
    void whenGetUsers_thenReturnsViewWithModel() throws Exception {
        mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(view().name("users"))
                .andExpect(model().attributeExists("users"));
    }
}
