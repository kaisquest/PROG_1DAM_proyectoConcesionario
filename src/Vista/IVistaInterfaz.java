package Vista;

import Modelo.*;

public interface IVistaInterfaz {

    /**
     * Muestra el menú de opciones, preguntando al usuario cuál quiere hacer.
     * @return el número de la opción a realizar
     */
    TOpciones mostrarMenu();

    /**
     * Muestra el menú de opciones para ordenar la lista de coches, preguntando al usuario cuál quiere hacer.
     * @return el número de la opción a realizar
     */
    TOpcionesOrdenar menuOpcionesOrdenar();

    /**
     * Muestra el menú de opciones de búsqueda, preguntando al usuario cuál quiere hacer.
     * @return el número de la opción a realizar
     */
    TOpcionesMenu menuOpcionesBusqueda();

    /**
     * Muestra el menú de opciones de registro, preguntando al usuario cuál quiere hacer.
     * @return el número de la opción a realizar
     */
    TOpcionesRegistro menuOpcionesRegistro(String mensaje);

    //TOpcionesVendedor menuOpcionesVendedor(String mensaje);

    /**
     * Pide los datos al usuario para crear una venta.
     * @param idVenta {@link Integer} el número de la venta que se va a registrar.
     * @param cliente {@link ClienteDTO} el cliente asociado a esa venta.
     * @param coche {@link CocheDTO} el coche asociado a esa venta.
     * @param precioVenta {@link Float} el precio de la venta.
     * @return una venta.
     */
    VentaDTO registrarVenta(int idVenta, ClienteDTO cliente, CocheDTO coche, float precioVenta, VendedorDTO vendedor);

    /**
     * Pide los datos al usuario para registrar un coche.
     * @return un coche.
     */
    CocheDTO anhadirCoche();

    /**
     * Pide los datos al usuario para registrar un cliente.
     * @return un cliente.
     */
    ClienteDTO registrarCliente();


    /**
     * Pide los datos al usuario para registrar un vendedor.
     * @return un vendedor.
     */
    VendedorDTO registrarVendedor(int numeroVendedor);

    /**
     * Pide una cadena.
     * @param mensaje {@link String} mensaje personalizado para el usuario.
     * @return la cadena introducida por el usuario.
     */
    String pedirCadena(String mensaje, boolean permiteVacio);

    /**
     * Pide un número en coma flotante.
     * @param mensaje {@link String} mensaje personalizado para el usuario.
     * @return el número introducido por el usuario.
     */
    float pedirFloat(String mensaje);

    /**
     * Pide un número natural.
     * @param mensaje {@link String} mensaje personalizado para el usuario.
     * @return el número introducido por el usuario.
     */
    int pedirInt(String mensaje);

    /**
     * Muestra un coche.
     * @param coche {@link CocheDTO} el coche a mostrar.
     */
    void mostrarCoche(CocheDTO coche);

    /**
     * Muestra una venta.
     * @param venta {@link VentaDTO} la venta a mostrar.
     */
    void mostrarVenta(VentaDTO venta);

    /**
     * Muestra un vendedor
     * @param vendedor {@link VentaDTO} el vendedor a mostrar.
     */
    void mostrarVendedor(VendedorDTO vendedor);

    /**
     * Muestra un cliente.
     * @param cliente {@link ClienteDTO} el cliente a mostrar.
     */
    void mostrarCliente(ClienteDTO cliente);

    /**
     * Imprime un mensaje por pantalla
     * @param mensaje {@link String} el mensaje que se tiene qye imprimir.
     * @param color {@link TColores} el color del mensaje a imprimir.
     */
    void imprimirMensaje(String mensaje, TColores color);

    /**
     * Imprime el mensaje de salida cuando se cierra el programa.
     */
    void mostrarMensajeSalida();

    /**
     * Solicita al usuario que se pulse una tecla para continuar la ejecución
     */
    void pulsaParaContinuar();

    /**
     * Imprime el logo del concesionario.
     */
    void imprimirLogo();

    /**
     * Imprime un separador entre mensajes.
     * @param mensaje {@link String} introduce mensaje personalizado entre el separador.
     */
    void imprimirSeparador(String mensaje);



}
