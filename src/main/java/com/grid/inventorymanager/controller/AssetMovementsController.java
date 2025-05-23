package com.grid.inventorymanager.controller;

import com.grid.inventorymanager.dto.AssetMovementsDTO;
import com.grid.inventorymanager.exceptions.AssetNotFoundException;
import com.grid.inventorymanager.exceptions.EmployeeNotFoundException;
import com.grid.inventorymanager.model.*;
import com.grid.inventorymanager.service.AssetMovementsService;
import com.grid.inventorymanager.service.AssetService;
import com.grid.inventorymanager.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/movements")
public class AssetMovementsController {

    private final AssetMovementsService assetMovementsService;
    private final AssetService assetService;
    private final EmployeeService employeeService;

    @GetMapping
    public List<AssetMovements> showAssetMovements() {
        return assetMovementsService.findAll();
    }

    @GetMapping("/emp/{employeeId}")
    public List<AssetMovements> showAssetMovementsByEmployee(@PathVariable Long employeeId) {
        AssetMovementsId id = new AssetMovementsId();
        id.setEmployeeId(employeeId);

        AssetMovements assetMovements = new AssetMovements();
        assetMovements.setId(id);

        return assetMovementsService.findAll(Example.of(assetMovements));
    }

    @GetMapping("/asset/{assetId}")
    public List<AssetMovements> showAssetMovementsByAsset(@PathVariable Long assetId) {
        AssetMovementsId id = new AssetMovementsId();
        id.setAssetId(assetId);

        AssetMovements assetMovements = new AssetMovements();
        assetMovements.setId(id);

        return assetMovementsService.findAll(Example.of(assetMovements));
    }

    @GetMapping("/{assetId}/{employeeId}")
    public List<AssetMovements> showSpecificAssetMovements(@PathVariable Long assetId, @PathVariable Long employeeId) {
        AssetMovementsId id = new AssetMovementsId();
        id.setAssetId(assetId);
        id.setEmployeeId(employeeId);

        AssetMovements assetMovements = new AssetMovements();
        assetMovements.setId(id);

        return assetMovementsService.findAll(Example.of(assetMovements));
    }

    @PostMapping
    public ResponseEntity<AssetMovements> createMovement(@Valid @RequestBody AssetMovementsDTO dto) {
        Asset asset = assetService.findById(dto.getAssetId())
                .orElseThrow(() -> new AssetNotFoundException("id: " + dto.getAssetId()));
        Employee employee = employeeService.findById(dto.getEmployeeId())
                .orElseThrow(() -> new EmployeeNotFoundException("id: " + dto.getEmployeeId()));

        AssetMovements assetMovements = AssetMovements.builder()
                .id(new AssetMovementsId(dto.getAssetId(), dto.getEmployeeId()))
                .asset(asset)
                .employee(employee)
                .movementType(dto.getMovementType())
                .assetMovementDate(dto.getAssetMovementDate())
                .build();

        AssetMovements saved = assetMovementsService.create(assetMovements);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("").build().toUri();
        return ResponseEntity.created(location).body(saved);
    }

}
