package Modelo;


/**
 * Representación de los colores válidos para el sistema
 */
public enum TColores {

    RESET("\u001B[0m"),
    BLACK("\u001B[30m"),
    RED("\u001B[31m"),
    GREEN("\u001B[32m"),
    YELLOW("\u001B[33m"),
    BLUE("\u001B[34m"),
    PURPLE("\u001B[35m"),
    CYAN("\u001B[36m"),
    WHITE("\u001B[37m");


    private final String color;
    TColores(String s) {
        this.color = s;
    }

    public String getColor() {
        return color;
    }
}
