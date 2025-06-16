package com.proyectoecommercetecno.productos;

import com.proyectoecommercetecno.pedidos.LineaPedido;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Iterator;

public class ListaProd extends Producto {

    private ArrayList<Producto> listaProd;

    private ArrayList<LineaPedido> listaPedido;

    private LineaPedido pedido;

    private static Scanner tcl = new Scanner(System.in);

    private static String opc = "";

    private double vT;

    public ListaProd(){

        listaProd = new ArrayList<Producto>();

        listaPedido = new ArrayList<LineaPedido>();

        pedido = new LineaPedido();

    }

    public void agregarProducto (String nombre, double precio, int cantStock){

        Producto producto = new Producto(nombre, precio, cantStock);

        listaProd.add(producto);
    }


    public void agregarPedido (String nombre, int CantPedido){

        pedido = new LineaPedido(nombre, CantPedido);

        for (Producto p : listaProd){

            if(pedido.getNombre().equalsIgnoreCase(p.getNombre())){

                p.setCantStock(p.getCantStock() - pedido.getCantPedido());

                LineaPedido pedido1 = new LineaPedido(p.getNombre(),  p.getPrecio(), p.getCantStock(), p.getID(), pedido.getCantPedido());

                listaPedido.add(pedido1);


            }

            //poner excepción elemento no encontrado

        }

    }

    public void agregarPedido (int Id, int CantPedido){

        pedido = new LineaPedido(Id, CantPedido);

        for (Producto p : listaProd){

            if(pedido.getIde() == p.getID()){

                p.setCantStock(p.getCantStock() - pedido.getCantPedido());

                LineaPedido pedido1 = new LineaPedido(p.getNombre(),  p.getPrecio(), p.getCantStock(), p.getID(), pedido.getCantPedido());

                listaPedido.add(pedido1);


            }

            //poner excepción elemento no encontrado
        }

    }

    private double CalcularValor(){

        for (Producto p: listaPedido){

           vT = p.setValorTotal(p.getPrecio() * pedido.getCantPedido());
            //excepcion de tipo de numero


        }

        return vT;

    }

    public void mostrarTotalProductos (){

        //excepcion de lista vacia

        for(Producto p : listaProd) {

            System.out.println("ID: " + p.getID() + "\nNombre: " + p.getNombre() + "\nPrecio: " + p.getPrecio() + "\nCantidad de Stock: " + p.getCantStock());

        }
    }



    public void mostrarPedido(){

        //excepcion de lista vacia

        for(LineaPedido p : listaPedido) {

            System.out.println("ID: " + p.getID() + "\nNombre: " + p.getNombre() + "\nPrecio Unitario: " + p.getPrecio() + "\nCantidad del ListaProd: " + p.getCantPedido());

           vT = CalcularValor();
        }

       System.out.println("Precio Total: $" + vT);
    }

    public void buscarProductosId(int Id){

        //excepcion de producto no encontrado

        for(Producto p : listaProd){
            if(Id == p.getID()){
                System.out.println("ID: " + p.getID() + "\nNombre: " + p.getNombre() + "\nPrecio: "
                        + p.getPrecio() + "\nCantidad de Stock: " + p.getCantStock());

                    opcionesExtra(p.getID());

            }
        }

    }

    public void buscarProductosNombre(String nombre){

        //excepcion de producto no encontrado

        for(Producto p: listaProd){

            if(nombre.equalsIgnoreCase(p.getNombre())){
                System.out.println("ID: " + p.getID() + "\nNombre: " + p.getNombre() + "\nPrecio: "
                        + p.getPrecio() + "\nCantidad de Stock: " + p.getCantStock());

                opcionesExtra(p.getID());
            }
        }


    }

    public void opcionesExtra(int Id){
        System.out.println("¿Desea modificar el precio?");
        opc = tcl.next();

        if (opc.equalsIgnoreCase("Si")) {
            modificarPrecio(Id);
        }

        System.out.println("¿Desea modificar el Stock?");
        opc = tcl.next();


        if (opc.equalsIgnoreCase("Si")) {

            modificarStock(Id);
        }
    }

    public void modificarPrecio(int Id){

       for(Producto p: listaProd) {

           if(Id == p.getID()) {
               System.out.println("Precio: ");
               p.setPrecio(tcl.nextDouble());
               //excepcion de tipo de numero
           }
       }
    }

    public void modificarStock(int Id) {
        for(Producto p: listaProd) {
            if(Id == p.getID()){
                System.out.println("Cantidad de Stock: ");
                p.setCantStock(tcl.nextInt());
            }
        }

    }


    public void eliminarProducto (int Id){

        //excepcion de producto no encontrado

        for(Producto p : listaProd){
            if(Id == p.getID()){
                System.out.println("ID: " + p.getID() + "\nNombre: " + p.getNombre() + "\nPrecio: "
                        + p.getPrecio() + "\nCantidad de Stock: " + p.getCantStock());

            }
        }
        System.out.println();
        //tcl.nextLine();
        System.out.println("Esta seguro de eliminar este elemento:");
        opc = tcl.nextLine();

        if(opc.equalsIgnoreCase("Si")) {

            Iterator<Producto> it = listaProd.iterator();
            
            while (it.hasNext()){
                Producto p = it.next();
                if(p.getID() == Id){
                    it.remove();
                }
            }

            System.out.println("¡Operación exitosa!");

        }
    }

}
