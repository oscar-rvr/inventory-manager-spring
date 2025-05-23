package com.grid.inventorymanager.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class PurchaseDTO {

    @NotNull(message = "Vendor ID is required")
    private Long vendorId;

    @NotNull(message = "Date is required")
    private LocalDate date;

    @NotNull(message = "Total amount is required")
    @Min(value = 0, message = "Total amount must be positive")
    private Double totalAmount;
}
