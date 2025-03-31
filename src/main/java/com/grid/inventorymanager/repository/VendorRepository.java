package com.grid.inventorymanager.repository;

import com.grid.inventorymanager.model.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendorRepository extends JpaRepository<Vendor,Integer>
{
}
