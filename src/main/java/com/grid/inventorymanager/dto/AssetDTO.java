package com.grid.inventorymanager.dto;

import com.grid.inventorymanager.validation.ValidSN;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;


@Data
public class AssetDTO {

    @NotBlank(message = "Name is required")
    @Size(max = 100, message = "Name must be at most 100 characters")
    private String name;

    @NotBlank(message = "Add a description")
    @Size(max = 255, message = "Description must be at most 255 characters")
    private String description;

    @NotBlank(message = "Series number is required")
    @Size(max = 50, message = "Series number must be at most 8 characters")
    @ValidSN
    private String seriesNumber;
}
