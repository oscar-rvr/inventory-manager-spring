package com.grid.inventorymanager.controller;

import com.grid.inventorymanager.exceptions.AssetNotFoundException;
import com.grid.inventorymanager.model.Asset;
import com.grid.inventorymanager.model.AssetMovements;
import com.grid.inventorymanager.repository.AssetRepository;
import com.grid.inventorymanager.service.AssetService;
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

    private final AssetService assetService;

    @GetMapping
    public List<Asset> retrieveAllAssets() {
        return assetService.findAll();
    }

    @GetMapping(path = "/{id}")
    public Asset retrieveOneAsset(@PathVariable Long id) {
        return assetService.findById(id).orElseThrow(() -> new AssetNotFoundException("id: " + id));
    }

    @PostMapping
    public ResponseEntity<Asset> createAsset(@RequestBody Asset asset) {
        Asset saved = assetService.create(asset);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(saved.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping(path = "/{id}")
    public void deleteAsset(@PathVariable Long id) {
        // Validate that the asset is not related to a purchase
        assetService.deletedById(id);
    }

    @GetMapping(path = "/{id}/movements")
    public Set<AssetMovements> retrieveAll(@PathVariable Long id) {
        Asset asset = assetService.findById(id).orElseThrow(() -> new AssetNotFoundException("id: " + id));
        return asset.getEmployees();
    }
}
