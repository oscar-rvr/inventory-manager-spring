package com.grid.inventorymanager.dto;

import com.grid.inventorymanager.validation.ValidEmail;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class EmployeePatchDTO {

    private String name;

    @ValidEmail
    private String mail;

}
