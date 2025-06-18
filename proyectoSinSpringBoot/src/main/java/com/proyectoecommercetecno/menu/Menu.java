package com.proyectoecommercetecno.menu;

import com.proyectoecommercetecno.productos.ListaProd;
import com.proyectoecommercetecno.productos.Producto;
import com.proyectoecommercetecno.excepciones.ProductNoEncontradoException;
import com.proyectoecommercetecno.excepciones.InvalidQuantityException;
import com.proyectoecommercetecno.excepciones.StockInsuficienteException;
import com.proyectoecommercetecno.excepciones.EmptyListException;
import com.proyectoecommercetecno.excepciones.InvalidInputException;


import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {

    private int opNum = 0;
    private String nom = "";
    private int Id = 0, cantidad = 0;
    private double prec = 0.0;
    private String opc = "";

    private ListaProd intervMenu = new ListaProd();
    private Scanner teclado = new Scanner(System.in);


    public void ejecutarMenu(){
        opc = saludoBienvenida();

        if(opc.equalsIgnoreCase("si")){
            usoWhile(opc);
        }
        saludoDespedida();

    }

    public String saludoBienvenida(){
        System.out.println("Bienvenido a TechLab:\n¿Desea ver el menu?: SI/NO");
        opc = teclado.next();
        teclado.nextLine();
        return opc;
    }

    public void saludoDespedida(){
        System.out.println("¡Gracias por usar nuestro servicios!");
    }

    public void usoWhile(String o){

        try {
            opc = o;

            while (opc.equalsIgnoreCase("si")) {

                System.out.println("==============================");
                System.out.println("=Sistema de Gestión - TECHLAB=");
                System.out.println("==============================");

                System.out.println("1) Agregar Producto: \n2) Listar Productos: \n3) Buscar/Actualizar Producto: " +
                        "\n4) Eliminar Producto: \n5) Crear un Pedido: \n6) Listar Pedidos: \n7) Salir");

                opNum = teclado.nextInt();
                teclado.nextLine();

                switch (opNum) {

                    case 1:
                        inicarProducto();
                        break;
                    case 2:
                        intervMenu.mostrarTotalProductos();
                        break;
                    case 3:
                        buscarProd();
                        break;
                    case 4:
                        eliminar();
                        break;
                    case 5:
                        orden();
                        break;
                    case 6:
                        intervMenu.mostrarPedido();
                        break;
                    case 7:
                        opc = "Salir";
                        break;
                    default:
                        System.out.println("Valor Invalido.");
                        break;
                }
            }
        } catch (InputMismatchException e) {
            System.err.println("Error: Entrada inválida. Por favor, ingrese un número para seleccionar una opción.");
            teclado.nextLine();
        } catch (EmptyListException | InvalidQuantityException | StockInsuficienteException e) {
            System.err.println("Error de operación: " + e.getMessage());
        } catch (InvalidInputException e) {
            System.err.println("Error de entrada: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Ocurrió un error inesperado: " + e.getMessage());
            e.printStackTrace();
        }

        teclado.close();
    }



    public void inicarProducto(){
        System.out.println("Ponga nombre del Producto: ");

        nom = teclado.nextLine();

        System.out.println("Indica precio del Producto: ");
        prec = teclado.nextDouble();
        teclado.nextLine();

        System.out.println("Indicar cantidad de stock del Producto: ");
        cantidad = teclado.nextInt();

        intervMenu.agregarProducto(nom, prec, cantidad);

    }

    public void buscarProd(){
        System.out.println("Desea Buscar Producto por: 1.Nombre/2.Id");


        try {
            int op1 = teclado.nextInt();
            teclado.nextLine();

            Producto productoEncontrado = new Producto();
            if (op1 == 1) {
                System.out.println("Ingrese el nombre:");
                nom = teclado.nextLine();
                productoEncontrado = intervMenu.buscarProductosNombre(nom);
                System.out.println("--- Producto Encontrado ---"); // Muestra el producto solo si se encuentra
                System.out.println("ID: " + productoEncontrado.getID() + "\nNombre: " + productoEncontrado.getNombre() + "\nPrecio: "
                        + productoEncontrado.getPrecio() + "\nCantidad de Stock: " + productoEncontrado.getCantStock());
                intervMenu.opcionesExtra(productoEncontrado);
            } else if (op1 == 2) {
                System.out.println("Ingrese el Id:");
                Id = teclado.nextInt();
                teclado.nextLine();

                System.out.println("ID: " + productoEncontrado.getID() + "\nNombre: " + productoEncontrado.getNombre() + "\nPrecio: "
                        + productoEncontrado.getPrecio() + "\nCantidad de Stock: " + productoEncontrado.getCantStock());
                intervMenu.opcionesExtra(productoEncontrado);
            } else {
                System.out.println("Opcion Invalida");
            }
        }catch (InputMismatchException e) {
            System.err.println("Error: Entrada inválida. Por favor, ingrese 1 o 2.");
            teclado.nextLine();
        } catch (ProductNoEncontradoException e) { //
            System.err.println("Error al buscar producto: " + e.getMessage());
        } catch (InvalidInputException e) {
            System.err.println("Error de entrada para la búsqueda: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Ocurrió un error inesperado durante la búsqueda: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void eliminar(){
        System.out.println("Ingresar el Id del Producto que desea eliminar:");
        try {
            Id = teclado.nextInt();
            teclado.nextLine(); // Consumir el salto de línea
            intervMenu.eliminarProducto(Id); // Llama a eliminarProducto, que ahora lanza ProductNotFoundException
        } catch (InputMismatchException e) {
            System.err.println("Error: El ID debe ser un número entero.");
            teclado.nextLine();
        } catch (ProductNoEncontradoException e) { // ¡Captura ProductNotFoundException aquí!
            System.err.println("Error al eliminar producto: " + e.getMessage());
        } catch (InvalidInputException e) { // Captura InvalidInputException si el ID es inválido (ej. <= 0)
            System.err.println("Error de entrada para eliminar: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Ocurrió un error inesperado al eliminar: " + e.getMessage());
            e.printStackTrace();
        }
    }


    public void orden() throws ProductNoEncontradoException, StockInsuficienteException {
        System.out.println("Desea ordenar Producto por: 1.Nombre/2.Id");
        int op1 = teclado.nextInt();
        try {
            if (op1 == 1) {
                System.out.println("Ponga nombre del Producto que desea agregar al Pedido: ");
                nom = teclado.nextLine();


                System.out.println("Indicar cantidad de unidades del Producto que desea agregar al Pedido: ");
                cantidad = teclado.nextInt();

                intervMenu.agregarPedido(nom, cantidad);

                System.out.println("Stock Insuficiente.");


            } else if (op1 == 2) {

                System.out.println("Indicar cantidad de unidades del Producto que desea agregar al Pedido: ");
                cantidad = teclado.nextInt();

                intervMenu.agregarPedido(Id, cantidad);

                System.out.println("Stock Insuficiente.");

            } else {
                System.out.println("Opcion invalida.");
            }
        } catch (InputMismatchException e) {
            System.err.println("Error: Entrada inválida. Por favor, ingrese 1 o 2.");
            teclado.nextLine(); // Limpiar el buffer
        } catch (ProductNoEncontradoException | InvalidInputException | InvalidQuantityException e) {
            // Captura otras excepciones de negocio que pueden venir de agregarPedido
            System.err.println("Error al crear pedido: " + e.getMessage());
        } catch (StockInsuficienteException e) { // ¡NUEVO BLOQUE CATCH ESPECÍFICO!
            System.err.println("Error de stock: " + e.getMessage() +
                    "\n  -> Producto: " + e.getProductName() +
                    "\n  -> Solicitaste: " + e.getRequestedQuantity() +
                    "\n  -> Disponible: " + e.getAvailableStock());
        } catch (Exception e) { // Captura cualquier otra excepción inesperada
            System.err.println("Ocurrió un error inesperado al crear el pedido: " + e.getMessage());
            e.printStackTrace();
        }
    }

}
