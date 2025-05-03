package com.grid.inventorymanager.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Entity
@Table(name = "purchase_details")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PurchaseDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "purchase_id")
    private Purchase purchase;

    @OneToOne
    @JoinColumn(name = "asset_id", unique = true)
    private Asset asset;

    private Integer amount;

    private Double pricePerItem;

    @Override
    public String toString() {
        return "PurchaseDetail{" +
                "purchaseId=" + (purchase != null ? purchase.getId() : null) +
                ", assetId=" + (asset != null ? asset.getId() : null) +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PurchaseDetail that)) return false;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
