package com.grid.inventorymanager.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.grid.inventorymanager.dto.AssetDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Tests negativos para validar errores en AssetDTO.
 */
@SpringBootTest
@AutoConfigureMockMvc
public class AssetControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void whenNameIsBlank_thenReturnsValidationError() throws Exception {
        AssetDTO dto = new AssetDTO();
        dto.setName("");
        dto.setDescription("Valid description");
        dto.setSeriesNumber("SN123");

        mockMvc.perform(post("/v1/assets")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errors.name").value("Name is required"));
    }

    @Test
    void whenSeriesNumberIsInvalid_thenReturnsValidationError() throws Exception {
        AssetDTO dto = new AssetDTO();
        dto.setName("Laptop");
        dto.setDescription("Valid description");
        dto.setSeriesNumber("###INVALID###");

        mockMvc.perform(post("/v1/assets")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errors.seriesNumber").value("Invalid Series Number"));
    }

    @Test
    void whenMultipleFieldsInvalid_thenReturnsMultipleErrors() throws Exception {
        AssetDTO dto = new AssetDTO();
        dto.setName("");
        dto.setDescription("");
        dto.setSeriesNumber("!!");

        mockMvc.perform(post("/v1/assets")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errors.name").value("Name is required"))
                .andExpect(jsonPath("$.errors.description").value("Add a description"))
                .andExpect(jsonPath("$.errors.seriesNumber").value("Invalid Series Number"));
    }
}
