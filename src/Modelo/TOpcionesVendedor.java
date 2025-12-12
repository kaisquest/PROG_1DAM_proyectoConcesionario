package Modelo;

public enum TOpcionesVendedor {

    PRECIO_MEDIO("El precio medio de los coches que ha vendido."),
    COCHE_CARO("Muestra el coche más caro vendido."),
    COCHES_VENDIDOS("Muestra cuántos coches ha vendido el vendedor."),
    COCHES_MARCA("Buscar por año de matriculación."),
    TOTAL_COCHES("Muestra cuánto suman los coches vendidos."),

    SALIR("Salir de la búsqueda.");

    private String opText;

    TOpcionesVendedor(String opText) {
        this.opText = opText;
    }

    public String getOpText() {
        return opText;
    }

}
