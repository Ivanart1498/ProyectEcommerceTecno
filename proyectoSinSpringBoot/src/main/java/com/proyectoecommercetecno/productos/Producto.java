package com.proyectoecommercetecno.productos;

public class Producto {

    private static int Id = 1;

    private int ID;

    private String nombre;

    private double precio;

    private int cantStock;

    private double valorTotal;

    public Producto(){}

    public Producto(String nombre){
        this.nombre = nombre;

    }


    public Producto(String nombre, double precio, int cantStock, int Id){

        this.nombre = nombre;
        this.precio = precio;
        this.cantStock = cantStock;
        this.ID = getID();

    }


    public Producto(String nombre, double precio, int cantStock){

        this.nombre = nombre;
        this.precio = precio;
        this.cantStock = cantStock;
        this.ID = Id;
        Id++;

    }

    public int getID() {
        return ID;
    }

   public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public int getCantStock() {
        return cantStock;
    }


    public void setPrecio(double precio) {

        if(precio >= 0) this.precio = precio;

    }

    public void setCantStock(int cantStock) {

        if(cantStock >= 0) this.cantStock = cantStock;


    }

    public double setValorTotal(double valorTotal){

        if(valorTotal >= 0) this.valorTotal += valorTotal;
        return valorTotal;
    }




}
