package com.grid.assetmovements.repository;

import com.grid.assetmovements.model.AssetMovements;
import com.grid.assetmovements.model.AssetMovementsId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssetMovementsRepository extends JpaRepository<AssetMovements, AssetMovementsId> {
}
