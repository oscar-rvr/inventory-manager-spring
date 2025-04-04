package com.grid.inventorymanager.repository;

import com.grid.inventorymanager.model.AssetMovements;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssetMovementsRepository extends JpaRepository<AssetMovements, Integer> {
}
