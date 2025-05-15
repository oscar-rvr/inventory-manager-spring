package com.grid.inventorymanager.service;

import com.grid.inventorymanager.exceptions.VendorNotFoundException;
import com.grid.inventorymanager.model.Vendor;
import com.grid.inventorymanager.repository.VendorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VendorService {

    private final VendorRepository vendorRepository;

    public Vendor create(Vendor vendor) {
        return vendorRepository.save(vendor);
    }

    public Optional<Vendor> findById(Long id) {
        return vendorRepository.findById(id);
    }

    public List<Vendor> findAll() {
        return vendorRepository.findAll();
    }

    public Vendor update(Long id, Vendor vendor) {
        Vendor existingVendor = vendorRepository.findById(id).orElseThrow(() -> new VendorNotFoundException("id: " + id));

        existingVendor.setName(vendor.getName());
        existingVendor.setContact(vendor.getContact());

        return vendorRepository.save(existingVendor);
    }

    public void deleteById(Long id) {
        vendorRepository.deleteById(id);
    }
}
