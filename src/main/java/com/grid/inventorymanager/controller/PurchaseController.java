package com.grid.inventorymanager.controller;

import com.grid.inventorymanager.dto.PurchaseDTO;
import com.grid.inventorymanager.exceptions.PurchaseNotFoundException;
import com.grid.inventorymanager.model.Purchase;
import com.grid.inventorymanager.model.Vendor;
import com.grid.inventorymanager.service.PurchaseService;
import com.grid.inventorymanager.repository.VendorRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/purchases")
public class PurchaseController {

    private final PurchaseService purchaseService;
    private final VendorRepository vendorRepository;

    @GetMapping
    public List<Purchase> findAll() {
        return purchaseService.findAll();
    }

    @GetMapping("/{id}")
    public Purchase findById(@PathVariable Long id) {
        return purchaseService.findById(id)
                .orElseThrow(() -> new PurchaseNotFoundException("Purchase id not found: " + id));
    }

    @PostMapping
    public ResponseEntity<Purchase> create(@Valid @RequestBody PurchaseDTO dto) {
        Vendor vendor = vendorRepository.findById(dto.getVendorId())
                .orElseThrow(() -> new PurchaseNotFoundException("Vendor ID not found: " + dto.getVendorId()));

        Purchase purchase = Purchase.builder()
                .vendor(vendor)
                .date(dto.getDate())
                .totalAmount(dto.getTotalAmount())
                .build();

        Purchase saved = purchaseService.create(purchase);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(saved.getId())
                .toUri();
        return ResponseEntity.created(location).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Purchase> update(@PathVariable Long id, @Valid @RequestBody PurchaseDTO dto) {
        Vendor vendor = vendorRepository.findById(dto.getVendorId())
                .orElseThrow(() -> new PurchaseNotFoundException("Vendor ID not found: " + dto.getVendorId()));

        Purchase purchase = Purchase.builder()
                .vendor(vendor)
                .date(dto.getDate())
                .totalAmount(dto.getTotalAmount())
                .build();

        Purchase updated = purchaseService.update(id, purchase);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        purchaseService.deletedById(id);
        return ResponseEntity.noContent().build();
    }
}
