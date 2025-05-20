package com.grid.inventorymanager.service;

import com.grid.inventorymanager.exceptions.PurchaseNotFoundException;
import com.grid.inventorymanager.exceptions.VendorNotFoundException;
import com.grid.inventorymanager.model.Purchase;
import com.grid.inventorymanager.model.Vendor;
import com.grid.inventorymanager.repository.PurchaseRepository;
import com.grid.inventorymanager.repository.VendorRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PurchaseService {
    private final PurchaseRepository purchaseRepository;
    private final VendorRepository vendorRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public Purchase create(Purchase purchase) {
        if (purchase.getVendor() == null || purchase.getVendor().getId() == null) {
            throw new VendorNotFoundException("Vendor is required");
        }

        Vendor vendor = entityManager.getReference(Vendor.class, purchase.getVendor().getId());
        purchase.setVendor(vendor);

        return purchaseRepository.save(purchase);
    }

    public Optional<Purchase> findById(Long id) {
        return purchaseRepository.findById(id);
    }

    public List<Purchase> findAll() {
        return purchaseRepository.findAll();
    }

    public Purchase update(Long id, Purchase purchase) {
        Purchase existingPurchase = purchaseRepository.findById(id)
                .orElseThrow(() -> new PurchaseNotFoundException("id: " + id));

        if (purchase.getVendor() != null && purchase.getVendor().getId() != null) {
            Vendor vendor = entityManager.getReference(Vendor.class, purchase.getVendor().getId());
            existingPurchase.setVendor(vendor);
        }

        if (purchase.getDate() != null) {
            existingPurchase.setDate(purchase.getDate());
        }

        if (purchase.getTotalAmount() != null) {
            existingPurchase.setTotalAmount(purchase.getTotalAmount());
        }

        return purchaseRepository.save(existingPurchase);
    }

    public void deletedById(Long id) {
        purchaseRepository.deleteById(id);
    }
}