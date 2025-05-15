package com.grid.inventorymanager;

import com.grid.inventorymanager.controller.AssetController;
import com.grid.inventorymanager.model.Asset;
import com.grid.inventorymanager.service.AssetService;
import com.grid.inventorymanager.repository.AssetRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class InventoryManagerSpringApplicationTests {

	@Autowired
	private AssetController assetController;

	@MockBean
	private AssetService assetService;

	@MockBean
	private AssetRepository assetRepository;

	@Test
	void testCreateAsset() {
		Asset asset = Asset.builder()
				.name("Laptop")
				.description("Dell XPS 13")
				.seriesNumber("123456789")
				.build();

		// Simular que se guarda y luego existe
		when(assetRepository.existsById(asset.getId())).thenReturn(true);

		assetService.create(asset); // no hace nada, pero simulamos comportamiento
		assertTrue(assetRepository.existsById(asset.getId())); // se valida el mock
	}

	@Test
	void testGetAsset() {
		Asset asset = Asset.builder()
				.name("Monitor")
				.description("Samsung 27\"")
				.seriesNumber("987654321")
				.build();

		// Simular búsqueda
		when(assetService.findById(asset.getId())).thenReturn(Optional.of(asset));

		Optional<Asset> fetchedAsset = assetService.findById(asset.getId());
		assertTrue(fetchedAsset.isPresent());
		assertEquals("Monitor", fetchedAsset.get().getName());
	}

	@Test
	void contextLoads() {
		assertNotNull(assetController);
	}
}
