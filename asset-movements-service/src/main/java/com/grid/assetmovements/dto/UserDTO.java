package com.grid.assetmovements.dto;

import lombok.Data;

@Data
public class UserDTO {
    private String username;
    private String role; // Lo pasamos como String si solo necesitas mostrarlo
}