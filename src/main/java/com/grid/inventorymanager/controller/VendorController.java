package com.grid.inventorymanager.controller;

import com.grid.inventorymanager.exceptions.VendorNotFoundException;
import com.grid.inventorymanager.model.Vendor;
import com.grid.inventorymanager.service.VendorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/vendors")
public class VendorController {
    private final VendorService vendorService;

    @PostMapping
    public ResponseEntity<Vendor> createVendor(@Valid @RequestBody Vendor vendor) {
        Vendor savedVendor = vendorService.create(vendor);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedVendor.getId())
                .toUri();
        return ResponseEntity.created(location).body(savedVendor);
    }

    @GetMapping
    public List<Vendor> retrieveAll() {
        return vendorService.findAll();
    }

    @GetMapping(path = "/{id}")
    public Vendor retrieveOne(@PathVariable Long id) {
        return vendorService.findById(id).orElseThrow(() -> new VendorNotFoundException("id: " + id));
    }

    @DeleteMapping(path = "/{id}")
    public void deleteOne(@PathVariable Long id) {
        vendorService.deleteById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Vendor> updateVendor(@PathVariable Long id, @Valid @RequestBody Vendor vendor) {
        Vendor updatedVendor = vendorService.update(id, vendor);
        return ResponseEntity.ok(updatedVendor);
    }



}
