package com.proyectoecommercetecno.pedidos;

import com.proyectoecommercetecno.productos.Producto;

public class LineaPedido extends Producto {

    private int cantPedido;

    private String nombre;

    private int Id;

    public LineaPedido(String nombre,  double precio, int cantStock,int Id, int cantPedido){
        super(nombre, precio, cantStock, Id);

        this.cantPedido = cantPedido;
    }

    public LineaPedido(String nombre, int cantPedido){
        super(nombre);
        this.cantPedido = cantPedido;
    }

    public LineaPedido(int Id, int cantPedido){
        this.Id = Id;
        this.cantPedido = cantPedido;
    }


    public LineaPedido(){}

    public LineaPedido(String nombre, int Id, int cantPedido){

        this.nombre = nombre;

        this.Id = Id;

        this.cantPedido = cantPedido;
    }

    public int getCantPedido(){
        return cantPedido;
    }

    public int getIde(){
        return Id;
    }
}
