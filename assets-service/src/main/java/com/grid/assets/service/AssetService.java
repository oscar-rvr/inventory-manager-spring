package com.grid.assets.service;

import com.grid.assets.dto.AssetPatchDTO;
import com.grid.assets.exceptions.AssetNotFoundException;
import com.grid.assets.model.Asset;
import com.grid.assets.repository.AssetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AssetService {
    private final AssetRepository assetRepository;
    private final WebClient.Builder webClientBuilder;

    public Set<?> getMovementsByAssetId(Long assetId) {
        String url = "http://employees-service:8082/v1/employees/assets/" + assetId;

        return webClientBuilder.build()
                .get()
                .uri(url)
                .retrieve()
                .bodyToMono(Set.class)
                .onErrorResume(ex -> Mono.error(new RuntimeException("Failed to fetch asset movements", ex)))
                .block();
    }

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

        if (dto.getName() != null) {
            existingAsset.setName(dto.getName());
        }
        if (dto.getDescription() != null) {
            existingAsset.setDescription(dto.getDescription());
        }
        if (dto.getSeriesNumber() != null) {
            existingAsset.setSeriesNumber(dto.getSeriesNumber());
        }
        return assetRepository.save(existingAsset);
    }

    public void deletedById(Long id) {
        assetRepository.deleteById(id);
    }
}
