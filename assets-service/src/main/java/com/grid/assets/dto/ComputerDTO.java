package com.grid.assets.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class ComputerDTO extends AssetDTO {
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
