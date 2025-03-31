package com.grid.inventorymanager.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "purchase_details")
@Data
@NoArgsConstructor
@AllArgsConstructor
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
