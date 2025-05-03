package com.grid.inventorymanager.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "purchases")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
    } /// ver si lo agrego con el PurchaseDetail

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
                ", vendor=" + (vendor != null ? vendor : null) +
                ", date=" + (date != null ? date : null) +
                ", totalAmount=" + (vendor != null ? vendor : null) +
                '}';
    }

}
