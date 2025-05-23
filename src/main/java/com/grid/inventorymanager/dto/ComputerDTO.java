package com.grid.inventorymanager.dto;

import com.grid.inventorymanager.validation.ValidSN;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ComputerDTO {

    // Campos heredados de Asset

    @NotBlank(message = "Name is required")
    @Size(max = 100, message = "Name must be at most 100 characters")
    private String name;

    @Size(max = 255, message = "Description must be at most 255 characters")
    private String description;

    @NotBlank(message = "Series number is required")
    @ValidSN
    @Size(max = 50, message = "Series number must be at most 8 characters")
    private String seriesNumber;

    // Campos propios de Computer

    @NotNull(message = "RAM is required")
    @Min(value = 1, message = "RAM must be at least 1 GB")
    private Integer ram;

    @NotNull(message = "Disk is required")
    @Min(value = 1, message = "Disk must be at least 1 GB")
    private Integer disk;

    @NotBlank(message = "Core is required")
    @Size(max = 50, message = "Core must be at most 50 characters")
    private String core;

    @NotBlank(message = "Screen state is required")
    @Size(max = 50, message = "Screen state must be at most 50 characters")
    private String screenState;

    @NotBlank(message = "Keyboard state is required")
    @Size(max = 50, message = "Keyboard state must be at most 50 characters")
    private String keyboardState;

    @NotBlank(message = "Shell state is required")
    @Size(max = 50, message = "Shell state must be at most 50 characters")
    private String shellState;

    @Size(max = 500, message = "Comments must be at most 500 characters")
    private String comments;
}
