package com.proyectoecommercetecno.excepciones;

public class StockInsuficienteException extends Exception{

    private final String productName;
    private final int requestedQuantity;
    private final int availableStock;

    public StockInsuficienteException(String message, String productName, int requestedQuantity, int availableStock) {
        super(message);
        this.productName = productName;
        this.requestedQuantity = requestedQuantity;
        this.availableStock = availableStock;
    }

    public String getProductName() {
        return productName;
    }

    public int getRequestedQuantity() {
        return requestedQuantity;
    }

    public int getAvailableStock() {
        return availableStock;
    }
}
