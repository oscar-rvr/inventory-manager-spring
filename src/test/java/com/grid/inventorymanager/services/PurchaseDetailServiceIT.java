package com.grid.inventorymanager.services;

import com.grid.inventorymanager.model.Asset;
import com.grid.inventorymanager.model.Purchase;
import com.grid.inventorymanager.model.PurchaseDetail;
import com.grid.inventorymanager.model.Vendor;
import com.grid.inventorymanager.repository.AssetRepository;
import com.grid.inventorymanager.repository.PurchaseDetailRepository;
import com.grid.inventorymanager.repository.PurchaseRepository;
import com.grid.inventorymanager.repository.VendorRepository;
import com.grid.inventorymanager.service.PurchaseDetailService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
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
@Disabled("need to update")
class PurchaseDetailServiceIT {

}
