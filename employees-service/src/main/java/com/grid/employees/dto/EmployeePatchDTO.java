package com.grid.employees.dto;

import com.grid.employees.validation.ValidEmail;
import lombok.Data;

@Data
public class EmployeePatchDTO {

    private String name;

    @ValidEmail
    private String mail;

}
