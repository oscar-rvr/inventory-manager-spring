package com.grid.inventorymanager.services;

import com.grid.inventorymanager.model.Computer;
import com.grid.inventorymanager.repository.ComputerRepository;
import com.grid.inventorymanager.service.ComputerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
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
@Disabled("need to update")

class ComputerServiceIT {

    @Autowired
    private ComputerService computerService;

    @Autowired
    private ComputerRepository computerRepository;

    private Computer computer;

    @BeforeEach
    void setUp() {
        computerRepository.deleteAll();

        computer = Computer.builder()
                .name("Workstation")
                .description("Office machine")
                .seriesNumber("CMP-001")
                .ram(16)
                .disk(512)
                .core("i7")
                .screenState("Good")
                .keyboardState("Excellent")
                .shellState("Fair")
                .comments("Test computer")
                .build();
    }

    @Test
    void whenCreate_thenComputerIsSaved() {
        Computer saved = computerService.create(computer);
        assertThat(saved.getId()).isNotNull();
        assertThat(computerRepository.findById(saved.getId())).isPresent();
    }

    @Test
    void whenFindAll_thenReturnsComputers() {
        computerService.create(computer);
        List<Computer> all = computerService.findAll();
        assertThat(all).hasSize(1);
    }

    @Test
    void whenFindById_thenReturnsComputer() {
        Computer saved = computerService.create(computer);
        Optional<Computer> found = computerService.findById(saved.getId());
        assertThat(found).isPresent().contains(saved);
    }

    @Test
    void whenUpdate_thenDataIsPersisted() {
        Computer saved = computerService.create(computer);
        saved.setComments("Updated");
        computerService.update(saved);

        Computer updated = computerRepository.findById(saved.getId()).orElseThrow();
        assertThat(updated.getComments()).isEqualTo("Updated");
    }

    @Test
    void whenDelete_thenComputerIsRemoved() {
        Computer saved = computerService.create(computer);
        computerService.deleteById(saved.getId());
        assertThat(computerRepository.findById(saved.getId())).isEmpty();
    }
}
