package Modelo;

public enum TOpciones {


    ANHADIR_COCHE("Añadir un coche."),
    MOSTRAR_COCHES("Mostrar el listado de coches disponibles."),
    MOSTRAR_ORDENADOS("Mostrar el listado de coches ordenados."),
    BUSCAR("Buscar un coche por marca, año o rango de precios."),
    BUSQUEDA_COMPLEJA("Buscar usando filtros avanzados."),
    REGISTRAR_CLIENTE("Registrar un nuevo cliente."),
    LISTAR_CLIENTES("Mostrar el listado de clientes registrados."),
    REGISTRAR_VENTA("Registrar una nueva venta."),
    LISTAR_VENTAS("Mostrar las ventas."),
    CREAR_VENDEDOR("Registra un nuevo vendedor."),
    LISTAR_VENDEDORES("Mostrar el listado de todos los vendedores."),
    ESTADISTICAS_VENDEDOR("Muestra el menú de estadíscas de un vendedor."),

    SALIR("Salir del programa.");



    private String opText;
    TOpciones(String opText) {
        this.opText = opText;
    }

    public String getOpText() {
        return opText;
    }

}
