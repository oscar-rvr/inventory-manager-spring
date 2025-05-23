package com.grid.inventorymanager.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "purchases")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties({"vendor", "details"})
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Vendor vendor;

    private LocalDate date;

    private Double totalAmount;

    @OneToMany(mappedBy = "purchase",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY)
    @Builder.Default
    private Set<PurchaseDetail> details = new HashSet<>();

    public void addDetail(Asset asset, Integer amount, Double pricePerItem) {
        PurchaseDetail detail = PurchaseDetail.builder()
                .purchase(this)
                .amount(amount)
                .asset(asset)
                .pricePerItem(pricePerItem)
                .build();
        details.add(detail);
    }//O: que hace esto?

    public void removeDetail(PurchaseDetail detail) {
        details.remove(detail);
        detail.setPurchase(null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Purchase purchase)) return false;
        return id != null && id.equals(purchase.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Purchase{" +
                "id=" + (id != null ? id : null) +
                ", date=" + (date != null ? date : null) +
                ", totalAmount=" + (totalAmount != null ? totalAmount : null) +
                ", vendorId=" + (vendor != null ? vendor.getId() : null) +
                '}';
    }
}
