package com.grid.inventorymanager.repository;

import com.grid.inventorymanager.model.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface VendorRepository extends JpaRepository<Vendor,Integer>
{
}
