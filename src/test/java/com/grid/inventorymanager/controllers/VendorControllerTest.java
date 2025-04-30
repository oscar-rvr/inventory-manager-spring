package com.grid.inventorymanager.controllers;

import com.grid.inventorymanager.model.Vendor;
import com.grid.inventorymanager.repository.VendorRepository;
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
class VendorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private VendorRepository vendorRepository;

    @BeforeEach
    void setup() {
        vendorRepository.deleteAll();

        vendorRepository.save(Vendor.builder()
                .name("Tech Supplier")
                .contact("contact@techsupplier.com")
                .build());
    }

    @Test
    void whenGetVendors_thenReturnsViewWithModel() throws Exception {
        mockMvc.perform(get("/vendors"))
                .andExpect(status().isOk())
                .andExpect(view().name("vendors"))
                .andExpect(model().attributeExists("vendors"));
    }
}
