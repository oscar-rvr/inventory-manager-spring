package com.grid.inventorymanager.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PurchaseDetailDTO {

    @NotNull(message = "Purchase ID is required")
    private Long purchaseId;

    @NotNull(message = "Asset ID is required")
    private Long assetId;

    @NotNull(message = "Amount is required")
    @Min(value = 1, message = "Amount must be at least 1")
    private Integer amount;

    @NotNull(message = "Price per item is required")
    @Min(value = 0, message = "Price per item must be positive")
    private Double pricePerItem;
}
