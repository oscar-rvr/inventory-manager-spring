package com.grid.inventorymanager.service;

import com.grid.inventorymanager.dto.PurchaseDetailDTO;
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

    public PurchaseDetail create(PurchaseDetailDTO dto) {
        Purchase purchase = purchaseRepository.findById(dto.getPurchaseId())
                .orElseThrow(() -> new PurchaseNotFoundException("Purchase ID: " + dto.getPurchaseId()));

        Asset asset = assetRepository.findById(dto.getAssetId())
                .orElseThrow(() -> new AssetNotFoundException("Asset ID: " + dto.getAssetId()));

        PurchaseDetail detail = PurchaseDetail.builder()
                .purchase(purchase)
                .asset(asset)
                .amount(dto.getAmount())
                .pricePerItem(dto.getPricePerItem())
                .build();

        return purchaseDetailRepository.save(detail);
    }

    public Optional<PurchaseDetail> findById(Long id) {
        return purchaseDetailRepository.findById(id);
    }

    public List<PurchaseDetail> findAll() {
        return purchaseDetailRepository.findAll();
    }

    public PurchaseDetail update(Long id, PurchaseDetailDTO dto) {
        PurchaseDetail existing = purchaseDetailRepository.findById(id)
                .orElseThrow(() -> new PurchaseNotFoundException("id: " + id));

        if (dto.getPurchaseId() != null) {
            Purchase purchase = purchaseRepository.findById(dto.getPurchaseId())
                    .orElseThrow(() -> new PurchaseNotFoundException("Purchase ID: " + dto.getPurchaseId()));
            existing.setPurchase(purchase);
        }

        if (dto.getAssetId() != null) {
            Asset asset = assetRepository.findById(dto.getAssetId())
                    .orElseThrow(() -> new AssetNotFoundException("Asset ID: " + dto.getAssetId()));
            existing.setAsset(asset);
        }

        if (dto.getAmount() != null) {
            existing.setAmount(dto.getAmount());
        }

        if (dto.getPricePerItem() != null) {
            existing.setPricePerItem(dto.getPricePerItem());
        }

        return purchaseDetailRepository.save(existing);
    }


    public void deleteById(Long id) {
        purchaseDetailRepository.deleteById(id);
    }
}
