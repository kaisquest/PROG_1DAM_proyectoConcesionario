package Modelo;

public enum TOpcionesOrdenar {
    MARCA_ORDEN("Ordenar por marca"),
    RANGO_ORDEN("Ordenar por rango de precios."),
    ANHO_ORDEN("Ordenar por año de matriculación."),

    SALIR("Salir de la búsqueda.");

    private String opText;

    TOpcionesOrdenar(String opText) {
        this.opText = opText;
    }

    public String getOpText() {
        return opText;
    }

}

