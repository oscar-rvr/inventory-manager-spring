package com.grid.inventorymanager.dto;

import com.grid.inventorymanager.model.MovementType;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class AssetMovementsDTO {

    @NotNull(message = "Asset ID is required")
    private Long assetId;

    @NotNull(message = "Employee ID is required")
    private Long employeeId;

    @NotNull(message = "Movement type is required")
    private MovementType movementType;

    @NotNull(message = "Asset movement date is required")
    private LocalDate assetMovementDate;
}
