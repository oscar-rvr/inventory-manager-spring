package com.grid.inventorymanager.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.grid.inventorymanager.dto.EmployeeDTO;
import com.grid.inventorymanager.dto.EmployeePatchDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Tests negativos para validaciones en EmployeeDTO y EmployeePatchDTO.
 */
@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void whenNameTooShort_thenReturnsValidationError() throws Exception {
        EmployeeDTO dto = new EmployeeDTO();
        dto.setName("Ana");
        dto.setMail("ana@gg.com");

        mockMvc.perform(post("/v1/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errors.name").value("Name must be between 4 and 20 characters"));
    }

    @Test
    void whenEmailIsInvalid_thenReturnsValidationError() throws Exception {
        EmployeeDTO dto = new EmployeeDTO();
        dto.setName("Anita");
        dto.setMail("ana@email.com");

        mockMvc.perform(post("/v1/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errors.mail").value("Invalid email. Does not belong to griddynamics domain"));
    }

    @Test
    void whenNameTooShortAndEmailInvalid_thenReturnsBothValidationErrors() throws Exception {
        EmployeeDTO dto = new EmployeeDTO();
        dto.setName("Ana");
        dto.setMail("ana@email.com");

        mockMvc.perform(post("/v1/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errors.name").value("Name must be between 4 and 20 characters"))
                .andExpect(jsonPath("$.errors.mail").value("Invalid email. Does not belong to griddynamics domain"));
    }

    @Test
    void whenInvalidEmailInPatch_thenReturnsValidationError() throws Exception {
        EmployeePatchDTO dto = new EmployeePatchDTO();
        dto.setMail("otro@email.com");

        mockMvc.perform(patch("/v1/employees/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errors.mail").value("Invalid email. Does not belong to griddynamics domain"));
    }
}
