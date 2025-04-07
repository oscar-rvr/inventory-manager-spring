package com.grid.inventorymanager.model;

import org.springframework.context.annotation.Profile;

@Profile("dev")
public enum Role {
    ADMIN,
    EMPLOYEE,
    MANAGER
}
