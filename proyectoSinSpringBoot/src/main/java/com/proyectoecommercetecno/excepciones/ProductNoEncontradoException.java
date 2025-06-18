package com.proyectoecommercetecno.excepciones;

public class ProductNoEncontradoException extends Exception {
    private String productId;

    public ProductNoEncontradoException(String message, String productId) {

        super(message);
        this.productId = productId;
    }

    public String getProductId(){
        return productId;
    }
}
