package com.grid.inventorymanager.repository;

import com.grid.inventorymanager.model.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseRepository
        extends JpaRepository<Purchase, Integer> {

}
