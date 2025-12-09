package Modelo;

public enum TOpcionesMenu {
    MARCA("Buscar por marca"),
    RANGO("Buscar por rango de precios,"),
    ANHO("Buscar por año de matriculación"),

    SALIR("Salir de la búsqueda.");

    private String opText;

    TOpcionesMenu(String opText) {
        this.opText = opText;
    }

    public String getOpText() {
        return opText;
    }

}