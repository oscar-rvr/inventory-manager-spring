package com.grid.inventorymanager.controllers;

import com.grid.inventorymanager.model.*;
import com.grid.inventorymanager.repository.AssetMovementsRepository;
import com.grid.inventorymanager.repository.AssetRepository;
import com.grid.inventorymanager.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class AssetMovementsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AssetMovementsRepository assetMovementsRepository;

    @Autowired
    private AssetRepository assetRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @BeforeEach
    void setup() {
        assetMovementsRepository.deleteAll();
        employeeRepository.deleteAll();
        assetRepository.deleteAll();

        Employee employee = employeeRepository.save(Employee.builder()
                .name("Oscar Dev")
                .mail("oscar@example.com")
                .role(Role.EMPLOYEE)
                .build());

        Asset asset = assetRepository.save(Asset.builder()
                .name("Laptop")
                .description("Dell XPS")
                .seriesNumber("SN001")
                .build());

        AssetMovements movement = AssetMovements.builder()
                .employee(employee)
                .asset(asset)
                .movementType(MovementType.ASSIGN)
                .assetMovementDate(LocalDate.now())
                .build();

        assetMovementsRepository.save(movement);
    }


}
