package com.grid.inventorymanager.service;

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

    public Optional<Vendor> findById(Integer id) {
        return vendorRepository.findById(id);
    }

    public List<Vendor> findAll() {
        return vendorRepository.findAll();
    }

    public Vendor update(Vendor vendor) {
        return vendorRepository.save(vendor);
    }

    public void deleteById(Integer id) {
        vendorRepository.deleteById(id);
    }
}
