package Modelo;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase que guarda un venedor con su Número de vendedor,
 * su Nombre y su DNI.
 */
public class VendedorDTO {

    private int numeroVendedor;
    private String nombre;
    private String dni;



    public VendedorDTO(String nombre, int numeroVendedor, String dni) {
        this.nombre = nombre;
        this.numeroVendedor = numeroVendedor;
        this.dni = dni;


    }

    public int getNumeroVendedor() {
        return numeroVendedor;
    }

    public void setNumeroVendedor(int numeroVendedor) {
        this.numeroVendedor = numeroVendedor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }
}



