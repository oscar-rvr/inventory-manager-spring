package com.grid.inventorymanager.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class PurchaseDetailNotFoundException extends RuntimeException {
    public PurchaseDetailNotFoundException(String message) {
        super(message);
    }
}
