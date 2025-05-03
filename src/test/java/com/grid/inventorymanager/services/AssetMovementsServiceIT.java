package com.grid.inventorymanager.services;

import com.grid.inventorymanager.model.*;
import com.grid.inventorymanager.repository.AssetMovementsRepository;
import com.grid.inventorymanager.repository.AssetRepository;
import com.grid.inventorymanager.repository.EmployeeRepository;
import com.grid.inventorymanager.service.AssetMovementsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
@Rollback
class AssetMovementsServiceIT {

    @Autowired
    private AssetMovementsService assetMovementsService;

    @Autowired
    private AssetRepository assetRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private AssetMovementsRepository assetMovementsRepository;

    private Employee employee;
    private Asset asset;
    private AssetMovements movement;

    @BeforeEach
    void setUp() {
        assetMovementsRepository.deleteAll();
        employeeRepository.deleteAll();
        assetRepository.deleteAll();

        employee = employeeRepository.save(Employee.builder()
                .name("Oscar Dev")
                .mail("oscar@example.com")
                .role(Role.ADMIN)
                .build());

        asset = assetRepository.save(Asset.builder()
                .name("Laptop")
                .description("Dell XPS")
                .seriesNumber("SN-456")
                .build());

        movement = AssetMovements.builder()
                .employee(employee)
                .asset(asset)
                .movementType(MovementType.ASSIGN)
                .assetMovementDate(LocalDate.now())
                .build();
    }

    @Test
    void whenCreate_thenPersistMovement() {
        AssetMovements saved = assetMovementsService.create(movement);
        assertThat(saved.getId()).isNotNull();
        assertThat(assetMovementsRepository.findById(saved.getId().getAssetId())).isPresent();
    }

    @Test
    void whenFindById_thenReturnsMovement() {
        AssetMovements saved = assetMovementsService.create(movement);
        Optional<AssetMovements> found = assetMovementsService.findById(saved.getId().getAssetId());
        assertThat(found).isPresent().contains(saved);
    }

    @Test
    void whenFindAll_thenReturnsList() {
        assetMovementsService.create(movement);
        List<AssetMovements> all = assetMovementsService.findAll();
        assertThat(all).hasSize(1);
    }

    @Test
    void whenUpdate_thenChangesAreSaved() {
        AssetMovements saved = assetMovementsService.create(movement);
        saved.setMovementType(MovementType.ASSIGN);
        assetMovementsService.update(saved);

        AssetMovements updated = assetMovementsRepository.findById(saved.getId().getAssetId()).orElseThrow();
        assertThat(updated.getMovementType()).isEqualTo(MovementType.ASSIGN);
    }

    @Test
    void whenDelete_thenItIsRemoved() {
        AssetMovements saved = assetMovementsService.create(movement);
        assetMovementsService.deletedById(saved.getId().getAssetId());
        assertThat(assetMovementsRepository.findById(saved.getId().getAssetId())).isEmpty();
    }
}
