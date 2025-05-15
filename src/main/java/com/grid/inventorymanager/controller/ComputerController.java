package com.grid.inventorymanager.controller;

import com.grid.inventorymanager.exceptions.ComputerNotFoundException;
import com.grid.inventorymanager.model.AssetMovements;
import com.grid.inventorymanager.model.Computer;
import com.grid.inventorymanager.service.ComputerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/computers")
public class ComputerController {

    private final ComputerService computerService;

    @GetMapping
    public List<Computer> retrieveAllComputers() {
        return computerService.findAll();
    }

    @GetMapping(path = "/{id}")
    public Computer retrieveOneComputer(@PathVariable Long id) {
        Computer computer = computerService.findById(id).orElseThrow(() -> new ComputerNotFoundException("id: " + id));
        return computer;
    }

    @PostMapping
    public ResponseEntity<Computer> createComputer(@RequestBody Computer computer) {
        Computer saved = computerService.create(computer);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(saved.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping(path = "/{id}")
    public void deleteComputer(@PathVariable Long id) {
        // Validate that the computer is not related to a purchase
        computerService.deleteById(id);
    }

    @GetMapping(path = "/{id}/movements")
    public Set<AssetMovements> retrieveAll(@PathVariable Long id) {
        return computerService.findById(id)
                .orElseThrow(() -> new ComputerNotFoundException("id: " + id))
                .getEmployees();
    }
}
