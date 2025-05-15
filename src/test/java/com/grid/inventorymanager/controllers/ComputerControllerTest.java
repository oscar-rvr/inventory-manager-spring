package com.grid.inventorymanager.controllers;

import com.grid.inventorymanager.model.*;
import com.grid.inventorymanager.repository.AssetMovementsRepository;
import com.grid.inventorymanager.repository.ComputerRepository;
import com.grid.inventorymanager.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class ComputerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ComputerRepository computerRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private AssetMovementsRepository assetMovementsRepository;

    @BeforeEach
    void setup() {
        assetMovementsRepository.deleteAll();
        employeeRepository.deleteAll();
        computerRepository.deleteAll();

        Employee employee = employeeRepository.save(Employee.builder()
                .name("Oscar Dev")
                .mail("oscar@example.com")
                .build());

        Computer computer = computerRepository.save(Computer.builder()
                .name("Workstation")
                .description("Lenovo")
                .seriesNumber("C-12345")
                .ram(16)
                .disk(512)
                .core("i7")
                .screenState("Good")
                .keyboardState("Good")
                .shellState("Good")
                .comments("Nuevo ingreso")
                .build());

        assetMovementsRepository.save(AssetMovements.builder()
                .employee(employee)
                .asset(computer) // hereda de Asset
                .movementType(MovementType.ASSIGN)
                .assetMovementDate(LocalDate.now())
                .build());
    }

    @Test
    void whenGetComputers_thenReturnsViewWithModel() throws Exception {
        mockMvc.perform(get("/computers"))
                .andExpect(status().isOk())
                .andExpect(view().name("computers"))
                .andExpect(model().attributeExists("computers"));
    }
}