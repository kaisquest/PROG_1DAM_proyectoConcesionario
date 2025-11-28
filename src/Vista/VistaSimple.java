package Vista;

import Modelo.CocheDTO;

import java.util.Scanner;

public class VistaSimple {
    private Scanner sc = new Scanner(System.in);


    public int pedirOpcion(String msg) {
        System.out.println(msg);

        if (){}


        return sc.nextInt();

    }

    public void mostrarError(String msg){
        System.err.println(msg);


    }
    public void mostrarCoche(CocheDTO coche) {
        System.out.println(coche.getMarca() + " " + coche.getModelo() + " " + coche.getMatricula() + " " + coche.getAnho() + " " + (coche.getKilometros() + "Km.") + " " + (coche.getPrecio()) + "€");


    }
}
