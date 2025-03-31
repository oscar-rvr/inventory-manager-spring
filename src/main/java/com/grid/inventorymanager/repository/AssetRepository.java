package com.grid.inventorymanager.repository;

import com.grid.inventorymanager.model.Asset;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface AssetRepository extends JpaRepository<Asset, Integer>
{
}
