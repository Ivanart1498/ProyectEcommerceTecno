package com.proyectoecommercetecno.menu;

import com.proyectoecommercetecno.pedidos.Pedido;

import java.util.Scanner;

public class Menu {

        public static void main(String [] args){

            int opNum = 0;
            String nom = "";
            int Id = 0, cantidad = 0;
            double prec = 0.0;

            Pedido intervMenu = new Pedido();

            Scanner teclado = new Scanner(System.in);
            Scanner teclado1 = new Scanner(System.in);
            Scanner tcl = new Scanner(System.in);

            System.out.println("Bienvenido a TechLab:\n¿Desea ver el menu?: SI/NO");
            String opc = teclado.next();

            while (opc.equalsIgnoreCase("si")){

                System.out.println("==============================");
                System.out.println("=Sistema de Gestión - TECHLAB=");
                System.out.println("==============================");

                System.out.println("1) Agregar Producto: \n2) Listar Productos: \n3) Buscar/Actualizar Producto: " +
                        "\n4) Eliminar Producto: \n5) Crear un Pedido: \n6) Listar Pedidos: \n7) Salir");

                opNum = teclado.nextInt();

                switch (opNum){

                    case 1:
                        System.out.println("Ponga nombre del Producto: ");
                        nom = teclado1.nextLine();

                        System.out.println("Indica precio del Producto: ");
                        prec = tcl.nextDouble();
                        teclado.nextLine();

                        System.out.println("Indicar cantidad de stock del Producto: ");
                        cantidad = tcl.nextInt();

                        intervMenu.agregarProducto(nom, prec, cantidad);
                        break;

                    case 2: intervMenu.mostrarTotalProductos();
                        break;

                    case 3:
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
                        break;
                    case 4:
                        System.out.println("Ingresar el Id del Producto que desea eliminar:");
                        Id = tcl.nextInt();
                        teclado.nextLine();

                        intervMenu.eliminarProducto(Id);
                        break;
                    case 5:
                        System.out.println("Desea ordenar Producto por: 1.Nombre/2.Id");
                        op1 = tcl.nextInt();

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
                        }

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

            System.out.println("¡Gracias por usar nuestro servicios!");

        }


}
