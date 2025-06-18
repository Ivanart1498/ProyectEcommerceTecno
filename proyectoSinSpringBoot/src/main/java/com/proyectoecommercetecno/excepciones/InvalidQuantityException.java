package com.proyectoecommercetecno.excepciones;

public class InvalidQuantityException extends IllegalArgumentException {
  private final int requestedQuantity;
  private final int availableStock;

  public InvalidQuantityException(String message, int requestedQuantity, int availableStock) {
    super(message);
    this.requestedQuantity = requestedQuantity;
    this.availableStock = availableStock;
  }

  public int getRequestedQuantity() {
    return requestedQuantity;
  }

  public int getAvailableStock() {
    return availableStock;
  }
}
