package com.grid.inventorymanager.repository;

import com.grid.inventorymanager.model.PurchaseDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseDetailRepository extends JpaRepository<PurchaseDetail, Integer>
{
}
