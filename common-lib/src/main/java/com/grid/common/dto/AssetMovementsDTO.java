package com.grid.common.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AssetMovementsDTO {
    private Long assetId;
    private Long employeeId;
    private String movementType;
    private LocalDate assetMovementDate;
}
