package com.proyectoecommercetecno.productos;

import com.proyectoecommercetecno.excepciones.*;
import com.proyectoecommercetecno.pedidos.LineaPedido;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Iterator;

public class ListaProd extends Producto {

    private ArrayList<Producto> listaProd;

    private ArrayList<LineaPedido> listaPedido;

    private LineaPedido pedido;

    private static Scanner tcl = new Scanner(System.in);

    private static String opc = "";

    private double vT;

    private boolean encontrado;

    public ListaProd(){

        listaProd = new ArrayList<Producto>();

        listaPedido = new ArrayList<LineaPedido>();

        pedido = new LineaPedido();

    }

    public ArrayList<Producto> getListaProd() {
        return listaProd;
    }

    public void agregarProducto (String nombre, double precio, int cantStock){

        if (nombre == null || nombre.trim().isEmpty()) {
            throw new InvalidInputException("El nombre del producto no puede estar vacío.");
        }
        if (precio < 0) {
            throw new InvalidInputException("El precio del producto no puede ser negativo.");
        }
        if (cantStock < 0) {
            throw new InvalidInputException("La cantidad de stock no puede ser negativa.");
        }

        Producto producto = new Producto(nombre, precio, cantStock);

        listaProd.add(producto);
    }


    public void agregarPedido (String nombre, int CantPedido) throws ProductNoEncontradoException, InvalidQuantityException, StockInsuficienteException {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new InvalidInputException("El nombre del producto para el pedido no puede estar vacío.");
        } else if (CantPedido < 0) {
            throw new InvalidInputException("La cantidad de unidades en el pedido no puede ser negativa.");
        } else {

            pedido = new LineaPedido(nombre, CantPedido);

            encontrado = false;
            for (Producto p : listaProd) {

                if (pedido.getNombre().equalsIgnoreCase(p.getNombre())) {
                    encontrado = true;
                    if (p.getCantStock() < CantPedido) {
                        throw new StockInsuficienteException(
                                "No hay suficiente stock para " + p.getNombre() + ". Stock disponible: " + p.getCantStock(),
                                nombre, CantPedido, p.getCantStock()
                        );
                    }

                    p.setCantStock(p.getCantStock() - pedido.getCantPedido());

                    LineaPedido pedido1 = new LineaPedido(p.getNombre(), p.getPrecio(), p.getCantStock(), p.getID(), pedido.getCantPedido());

                    listaPedido.add(pedido1);


                }

            }


            if (!encontrado) {
                throw new ProductNoEncontradoException("Producto '" + nombre + "' no encontrado para agregar al pedido.", nombre);
            }
        }
    }

    public void agregarPedido (int Id, int CantPedido) throws ProductNoEncontradoException, InvalidQuantityException{
        if (CantPedido < 0) {
            throw new InvalidInputException("La cantidad de unidades en el pedido debe ser positiva.");
        } else {

            pedido = new LineaPedido(Id, CantPedido);

            encontrado = false;
            for (Producto p : listaProd) {
                encontrado = true;

                if (pedido.getIde() == p.getID()) {

                    p.setCantStock(p.getCantStock() - pedido.getCantPedido());

                    LineaPedido pedido1 = new LineaPedido(p.getNombre(), p.getPrecio(), p.getCantStock(), p.getID(), pedido.getCantPedido());

                    listaPedido.add(pedido1);


                }

                if (!encontrado) {
                    throw new ProductNoEncontradoException("Producto no encontrado para agregar al pedido.", String.valueOf(Id));
                }
            }

        }

    }

    private double CalcularValor(){

        for (Producto p: listaPedido){

           vT = p.setValorTotal(p.getPrecio() * pedido.getCantPedido());
            //excepcion de tipo de numero


        }

        return vT;

    }

    public void mostrarTotalProductos () throws EmptyListException {

        if (listaProd.isEmpty()) {
            throw new EmptyListException("La lista de productos está vacía.");
        } else {

            System.out.println("Lista de Pedidos.");
            for (Producto p : listaProd) {

                System.out.println("ID: " + p.getID() + "\nNombre: " + p.getNombre() + "\nPrecio: " + p.getPrecio() + "\nCantidad de Stock: " + p.getCantStock());

            }

        }
    }



    public void mostrarPedido() throws EmptyListException {
        if (listaProd.isEmpty()) {
            throw new EmptyListException("La lista de productos está vacía.");
        } else {

            System.out.println("Lista de Pedidos.");

            for (LineaPedido p : listaPedido) {

                System.out.println("Nombre: " + p.getNombre() + "\nPrecio Unitario: " + p.getPrecio() + "\nCantidad del ListaProd: " + p.getCantPedido());

                vT += CalcularValor();
            }

            System.out.println("Precio Total: $" + vT);

        }
    }

    public Producto buscarProductosId(int Id) throws ProductNoEncontradoException, InvalidQuantityException{


        for(Producto p : listaProd){
            if(Id == p.getID()){
                System.out.println("Producto Encontrado:");
                System.out.println("ID: " + p.getID() + "\nNombre: " + p.getNombre() + "\nPrecio: "
                        + p.getPrecio() + "\nCantidad de Stock: " + p.getCantStock());

                    return p;

            }
        }
        throw new ProductNoEncontradoException("Producto no encontrado.", String.valueOf(Id));
    }

    public Producto buscarProductosNombre(String nombre) throws ProductNoEncontradoException, InvalidQuantityException{
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new InvalidInputException("El nombre del producto para el pedido no puede estar vacío.");
        } else {

            for (Producto p : listaProd) {

                if (nombre.equalsIgnoreCase(p.getNombre())) {
                    System.out.println("ID: " + p.getID() + "\nNombre: " + p.getNombre() + "\nPrecio: "
                            + p.getPrecio() + "\nCantidad de Stock: " + p.getCantStock());

                    return p;
                }
            }
            throw new ProductNoEncontradoException("Producto '" + nombre + "' no encontrado.", nombre);
        }
    }

    public void opcionesExtra(Producto p){
        System.out.println("¿Desea modificar el precio?");
        opc = tcl.next();

        if (opc.equalsIgnoreCase("Si")) {
            modificarPrecio(p);
        }

        System.out.println("¿Desea modificar el Stock?");
        opc = tcl.next();


        if (opc.equalsIgnoreCase("Si")) {

            modificarStock(p);
        }
    }

    public void modificarPrecio(Producto p){

        System.out.println("Ingrese el nuevo precio para " + p.getNombre() + ": ");
        double nuevoPrecio = 0.0;
        try {
            nuevoPrecio = tcl.nextDouble();
            p.setPrecio(nuevoPrecio); // El setter de Producto lanza InvalidInputException si es negativo
            System.out.println("Precio actualizado con éxito para " + p.getNombre() + ".");
        } catch (InputMismatchException e) {
            System.err.println("Error: El precio debe ser un número válido.");
            tcl.nextLine(); // Consumir el salto de línea
        } catch (InvalidInputException e) {
            System.err.println("Error al modificar precio: " + e.getMessage());
        }
    }

    public void modificarStock(Producto p) {
        System.out.println("Ingrese la nueva cantidad de Stock para " + p.getNombre() + ": ");
        int nuevaCantidad = 0;
        try {
            nuevaCantidad = tcl.nextInt();
            p.setCantStock(nuevaCantidad); // El setter de Producto lanza InvalidInputException si es negativo
            System.out.println("Stock actualizado con éxito para " + p.getNombre() + ".");
        } catch (InputMismatchException e) {
            System.err.println("Error: La cantidad de stock debe ser un número entero.");
            tcl.nextLine(); // Consumir el salto de línea
        } catch (InvalidInputException e) {
            System.err.println("Error al modificar stock: " + e.getMessage());
        }

    }


    public void eliminarProducto (int Id) throws ProductNoEncontradoException{

        Producto productoAEliminar = new Producto();
        try {
            productoAEliminar = buscarProductosId(Id);
        } catch (ProductNoEncontradoException e){
            throw e;
        }

        System.out.println("ID: " + productoAEliminar.getID() + "\nNombre: " + productoAEliminar.getNombre() + "\nPrecio: "
                + productoAEliminar.getPrecio() + "\nCantidad de Stock: " + productoAEliminar.getCantStock());
        System.out.println("Esta seguro de eliminar este elemento:");
        opc = tcl.next();

        if(opc.equalsIgnoreCase("Si")) {

            Iterator<Producto> it = listaProd.iterator();
            boolean removido = false;

            while (it.hasNext()){
                Producto p = it.next();
                if(p.getID() == Id){

                    it.remove();
                    removido = true;
                    break;
                }
            }

            if(removido)System.out.println("¡Operación exitosa!");
            else System.err.println("Error inesperado al eliminar el producto.");

        }else {
            System.out.println("Operación de eliminación cancelada.");
        }
    }

}
