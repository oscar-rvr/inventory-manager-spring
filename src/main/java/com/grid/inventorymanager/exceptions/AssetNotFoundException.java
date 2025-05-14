package com.grid.inventorymanager.exceptions;

public class AssetNotFoundException extends RuntimeException {
  public AssetNotFoundException(String message) {
    super(message);
  }
}
