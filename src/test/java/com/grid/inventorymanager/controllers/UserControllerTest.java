package com.grid.inventorymanager.controllers;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.grid.inventorymanager.dto.UserDTO;
import com.grid.inventorymanager.model.Role;
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
public class UserControllerTest {

    /*Estos son los test cases negativo para comprobar que falla cunado debe fallar y ademas que se cumple el estandar solicitado, aqui se testea */
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void whenInvalidUsername_thenReturnsValidationError() throws Exception {
        // given
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername("123");
        userDTO.setPassword("password123");
        userDTO.setRole(Role.EMPLOYEE);

        // when/then
        mockMvc.perform(post("/v1/users/2")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userDTO)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.title").value("Validation failed"))
                .andExpect(jsonPath("$.errors.username").value("Username must start with a letter and contain only letters and numbers"));
    }

    @Test
    void whenInvalidPassword_thenReturnsValidationError() throws Exception {
        // given
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername("validName");
        userDTO.setPassword("pass12");
        userDTO.setRole(Role.EMPLOYEE);

        // when/then
        mockMvc.perform(post("/v1/users/2")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userDTO)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.title").value("Validation failed"))
                .andExpect(jsonPath("$.errors.password").value("Password must be at least 8 characters"));
    }

    @Test
    void whenInvalidPasswordAndUsername_thenReturnsValidationError() throws Exception {
        // given
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername("12");
        userDTO.setPassword("pass12");
        userDTO.setRole(Role.EMPLOYEE);

        // when/then
        mockMvc.perform(post("/v1/users/2")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userDTO)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.title").value("Validation failed"))
                .andExpect(jsonPath("$.errors.username").value("Username must start with a letter and contain only letters and numbers"))
                .andExpect(jsonPath("$.errors.password").value("Password must be at least 8 characters"));
    }
}

