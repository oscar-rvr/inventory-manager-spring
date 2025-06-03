package com.grid.employees.dto;

import com.grid.employees.validation.ValidEmail;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class EmployeeDTO {

    @NotBlank(message = "Name is required")
    @Size(min = 4, max = 20, message = "Name must be between 4 and 20 characters")
    private String name;

    @NotBlank(message = "Email is required")
    @ValidEmail
    private String mail;
}
