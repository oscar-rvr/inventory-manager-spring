package com.grid.assetmovements.service;

import com.grid.assetmovements.client.AssetClient;
import com.grid.assetmovements.client.EmployeeClient;
import com.grid.assetmovements.client.UserClient;
import com.grid.assetmovements.dto.UserDTO;
import com.grid.assetmovements.model.AssetMovements;
import com.grid.assetmovements.model.AssetMovementsId;
import com.grid.assetmovements.repository.AssetMovementsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AssetMovementsService {
    private final AssetMovementsRepository assetMovementsRepository;
    private final UserClient userClient;
    private final AssetClient assetClient;
    private final EmployeeClient employeeClient;

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

    public AssetMovements create(AssetMovements assetMovements, Long userId) {
        userClient.getUserById(userId);
        assetClient.validateAssetExists(assetMovements.getId().getAssetId());
        employeeClient.validateEmployeeExists(assetMovements.getId().getEmployeeId());

        return assetMovementsRepository.save(assetMovements);
    }




}
