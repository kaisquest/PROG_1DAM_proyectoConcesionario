package Vista;

import Modelo.CocheDTO;
import Modelo.TOpciones;

import java.util.Scanner;

public class VistaSimple {
    private Scanner sc = new Scanner(System.in);


    public TOpciones mostrarMenu() {
        System.out.println("\n"+"Bienvenido al programa de gestión concesionario.");
        imprimirLinea("=");
        TOpciones[] opciones = TOpciones.values();
        for (int i = 0; i < opciones.length; i++) {
            System.out.println((i + 1) + ". " + opciones[i].getOpText());

        }
        imprimirLinea("=");
        int op = sc.nextInt();
        sc.nextLine();
        int opUsuario;

        while (true) {
            opUsuario = sc.nextInt();
            sc.nextLine();
            if (opUsuario >= 1 && opUsuario <= opciones.length) {
                return opciones[opUsuario - 1];
            }
            //printMessage("Opción incorrecta", TColors.RED);
        }


    }


    public int pedirOpcion(String msg) {
        System.out.println(msg);


        return sc.nextInt();

    }

    public void mostrarError(String msg) {
        System.err.println(msg);


    }

    public void mostrarCoche(CocheDTO coche) {
        System.out.println(coche.getMarca() + " " + coche.getModelo() + " " + coche.getMatricula() + " "
                + coche.getAnho() + " " + (coche.getKilometros() + "Km.") + " " + (coche.getPrecio()) + "€");


    }

    private void imprimirLinea(String mensaje){
        System.out.println("\n"+"======================"+ mensaje + "==========================="
                + "\n");
    }



}

