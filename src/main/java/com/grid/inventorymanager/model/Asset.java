package com.grid.inventorymanager.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "assets")
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@JsonIgnoreProperties({"id", "employees"})
public class Asset {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    @Column(name = "series_number", unique = true)
    private String seriesNumber;

    @OneToMany(mappedBy = "asset",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY)
    @Builder.Default
    private Set<AssetMovements> employees = new HashSet<>();

    public void addEmployee(Employee employee) {
        AssetMovementsId id = new AssetMovementsId();
        id.setAssetId(this.id);
        id.setEmployeeId(employee.getId());

        boolean alreadyExists = employees.stream().anyMatch(movement ->
                movement.getId().equals(id)
        );

        if (alreadyExists) {
            // Optionally update movementType or date here if needed
            return;
        }

        AssetMovements assetMovements = AssetMovements.builder()
                .id(id)
                .employee(employee)
                .asset(this)
                .assetMovementDate(LocalDate.now())
                .movementType(MovementType.ASSIGN)
                .build();
        employees.add(assetMovements);
        employee.getAssets().add(assetMovements);
    }

    public void removeEmployee(Employee employee) {
        for (Iterator<AssetMovements> iterator = employees.iterator(); iterator.hasNext(); ) {
            AssetMovements assetMovements = iterator.next();
            if (assetMovements.getAsset().equals(this) && assetMovements.getEmployee().equals(employee)) {
                iterator.remove();
                assetMovements.getEmployee().getAssets().remove(assetMovements);
                assetMovements.setEmployee(null);
                assetMovements.setAsset(null);
                break;
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Asset asset)) return false;
        return id != null && id.equals(asset.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
