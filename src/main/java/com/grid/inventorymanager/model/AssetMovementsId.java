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
        if (o == null || getClass() != o.getClass()) return false;
        AssetMovementsId that = (AssetMovementsId) o;
        return Objects.equals(employeeId, that.employeeId) &&
                Objects.equals(assetId, that.assetId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employeeId, assetId);
    }
}
