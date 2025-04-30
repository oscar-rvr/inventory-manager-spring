package com.grid.inventorymanager.service;

import com.grid.inventorymanager.model.AssetMovements;
import com.grid.inventorymanager.repository.AssetMovementsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AssetMovementsService {
    private final AssetMovementsRepository assetMovementsRepository;

    public AssetMovements create(AssetMovements assetMovements) {
        return assetMovementsRepository.save(assetMovements);
    }

    public Optional<AssetMovements> findById(Integer id) {
        return assetMovementsRepository.findById(id);
    }

    public List<AssetMovements> findAll() {
        return assetMovementsRepository.findAll();
    }

    public AssetMovements update(AssetMovements assetMovements) {
        return assetMovementsRepository.save(assetMovements);
    }

    public void deletedById(Integer id) {
        assetMovementsRepository.deleteById(id);
    }
}
