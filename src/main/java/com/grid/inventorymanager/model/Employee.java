package com.grid.inventorymanager.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.sql.DatabaseMetaData;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "employees")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties({"id", "assets"})
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String mail;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "employee",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY)
    @Builder.Default
    private Set<AssetMovements> assets = new HashSet<>();

    @OneToOne(mappedBy = "employee",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY)
    private User user;

    public void addAsset(Asset asset, MovementType movementType, LocalDate date) {
        AssetMovementsId id = new AssetMovementsId();
        id.setAssetId(asset.getId());
        id.setEmployeeId(this.id);

        AssetMovements assetMovements = AssetMovements.builder()
                .id(id)
                .employee(this)
                .asset(asset)
                .assetMovementDate(date)
                .movementType(movementType)
                .build();

        this.assets.add(assetMovements);
        asset.getEmployees().add(assetMovements);
    }

    public void removeAsset(Asset asset) {
        for (Iterator<AssetMovements> iterator = assets.iterator(); iterator.hasNext(); ) {
            AssetMovements assetMovements = iterator.next();
            if (assetMovements.getEmployee().equals(this) && assetMovements.getAsset().equals(asset)) {
                iterator.remove();
                assetMovements.getAsset().getEmployees().remove(assetMovements);
                assetMovements.setEmployee(null);
                assetMovements.setAsset(null);
                break;
            }
        }
    }

    public void addUser(User user) {
        this.user = user;
        user.setEmployee(this);
    }

    public void removeUser() {
        this.user.setEmployee(null);
        this.user = null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee employee)) return false;
        return id != null && id.equals(employee.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}