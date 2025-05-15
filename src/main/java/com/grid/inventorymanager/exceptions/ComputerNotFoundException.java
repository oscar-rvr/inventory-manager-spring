package com.grid.inventorymanager.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class ComputerNotFoundException extends RuntimeException {
    public ComputerNotFoundException(String message) {
        super(message);
    }
}