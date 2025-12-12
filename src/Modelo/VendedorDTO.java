package Modelo;

import java.util.ArrayList;
import java.util.List;

public class VendedorDTO {

    private int numeroVendedor;
    private String nombre;
    private String dni;
    private List<VentaDTO> ventasRealizadas;


    public VendedorDTO(String nombre, int numeroVendedor, String dni) {
        this.nombre = nombre;
        this.numeroVendedor = numeroVendedor;
        this.dni = dni;
        List<VentaDTO> ventasRealizadas = new ArrayList<>();

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

    public List<VentaDTO> getVentasRealizadas() {
        return ventasRealizadas;
    }

    public void setVentasRealizadas(List<VentaDTO> ventasRealizadas) {
        this.ventasRealizadas = ventasRealizadas;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }
}



