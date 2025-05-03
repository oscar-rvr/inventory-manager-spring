package com.grid.inventorymanager.service;

import com.grid.inventorymanager.model.PurchaseDetail;
import com.grid.inventorymanager.repository.PurchaseDetailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PurchaseDetailService {

    private final PurchaseDetailRepository purchaseDetailRepository;

    public PurchaseDetail create(PurchaseDetail purchaseDetail) {
        return purchaseDetailRepository.save(purchaseDetail);
    }

    public Optional<PurchaseDetail> findById(Long id) {
        return purchaseDetailRepository.findById(id);
    }

    public List<PurchaseDetail> findAll() {
        return purchaseDetailRepository.findAll();
    }

    public PurchaseDetail update(PurchaseDetail purchaseDetail) {
        return purchaseDetailRepository.save(purchaseDetail);
    }

    public void deleteById(Long id) {
        purchaseDetailRepository.deleteById(id);
    }
}
