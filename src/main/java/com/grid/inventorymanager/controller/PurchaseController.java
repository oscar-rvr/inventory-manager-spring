package com.grid.inventorymanager.controller;

import com.grid.inventorymanager.exceptions.PurchaseNotFoundException;
import com.grid.inventorymanager.model.Purchase;
import com.grid.inventorymanager.service.PurchaseService;
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
    public ResponseEntity<Purchase> create(@Valid @RequestBody Purchase purchase) {
        Purchase savedPurchase = purchaseService.create(purchase);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedPurchase.getId())
                .toUri();
        return ResponseEntity.created(location).body(savedPurchase);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Purchase> update(@PathVariable Long id, @Valid @RequestBody Purchase purchase) {
        Purchase updatedPurchase = purchaseService.update(id, purchase);
        return ResponseEntity.ok(updatedPurchase);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        purchaseService.deletedById(id);
        return ResponseEntity.noContent().build();
    }
}
