package com.grid.inventorymanager.controller;

import com.grid.inventorymanager.exceptions.AssetNotFoundException;
import com.grid.inventorymanager.exceptions.EmployeeNotFoundException;
import com.grid.inventorymanager.model.Asset;
import com.grid.inventorymanager.model.AssetMovements;
import com.grid.inventorymanager.model.Employee;
import com.grid.inventorymanager.model.MovementType;
import com.grid.inventorymanager.repository.AssetRepository;
import com.grid.inventorymanager.repository.EmployeeRepository;
import com.grid.inventorymanager.service.AssetService;
import com.grid.inventorymanager.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/assets")
public class AssetController {

    private final AssetRepository assetRepository;
    private final EmployeeRepository employeeRepository;
    private final AssetService assetService;

    @GetMapping
    public List<Asset> retrieveAll() {
        return assetRepository.findAll();
    }

    @GetMapping(path = "/{id}")
    public Asset retrieveOne(@PathVariable Long id) {
        Asset user = assetRepository.findById(id).orElse(null);
        if (user == null) {
            throw new AssetNotFoundException("id: " + id);
        }
        return user;
    }

    @PostMapping
    public ResponseEntity<Asset> createUser(@RequestBody Asset asset) {
        Asset saved = assetRepository.save(asset);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(saved.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping(path = "/{id}")
    public void deleteUser(@PathVariable Long id) {
        assetRepository.deleteById(id);
    }

    @GetMapping(path = "/{id}/movements")
    public Set<AssetMovements> retrieveAll(@PathVariable Long id) {
        Asset asset = assetRepository.findById(id).orElse(null);
        if (asset == null) {
            throw new AssetNotFoundException("id: " + id);
        }
        return asset.getEmployees();
    }

    @PostMapping(path = "/{id}/movements/{employeeId}/{movementType}")
    public ResponseEntity<AssetMovements> createMovement(@PathVariable Long id, @PathVariable Long employeeId, @PathVariable MovementType movementType) {
        Asset asset = assetRepository.findById(id).orElse(null);
        if (asset == null) {
            throw new AssetNotFoundException("id: " + id);
        }
        Employee employee = employeeRepository.findById(employeeId).orElse(null);
        if (employee == null) {
            throw new EmployeeNotFoundException("id: " + id);
        }

        asset.addEmployee(employee, LocalDate.now(), movementType);

        Asset saved = assetRepository.save(asset);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}/movements").buildAndExpand(saved.getId()).toUri();
        return ResponseEntity.created(location).build();
    }
}
