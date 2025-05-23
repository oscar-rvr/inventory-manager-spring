package com.grid.inventorymanager.service;

import com.grid.inventorymanager.dto.AssetPatchDTO;
import com.grid.inventorymanager.exceptions.AssetNotFoundException;
import com.grid.inventorymanager.model.Asset;
import com.grid.inventorymanager.repository.AssetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AssetService {
    private final AssetRepository assetRepository;

    public Asset create(Asset asset) {
        return assetRepository.save(asset);
    }

    public Optional<Asset> findById(Long id) {
        return assetRepository.findById(id);
    }

    public List<Asset> findAll() {
        return assetRepository.findAll();
    }

    public Asset update(Asset asset) {
        return assetRepository.save(asset);
    }

    public Asset update(Long id, AssetPatchDTO dto) {
        Asset existingAsset = assetRepository.findById(id).orElseThrow(() -> new AssetNotFoundException("id: " + id));

        if(dto.getName() != null) {
            existingAsset.setName(dto.getName());
        }
        if(dto.getDescription() != null) {
            existingAsset.setDescription(dto.getDescription());
        }
        if(dto.getSeriesNumber() != null) {
            existingAsset.setSeriesNumber(dto.getSeriesNumber());
        }
        return assetRepository.save(existingAsset);
    }


    public void deletedById(Long id) {
        assetRepository.deleteById(id);
    }
}
