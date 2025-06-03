package com.grid.assetmovements.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "asset_movements")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AssetMovements {

    @EmbeddedId
    private AssetMovementsId id;

    @Enumerated(EnumType.STRING)
    private MovementType movementType;

    private LocalDate assetMovementDate;

    @Override
    public String toString() {
        return "AssetMovements{" +
                "employeeId=" + (id != null ? id.getEmployeeId() : null) +
                ", assetId=" + (id != null ? id.getAssetId() : null) +
                ", movementType=" + movementType +
                ", date=" + assetMovementDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AssetMovements that)) return false;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
