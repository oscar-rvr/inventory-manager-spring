package com.grid.inventorymanager.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.grid.inventorymanager.dto.ComputerDTO;
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
public class ComputerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void whenRamIsZero_thenReturnsValidationError() throws Exception {
        ComputerDTO dto = ComputerDTO.builder()
                .name("Laptop")
                .seriesNumber("ABC12345")
                .ram(0)
                .disk(256)
                .core("i5")
                .screenState("Good")
                .keyboardState("Good")
                .shellState("Good")
                .build();

        mockMvc.perform(post("/v1/computers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errors.ram").value("RAM must be at least 1 GB"));
    }

    @Test
    void whenSeriesNumberIsInvalid_thenReturnsValidationError() throws Exception {
        ComputerDTO dto = ComputerDTO.builder()
                .name("PC")
                .seriesNumber("!!INVALID!!")
                .ram(8)
                .disk(500)
                .core("Ryzen 5")
                .screenState("New")
                .keyboardState("Good")
                .shellState("Fair")
                .build();

        mockMvc.perform(post("/v1/computers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errors.seriesNumber").value("Invalid Series Number"));
    }

    @Test
    void whenNameAndCoreAreBlank_thenReturnsValidationErrors() throws Exception {
        ComputerDTO dto = ComputerDTO.builder()
                .name("")
                .seriesNumber("PC123456")
                .ram(8)
                .disk(500)
                .core("")
                .screenState("New")
                .keyboardState("Good")
                .shellState("Good")
                .build();

        mockMvc.perform(post("/v1/computers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errors.name").value("Name is required"))
                .andExpect(jsonPath("$.errors.core").value("Core is required"));
    }

    @Test
    void whenMultipleFieldsInvalid_thenReturnsMultipleErrors() throws Exception {
        ComputerDTO dto = ComputerDTO.builder()
                .name("")                 // error
                .seriesNumber("INVALID") // error @ValidSN
                .ram(0)                  // error
                .disk(null)              // error
                .core("")                // error
                .screenState("")         // error
                .keyboardState("")       // error
                .shellState("")          // error
                .build();

        mockMvc.perform(post("/v1/computers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errors.name").value("Name is required"))
                .andExpect(jsonPath("$.errors.seriesNumber").value("Invalid Series Number"))
                .andExpect(jsonPath("$.errors.ram").value("RAM must be at least 1 GB"))
                .andExpect(jsonPath("$.errors.disk").value("Disk is required"))
                .andExpect(jsonPath("$.errors.core").value("Core is required"))
                .andExpect(jsonPath("$.errors.screenState").value("Screen state is required"))
                .andExpect(jsonPath("$.errors.keyboardState").value("Keyboard state is required"))
                .andExpect(jsonPath("$.errors.shellState").value("Shell state is required"));
    }
}
