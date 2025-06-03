package com.grid.assets.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class AssetMovementDTO {
    private Long assetId;
    private Long employeeId;
    private LocalDate assetMovementDate;
    private String movementType;
}
