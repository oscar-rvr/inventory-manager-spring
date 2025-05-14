package com.grid.inventorymanager.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "asset_movements")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties({"asset", "employee"})
public class AssetMovements {
    @EmbeddedId
    private AssetMovementsId id;

    @Enumerated(EnumType.STRING)
    private MovementType movementType;

    private LocalDate assetMovementDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("assetId")
    private Asset asset;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("employeeId")
    private Employee employee;

    @Override
    public String toString() {
        return "AssetMovements{" +
                "employeeId=" + (employee != null ? employee.getId() : null) +
                ", assetId=" + (asset != null ? asset.getId() : null) +
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

