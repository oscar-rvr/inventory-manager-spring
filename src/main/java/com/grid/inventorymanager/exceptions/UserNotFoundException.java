package com.grid.inventorymanager.exceptions;

//@ResponseStatus(code = HttpStatus.NOT_FOUND)Esto ahora lo manejamos desde el global
public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super(message);
    }
}
