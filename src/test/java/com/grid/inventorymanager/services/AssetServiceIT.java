package com.grid.inventorymanager.services;

import com.grid.inventorymanager.model.Asset;
import com.grid.inventorymanager.repository.AssetRepository;
import com.grid.inventorymanager.service.AssetService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
@Rollback
class AssetServiceIT {

    @Autowired
    private AssetService assetService;

    @Autowired
    private AssetRepository assetRepository;

    private Asset asset;

    @BeforeEach
    void setUp() {
        assetRepository.deleteAll();
        asset = Asset.builder()
                .name("Laptop")
                .description("Dell XPS")
                .seriesNumber("SN-1234")
                .build();
    }

    @Test
    void whenCreate_thenAssetIsPersisted() {
        Asset saved = assetService.create(asset);
        assertThat(saved.getId()).isNotNull();
        assertThat(assetRepository.findById(saved.getId())).isPresent();
    }

    @Test
    void whenFindAll_thenReturnsList() {
        assetService.create(asset);
        List<Asset> all = assetService.findAll();
        assertThat(all).hasSize(1);
    }

    @Test
    void whenFindById_thenReturnsCorrectAsset() {
        Asset saved = assetService.create(asset);
        Optional<Asset> found = assetService.findBy(saved.getId());
        assertThat(found).isPresent().contains(saved);
    }

    @Test
    void whenUpdate_thenDataIsUpdated() {
        Asset saved = assetService.create(asset);
        saved.setDescription("Updated description");
        assetService.update(saved);

        Asset updated = assetRepository.findById(saved.getId()).orElseThrow();
        assertThat(updated.getDescription()).isEqualTo("Updated description");
    }

    @Test
    void whenDelete_thenAssetIsRemoved() {
        Asset saved = assetService.create(asset);
        assetService.deletedById(saved.getId());
        assertThat(assetRepository.findById(saved.getId())).isEmpty();
    }
}
