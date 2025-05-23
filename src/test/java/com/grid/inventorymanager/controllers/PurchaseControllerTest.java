package com.grid.inventorymanager.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.grid.inventorymanager.dto.PurchaseDTO;
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
public class PurchaseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void whenTotalAmountIsNegative_thenReturnsValidationError() throws Exception {
        PurchaseDTO dto = new PurchaseDTO();
        dto.setVendorId(1L);
        dto.setDate(java.time.LocalDate.now());
        dto.setTotalAmount(-50.0);

        mockMvc.perform(post("/v1/purchases")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.title").value("Validation failed"))
                .andExpect(jsonPath("$.errors.totalAmount").value("Total amount must be positive"));
    }

    @Test
    void whenVendorIdIsMissing_thenReturnsValidationError() throws Exception {
        PurchaseDTO dto = new PurchaseDTO();
        dto.setVendorId(null);
        dto.setDate(java.time.LocalDate.now());
        dto.setTotalAmount(500.0);

        mockMvc.perform(post("/v1/purchases")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.title").value("Validation failed"))
                .andExpect(jsonPath("$.errors.vendorId").value("Vendor ID is required"));
    }

    @Test
    void whenVendorIdAndDateMissing_thenReturnsValidationErrors() throws Exception {
        PurchaseDTO dto = new PurchaseDTO();
        dto.setVendorId(null);
        dto.setDate(null);
        dto.setTotalAmount(500.0);

        mockMvc.perform(post("/v1/purchases")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.title").value("Validation failed"))
                .andExpect(jsonPath("$.errors.vendorId").value("Vendor ID is required"))
                .andExpect(jsonPath("$.errors.date").value("Date is required"));
    }
}
