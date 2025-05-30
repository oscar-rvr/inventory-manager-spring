package com.grid.inventorymanager.service;

import com.grid.inventorymanager.model.AssetMovements;
import com.grid.inventorymanager.model.AssetMovementsId;
import com.grid.inventorymanager.repository.AssetMovementsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AssetMovementsService {
    private final AssetMovementsRepository assetMovementsRepository;

    public List<AssetMovements> findAll() {
        return assetMovementsRepository.findAll();
    }

    public List<AssetMovements> findAll(Example<AssetMovements> assetMovementsId) {
        return assetMovementsRepository.findAll(assetMovementsId);
    }

    public AssetMovements create(AssetMovements assetMovements) {
        return assetMovementsRepository.save(assetMovements);
    }

    public Optional<AssetMovements> findById(AssetMovementsId id) {
        return assetMovementsRepository.findById(id);
    }

    public AssetMovements update(AssetMovements assetMovements) {
        return assetMovementsRepository.save(assetMovements);
    }

    public void deletedById(AssetMovementsId id) {
        assetMovementsRepository.deleteById(id);
    }
}
