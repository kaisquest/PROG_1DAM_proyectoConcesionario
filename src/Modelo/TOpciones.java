package Modelo;

public enum TOpciones {


    ANHADIR_COCHE("Añadir un coche."),
    MOSTRAR_COCHES("Mostrar el listado de coches disponibles."),
    BUSCAR("Buscar un coche por marca, año o rango de precios."),
    REGISTRAR_CLIENTE("Registrar un uevo cliente."),
    REGISTRAR_VENTA("Registrar una nueva venta."),
    LISTAR_VENTAS("Mostrar las ventas."),
    SALIR("Salir del programa.");


    private String opText;
    TOpciones(String opText) {
        this.opText = opText;
    }

    public String getOpText() {
        return opText;
    }

}
