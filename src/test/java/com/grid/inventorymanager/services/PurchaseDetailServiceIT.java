package com.grid.inventorymanager.services;

import com.grid.inventorymanager.model.*;
import com.grid.inventorymanager.repository.AssetRepository;
import com.grid.inventorymanager.repository.PurchaseDetailRepository;
import com.grid.inventorymanager.repository.PurchaseRepository;
import com.grid.inventorymanager.repository.VendorRepository;
import com.grid.inventorymanager.service.PurchaseDetailService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
@Rollback
class PurchaseDetailServiceIT {

    @Autowired
    private PurchaseDetailService purchaseDetailService;

    @Autowired
    private PurchaseDetailRepository purchaseDetailRepository;

    @Autowired
    private VendorRepository vendorRepository;

    @Autowired
    private PurchaseRepository purchaseRepository;

    @Autowired
    private AssetRepository assetRepository;

    private Purchase purchase;
    private Asset asset;
    private PurchaseDetail purchaseDetail;

    @BeforeEach
    void setUp() {
        purchaseDetailRepository.deleteAll();
        purchaseRepository.deleteAll();
        vendorRepository.deleteAll();
        assetRepository.deleteAll();

        Vendor vendor = vendorRepository.save(Vendor.builder()
                .name("Tech Supplier")
                .contact("contact@techsupplier.com")
                .build());

        purchase = purchaseRepository.save(Purchase.builder()
                .vendor(vendor)
                .date(LocalDate.now())
                .totalAmount(1000.0)
                .build());

        asset = assetRepository.save(Asset.builder()
                .name("Monitor")
                .description("27 inch 4K")
                .seriesNumber("MON-999")
                .build());

        purchaseDetail = PurchaseDetail.builder()
                .purchase(purchase)
                .asset(asset)
                .amount(2)
                .pricePerItem(500.0)
                .build();
    }

    @Test
    void whenCreate_thenPurchaseDetailIsSaved() {
        PurchaseDetail saved = purchaseDetailService.create(purchaseDetail);
        assertThat(saved.getId()).isNotNull();
        assertThat(purchaseDetailRepository.findById(saved.getId())).isPresent();
    }

    @Test
    void whenFindById_thenReturnsDetail() {
        PurchaseDetail saved = purchaseDetailService.create(purchaseDetail);
        Optional<PurchaseDetail> found = purchaseDetailService.findById(saved.getId());
        assertThat(found).isPresent().contains(saved);
    }

    @Test
    void whenFindAll_thenReturnsList() {
        purchaseDetailService.create(purchaseDetail);
        List<PurchaseDetail> all = purchaseDetailService.findAll();
        assertThat(all).hasSize(1);
    }

    @Test
    void whenUpdate_thenChangesArePersisted() {
        PurchaseDetail saved = purchaseDetailService.create(purchaseDetail);
        saved.setAmount(3);
        purchaseDetailService.update(saved);

        PurchaseDetail updated = purchaseDetailRepository.findById(saved.getId()).orElseThrow();
        assertThat(updated.getAmount()).isEqualTo(3);
    }

    @Test
    void whenDelete_thenItIsRemoved() {
        PurchaseDetail saved = purchaseDetailService.create(purchaseDetail);
        purchaseDetailService.deleteById(saved.getId());
        assertThat(purchaseDetailRepository.findById(saved.getId())).isEmpty();
    }
}
