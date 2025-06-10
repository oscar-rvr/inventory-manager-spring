package com.grid.assets.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ComputerPatchDTO {
    @Size(max = 100, message = "Name must be at most 100 characters")
    private String name;

    @Size(max = 255, message = "Description must be at most 255 characters")
    private String description;

    @Min(value = 1, message = "RAM must be at least 1 GB")
    private Integer ram;

    @Min(value = 1, message = "Disk must be at least 1 GB")
    private Integer disk;

    @Size(max = 50, message = "Core must be at most 50 characters")
    private String core;

    @Size(max = 50, message = "Screen state must be at most 50 characters")
    private String screenState;

    @Size(max = 50, message = "Keyboard state must be at most 50 characters")
    private String keyboardState;

    @Size(max = 50, message = "Shell state must be at most 50 characters")
    private String shellState;

    @Size(max = 500, message = "Comments must be at most 500 characters")
    private String comments;
}
