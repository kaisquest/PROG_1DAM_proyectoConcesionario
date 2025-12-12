package Modelo;

/**
 * Clase que guarda un cliente con su DNI, Nombre y teléfono.
 */
public class ClienteDTO {
    private String dni;
    private String nombre;
    private int telefono;

    public ClienteDTO(String dni, String nombre, int telefono) {
        this.dni = dni;
        this.nombre = nombre;
        this.telefono = telefono;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }
}
