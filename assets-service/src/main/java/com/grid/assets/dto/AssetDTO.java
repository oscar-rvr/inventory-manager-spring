package com.grid.assets.dto;

import com.grid.assets.validation.ValidSN;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Data
@SuperBuilder
@NoArgsConstructor
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
