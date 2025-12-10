package Modelo;

public enum TOpcionesRegistro {
    SI("Sí"),
    NO("No");

    private String opText;

    TOpcionesRegistro(String opText) {
        this.opText = opText;
    }

    public String getOpText() {
        return opText;
    }

}