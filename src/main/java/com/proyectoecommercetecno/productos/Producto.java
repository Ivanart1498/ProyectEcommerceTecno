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
        else System.out.println("Error. El valor ingresado es invalido.");
    }

    public void setCantStock(int cantStock) {

        if(cantStock >= 0) this.cantStock = cantStock;
        else System.out.println("Error. El valor ingresado es invalido.");
        /*excepcion numero negativo
          excepción tipo de dato invalido
         */
    }

    public double setValorTotal(double valorTotal){

        if(valorTotal >= 0) this.valorTotal = valorTotal;
        /*excepcion numero negativo
          excepción tipo de dato invalido
         */
        return valorTotal;
    }




}
