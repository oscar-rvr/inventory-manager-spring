package com.grid.inventorymanager.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AssetMovementsId implements Serializable {

    @Column(name = "employee_id")
    private Long employeeId;

    @Column(name = "asset_id")
    private Long assetId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AssetMovementsId )) return false;
        return (employeeId != null && employeeId.equals(((AssetMovementsId) o).getEmployeeId()))
                && (assetId != null && assetId.equals(((AssetMovementsId) o).getAssetId()));
    }

    @Override
    public int hashCode() {
        return Objects.hash(employeeId, assetId);
    }
}
