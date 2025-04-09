package com.grid.inventorymanager.repository;

import com.grid.inventorymanager.model.PurchaseDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface PurchaseDetailRepository extends JpaRepository<PurchaseDetail, Integer>
{
}
