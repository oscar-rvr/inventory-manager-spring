package com.grid.assets.controller;

import com.grid.assets.dto.AssetDTO;
import com.grid.assets.dto.AssetMovementDTO;
import com.grid.assets.dto.AssetPatchDTO;
import com.grid.assets.exceptions.AssetNotFoundException;
import com.grid.assets.model.Asset;
import com.grid.assets.service.AssetService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/assets")
public class AssetController {

    private final AssetService assetService;
    private final WebClient webClient;


    @GetMapping
    public List<Asset> retrieveAllAssets() {
        return assetService.findAll();
    }

    @GetMapping(path = "/{id}")
    public Asset retrieveOneAsset(@PathVariable Long id) {
        return assetService.findById(id).orElseThrow(() -> new AssetNotFoundException("id: " + id));
    }

    @PostMapping
    public ResponseEntity<Asset> createAsset(@Valid @RequestBody AssetDTO assetDTO) {
        Asset asset = Asset.builder()
                .name(assetDTO.getName())
                .description(assetDTO.getDescription())
                .seriesNumber(assetDTO.getSeriesNumber())
                .build();

        Asset saved = assetService.create(asset);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(saved.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping(path = "/{id}")
    public void deleteAsset(@PathVariable Long id) {
        // Validate that the asset is not related to a purchase
        assetService.deletedById(id);
    }

    @GetMapping(path = "/{id}/movements")
    public Set<AssetMovementDTO> retrieveAll(@PathVariable Long id) {
        Asset asset = assetService.findById(id)
                .orElseThrow(() -> new AssetNotFoundException("id: " + id));

        String url = "http://asset-movements-service:8084/v1/movements/asset/" + id;

        AssetMovementDTO[] movements = webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(AssetMovementDTO[].class)
                .block();

        return Set.of(movements);
    }


    @PutMapping(path = "/{id}")
    public ResponseEntity<Asset> updateAsset(@PathVariable Long id, @RequestBody AssetPatchDTO assetDTO) {
        Asset updatedAsset = assetService.update(id, assetDTO);
        return ResponseEntity.ok(updatedAsset);
    }
}
