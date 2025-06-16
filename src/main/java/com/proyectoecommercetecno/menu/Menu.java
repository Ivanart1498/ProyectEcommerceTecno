package com.proyectoecommercetecno.menu;

import com.proyectoecommercetecno.productos.ListaProd;

import java.util.Scanner;

public class Menu {

    private int opNum = 0;
    private String nom = "";
    private int Id = 0, cantidad = 0;
    private double prec = 0.0;
    private String opc = "";

    private ListaProd intervMenu = new ListaProd();

    private Scanner teclado = new Scanner(System.in);
    private Scanner teclado1 = new Scanner(System.in);
    private Scanner tcl = new Scanner(System.in);

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
        return opc;
    }

    public void saludoDespedida(){
        System.out.println("¡Gracias por usar nuestro servicios!");
    }

    public void usoWhile(String o){
        opc = o;

        while (opc.equalsIgnoreCase("si")){

            System.out.println("==============================");
            System.out.println("=Sistema de Gestión - TECHLAB=");
            System.out.println("==============================");

            System.out.println("1) Agregar Producto: \n2) Listar Productos: \n3) Buscar/Actualizar Producto: " +
                    "\n4) Eliminar Producto: \n5) Crear un Pedido: \n6) Listar Pedidos: \n7) Salir");

            opNum = teclado.nextInt();

            switch (opNum){

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
                case 7: opc = "Salir";
                    break;
                default:
                    System.out.println("Valor Invalido.");
                    break;
            }
        }


    }

    public void inicarProducto(){
        System.out.println("Ponga nombre del Producto: ");
        nom = teclado1.nextLine();

        System.out.println("Indica precio del Producto: ");
        prec = tcl.nextDouble();
        teclado.nextLine();

        System.out.println("Indicar cantidad de stock del Producto: ");
        cantidad = tcl.nextInt();

        intervMenu.agregarProducto(nom, prec, cantidad);

    }

    public void buscarProd(){
        System.out.println("Desea Buscar Producto por: 1.Nombre/2.Id");
        int op1 = tcl.nextInt();

        if(op1 == 1){
            System.out.println("Ingrese el nombre:");
            nom = teclado1.nextLine();

            intervMenu.buscarProductosNombre(nom);
        } else if(op1 == 2){
            System.out.println("Ingrese el Id:");
            Id = tcl.nextInt();
            // teclado.nextLine();

            intervMenu.buscarProductosId(Id);
        } else {
            System.out.println("Opcion Invalida");
        }
    }

    public void eliminar(){
        System.out.println("Ingresar el Id del Producto que desea eliminar:");
        Id = tcl.nextInt();
        teclado.nextLine();

        intervMenu.eliminarProducto(Id);
    }

    public void orden(){
        System.out.println("Desea ordenar Producto por: 1.Nombre/2.Id");
        int op1 = tcl.nextInt();

        if(op1 == 1) {
            System.out.println("Ponga nombre del Producto que desea agregar al Pedido: ");
            nom = teclado1.nextLine();


            System.out.println("Indicar cantidad de unidades del Producto que desea agregar al Pedido: ");
            cantidad = tcl.nextInt();

            intervMenu.agregarPedido(nom, cantidad);

        } else if(op1 == 2) {
            System.out.println("Indica Id del Producto que desea agregar al Pedido: ");
            Id = tcl.nextInt();
            teclado.nextLine();

            System.out.println("Indicar cantidad de unidades del Producto que desea agregar al Pedido: ");
            cantidad = tcl.nextInt();

            intervMenu.agregarPedido(Id, cantidad);
        } else {
            System.out.println("Opcion invalida.");
        }
    }

}
