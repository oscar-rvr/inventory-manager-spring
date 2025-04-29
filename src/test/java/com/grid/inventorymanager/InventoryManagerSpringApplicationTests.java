package com.grid.inventorymanager;

import com.grid.inventorymanager.controller.AssetController;
import com.grid.inventorymanager.model.Asset;
import com.grid.inventorymanager.repository.AssetRepository;
import com.grid.inventorymanager.service.AssetService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class InventoryManagerSpringApplicationTests {
/*
	@Autowired
	private AssetController assetController;

	@Autowired
	private AssetService assetService;

	@Autowired
	private AssetRepository assetRepository;

	@BeforeEach
	void setup(){
		assetRepository.deleteAll(); //limpiar db
	}

	@Test
	void testCreateAsset() {
		// Crear un Asset
		Asset asset = Asset.builder()
				.name("Laptop")
				.description("Dell XPS 13")
				.seriesNumber("123456789")
				.build();

		assetService.create(asset);  // Guardar el Asset
		assertTrue(assetRepository.existsById(asset.getId()));  // Verifica que el asset fue guardado
	}

	@Test
	void testGetAsset() {
		// Crear un Asset
		Asset asset = Asset.builder()
				.name("Monitor")
				.description("Samsung 27\"")
				.seriesNumber("987654321")
				.build();
		assetRepository.save(asset);  // Guardar el asset en la base de datos

		// Recuperar el asset
		Optional<Asset> fetchedAsset = assetService.findBy(asset.getId());
		assertNotNull(fetchedAsset);
		assertEquals("Monitor", fetchedAsset.get().getName());  // Verifica que el nombre del asset es correcto
	}

	@Test
	void contextLoads() {
	}
*/

}
