package com.grid.inventorymanager.controllers;

import com.grid.inventorymanager.model.Asset;
import com.grid.inventorymanager.model.Purchase;
import com.grid.inventorymanager.model.PurchaseDetail;
import com.grid.inventorymanager.model.Vendor;
import com.grid.inventorymanager.repository.AssetRepository;
import com.grid.inventorymanager.repository.PurchaseDetailRepository;
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
class PurchaseDetailControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PurchaseDetailRepository purchaseDetailRepository;

    @Autowired
    private PurchaseRepository purchaseRepository;

    @Autowired
    private VendorRepository vendorRepository;

    @Autowired
    private AssetRepository assetRepository;

    @BeforeEach
    void setup() {
        purchaseDetailRepository.deleteAll();
        purchaseRepository.deleteAll();
        vendorRepository.deleteAll();
        assetRepository.deleteAll();

        Vendor vendor = vendorRepository.save(Vendor.builder()
                .name("Tech Supplier")
                .contact("contact@techsupplier.com")
                .build());

        Purchase purchase = purchaseRepository.save(Purchase.builder()
                .vendor(vendor)
                .date(LocalDate.now())
                .totalAmount(1000.0)
                .build());

        Asset asset = assetRepository.save(Asset.builder()
                .name("Laptop")
                .description("HP EliteBook")
                .seriesNumber("SN-4567")
                .build());

        purchaseDetailRepository.save(PurchaseDetail.builder()
                .purchase(purchase)
                .asset(asset)
                .amount(2)
                .pricePerItem(500.0)
                .build());
    }

    @Test
    void whenGetPurchaseDetails_thenReturnsViewWithModel() throws Exception {
        mockMvc.perform(get("/purchaseDetails"))
                .andExpect(status().isOk())
                .andExpect(view().name("purchaseDetail"))
                .andExpect(model().attributeExists("purchaseDetails"));
    }
}
