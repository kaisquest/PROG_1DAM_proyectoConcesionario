package Modelo;

import java.util.Date;

public class CocheDTO {
    private String marca;
    private String modelo;
    private String matricula;
    private float precio;
    private Date anho;
    private float kilometros;

    public CocheDTO(String marca, String modelo, String matricula, float precio, Date anho, float kilometros) {
        this.marca = marca;
        this.modelo = modelo;
        this.matricula = matricula;
        this.precio = precio;
        this.anho = anho;
        this.kilometros = kilometros;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public Date getAnho() {
        return anho;
    }

    public void setAnho(Date anho) {
        this.anho = anho;
    }

    public float getKilometros() {
        return kilometros;
    }

    public void setKilometros(float kilometros) {
        this.kilometros = kilometros;
    }
}
