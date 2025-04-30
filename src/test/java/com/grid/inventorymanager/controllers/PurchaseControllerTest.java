package com.grid.inventorymanager.controllers;

import com.grid.inventorymanager.model.Purchase;
import com.grid.inventorymanager.model.Vendor;
import com.grid.inventorymanager.repository.PurchaseRepository;
import com.grid.inventorymanager.repository.VendorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class PurchaseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PurchaseRepository purchaseRepository;

    @Autowired
    private VendorRepository vendorRepository;

    @BeforeEach
    void setup() {
        purchaseRepository.deleteAll();
        vendorRepository.deleteAll();

        Vendor vendor = vendorRepository.save(Vendor.builder()
                .name("Tech Supplier")
                .contact("contact@techsupplier.com")
                .build());

        purchaseRepository.save(Purchase.builder()
                .vendor(vendor)
                .date(LocalDate.now())
                .totalAmount(1200.00)
                .build());
    }

    @Test
    void whenGetPurchases_thenReturnsViewWithModel() throws Exception {
        mockMvc.perform(get("/purchases"))
                .andExpect(status().isOk())
                .andExpect(view().name("purchases"))
                .andExpect(model().attributeExists("purchases"));
    }
}
