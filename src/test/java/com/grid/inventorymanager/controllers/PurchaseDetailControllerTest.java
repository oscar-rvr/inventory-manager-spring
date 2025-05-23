package com.grid.inventorymanager.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.grid.inventorymanager.dto.PurchaseDetailDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
public class PurchaseDetailControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void whenAmountIsZero_thenReturnsValidationError() throws Exception {
        PurchaseDetailDTO dto = new PurchaseDetailDTO();
        dto.setPurchaseId(1L);
        dto.setAssetId(1L);
        dto.setAmount(0);
        dto.setPricePerItem(100.0);

        mockMvc.perform(post("/v1/purchase-details")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.title").value("Validation failed"))
                .andExpect(jsonPath("$.errors.amount").value("Amount must be at least 1"));
    }

    @Test
    void whenPricePerItemIsMissing_thenReturnsValidationError() throws Exception {
        PurchaseDetailDTO dto = new PurchaseDetailDTO();
        dto.setPurchaseId(1L);
        dto.setAssetId(1L);
        dto.setAmount(5);
        dto.setPricePerItem(null);

        mockMvc.perform(post("/v1/purchase-details")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.title").value("Validation failed"))
                .andExpect(jsonPath("$.errors.pricePerItem").value("Price per item is required"));
    }

    @Test
    void whenPurchaseIdAndAssetIdMissing_thenReturnsValidationErrors() throws Exception {
        PurchaseDetailDTO dto = new PurchaseDetailDTO();
        dto.setPurchaseId(null);
        dto.setAssetId(null);
        dto.setAmount(5);
        dto.setPricePerItem(99.0);

        mockMvc.perform(post("/v1/purchase-details")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.title").value("Validation failed"))
                .andExpect(jsonPath("$.errors.purchaseId").value("Purchase ID is required"))
                .andExpect(jsonPath("$.errors.assetId").value("Asset ID is required"));
    }
}
