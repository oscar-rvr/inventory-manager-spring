package com.grid.inventorymanager.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.grid.inventorymanager.dto.AssetMovementsDTO;
import com.grid.inventorymanager.model.MovementType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class AssetMovementsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void whenAssetIdIsNull_thenReturnsValidationError() throws Exception {
        AssetMovementsDTO dto = new AssetMovementsDTO();
        dto.setAssetId(null);
        dto.setEmployeeId(1L);
        dto.setMovementType(MovementType.ASSIGN);
        dto.setAssetMovementDate(LocalDate.now());

        mockMvc.perform(post("/v1/movements")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errors.assetId").value("Asset ID is required"));
    }

    @Test
    void whenEmployeeIdIsNull_thenReturnsValidationError() throws Exception {
        AssetMovementsDTO dto = new AssetMovementsDTO();
        dto.setAssetId(1L);
        dto.setEmployeeId(null);
        dto.setMovementType(MovementType.ASSIGN);
        dto.setAssetMovementDate(LocalDate.now());

        mockMvc.perform(post("/v1/movements")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errors.employeeId").value("Employee ID is required"));
    }

    @Test
    void whenMovementTypeAndDateAreNull_thenReturnsValidationErrors() throws Exception {
        AssetMovementsDTO dto = new AssetMovementsDTO();
        dto.setAssetId(1L);
        dto.setEmployeeId(2L);
        dto.setMovementType(null);
        dto.setAssetMovementDate(null);

        mockMvc.perform(post("/v1/movements")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errors.movementType").value("Movement type is required"))
                .andExpect(jsonPath("$.errors.assetMovementDate").value("Asset movement date is required"));
    }

    @Test
    void whenAllFieldsAreMissing_thenReturnsAllValidationErrors() throws Exception {
        AssetMovementsDTO dto = new AssetMovementsDTO();

        mockMvc.perform(post("/v1/movements")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errors.assetId").value("Asset ID is required"))
                .andExpect(jsonPath("$.errors.employeeId").value("Employee ID is required"))
                .andExpect(jsonPath("$.errors.movementType").value("Movement type is required"))
                .andExpect(jsonPath("$.errors.assetMovementDate").value("Asset movement date is required"));
    }
}
