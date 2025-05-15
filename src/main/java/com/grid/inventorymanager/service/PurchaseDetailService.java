package com.grid.inventorymanager.service;

import com.grid.inventorymanager.exceptions.AssetNotFoundException;
import com.grid.inventorymanager.exceptions.PurchaseNotFoundException;
import com.grid.inventorymanager.model.Asset;
import com.grid.inventorymanager.model.Purchase;
import com.grid.inventorymanager.model.PurchaseDetail;
import com.grid.inventorymanager.repository.AssetRepository;
import com.grid.inventorymanager.repository.PurchaseDetailRepository;
import com.grid.inventorymanager.repository.PurchaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PurchaseDetailService {

    private final PurchaseDetailRepository purchaseDetailRepository;
    private final PurchaseRepository purchaseRepository;
    private final AssetRepository assetRepository;

    public PurchaseDetail create(PurchaseDetail purchaseDetail) {
        return purchaseDetailRepository.save(purchaseDetail);
    }

    public Optional<PurchaseDetail> findById(Long id) {
        return purchaseDetailRepository.findById(id);
    }

    public List<PurchaseDetail> findAll() {
        return purchaseDetailRepository.findAll();
    }

    public PurchaseDetail update(Long id, PurchaseDetail purchaseDetail) {
        PurchaseDetail existingPurchaseDetail = purchaseDetailRepository.findById(id)
                .orElseThrow(() -> new PurchaseNotFoundException("id: " + id));

        Purchase purchase = purchaseRepository.findById(purchaseDetail.getPurchase().getId())
                .orElseThrow(() -> new PurchaseNotFoundException("purchase id: " + purchaseDetail.getPurchase().getId()));

        Asset asset = assetRepository.findById(purchaseDetail.getAsset().getId())
                .orElseThrow(() -> new AssetNotFoundException("asset id: " + purchaseDetail.getAsset().getId()));

        existingPurchaseDetail.setPurchase(purchase);
        existingPurchaseDetail.setAsset(asset);
        existingPurchaseDetail.setAmount(purchaseDetail.getAmount());
        existingPurchaseDetail.setPricePerItem(purchaseDetail.getPricePerItem());

        return purchaseDetailRepository.save(existingPurchaseDetail);
    }


    public void deleteById(Long id) {
        purchaseDetailRepository.deleteById(id);
    }
}
