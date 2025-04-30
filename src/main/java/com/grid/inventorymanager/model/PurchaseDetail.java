package com.grid.inventorymanager.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "purchase_details")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PurchaseDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "purchase_id", nullable = false)
    private Purchase purchase; // FK

    @ManyToOne
    @JoinColumn(name = "asset_id", nullable = false)
    private Asset asset; // FK

    private Integer amount;
    private Double pricePerItem;
}
