package com.grid.inventorymanager.controller;

import com.grid.inventorymanager.exceptions.PurchaseDetailNotFoundException;
import com.grid.inventorymanager.model.PurchaseDetail;
import com.grid.inventorymanager.service.PurchaseDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
@RestController
@RequestMapping("/v1/purchase-details")
@RequiredArgsConstructor
public class PurchaseDetailController {

    private final PurchaseDetailService purchaseDetailService;

    @GetMapping
    public List<PurchaseDetail> findAll() {
        return purchaseDetailService.findAll();
    }

    @GetMapping("/{id}")
    public PurchaseDetail findById(@PathVariable Long id) {
        return purchaseDetailService.findById(id)
                .orElseThrow(() -> new PurchaseDetailNotFoundException("id: " + id));
    }

    @PostMapping
    public ResponseEntity<PurchaseDetail> create(@RequestBody PurchaseDetail detail) {
        PurchaseDetail savedDetail = purchaseDetailService.create(detail);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedDetail.getId())
                .toUri();
        return ResponseEntity.created(location).body(savedDetail);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        purchaseDetailService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<PurchaseDetail> update(@PathVariable Long id, @RequestBody PurchaseDetail detail) {
        PurchaseDetail updatedDetail = purchaseDetailService.update(id, detail);
        return ResponseEntity.ok(updatedDetail);
    }
}
