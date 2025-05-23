package com.grid.inventorymanager.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.grid.inventorymanager.dto.VendorDTO;
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
public class VendorControllerTest {

    @Autowired
    private MockMvc mockMvc;             // simula peticiones HTTP

    @Autowired
    private ObjectMapper objectMapper;   // convierte DTO a JSON

    @Test
    void whenNameIsBlank_thenReturnsValidationError() throws Exception {
        VendorDTO dto = new VendorDTO();
        dto.setName("");
        dto.setContact("Contacto válido");

        mockMvc.perform(post("/v1/vendors")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.title").value("Validation failed"))
                .andExpect(jsonPath("$.errors.name")
                        .value("Name is required"));
    }

    @Test
    void whenContactIsBlank_thenReturnsValidationError() throws Exception {
        VendorDTO dto = new VendorDTO();
        dto.setName("Nombre válido");
        dto.setContact("");

        mockMvc.perform(post("/v1/vendors")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.title").value("Validation failed"))
                .andExpect(jsonPath("$.errors.contact")
                        .value("Contact is required"));
    }

    @Test
    void whenNameAndContactAreBlank_thenReturnsValidationErrors() throws Exception {
        VendorDTO dto = new VendorDTO();
        dto.setName("");
        dto.setContact("");

        mockMvc.perform(post("/v1/vendors")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.title").value("Validation failed"))
                .andExpect(jsonPath("$.errors.name")
                        .value("Name is required"))
                .andExpect(jsonPath("$.errors.contact")
                        .value("Contact is required"));
    }
}
