package com.grid.inventorymanager.services;

import com.grid.inventorymanager.model.Vendor;
import com.grid.inventorymanager.repository.VendorRepository;
import com.grid.inventorymanager.service.VendorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
@Rollback
@Disabled("need to update")

class VendorServiceIT {

    @Autowired
    private VendorService vendorService;

    @Autowired
    private VendorRepository vendorRepository;

    private Vendor vendor;

    @BeforeEach
    void setUp() {
        vendorRepository.deleteAll();

        vendor = Vendor.builder()
                .name("Tech Solutions")
                .contact("info@techsolutions.com")
                .build();
    }

    @Test
    void whenCreate_thenVendorIsSaved() {
        Vendor saved = vendorService.create(vendor);
        assertThat(saved.getId()).isNotNull();
        assertThat(vendorRepository.findById(saved.getId())).isPresent();
    }

    @Test
    void whenFindById_thenReturnsVendor() {
        Vendor saved = vendorService.create(vendor);
        Optional<Vendor> found = vendorService.findById(saved.getId());
        assertThat(found).isPresent().contains(saved);
    }

    @Test
    void whenFindAll_thenReturnsList() {
        vendorService.create(vendor);
        List<Vendor> all = vendorService.findAll();
        assertThat(all).hasSize(1);
    }

    @Test
    void whenUpdate_thenChangesArePersisted() {
        Vendor saved = vendorService.create(vendor);
        saved.setContact("newemail@tech.com");
        vendorService.update(saved.getId(), saved);

        Vendor updated = vendorRepository.findById(saved.getId()).orElseThrow();
        assertThat(updated.getContact()).isEqualTo("newemail@tech.com");
    }

    @Test
    void whenDelete_thenVendorIsRemoved() {
        Vendor saved = vendorService.create(vendor);
        vendorService.deleteById(saved.getId());
        assertThat(vendorRepository.findById(saved.getId())).isEmpty();
    }
}
