package com.grid.inventorymanager.controllers;

import com.grid.inventorymanager.model.Asset;
import com.grid.inventorymanager.repository.AssetRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class AssetControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AssetRepository assetRepository;

    @BeforeEach
    void setup() {
        assetRepository.deleteAll();
        assetRepository.save(Asset.builder()
                .name("Laptop")
                .description("HP Elitebook")
                .seriesNumber("SN123")
                .build());
    }

    @Test
    void whenGetAssets_thenReturnsAssetsViewWithModel() throws Exception {
        mockMvc.perform(get("/assets"))
                .andExpect(status().isOk())
                .andExpect(view().name("assets"))
                .andExpect(model().attributeExists("assets"));
    }
}
