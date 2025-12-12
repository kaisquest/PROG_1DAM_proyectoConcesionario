package Controlador;

import Modelo.*;
import Vista.VistaSimple;

import java.util.List;

public interface IControlador {
    /**
     * Función que inicializa el controlador y lo deja en ejecución.
     */
    void run();

    /**
     * Añade un coche a la lista de coches.
     * @param nuevoCoche el objeto {@link CocheDTO} que contiene el nuevo coche a añadir.
     */
    void anhadirUnCoche(CocheDTO nuevoCoche);

    /**
     * Busca en la lista todos los coches de la misma marca.
     * @param marcaBusqueda {@link String} contiene la marca de coche a buscar.
     * @return la lista de coches coincidentes.
     */
    List<CocheDTO> busquedaMarca(String marcaBusqueda);

    /**
     * Busca en la lista todos los coches inferiores a un precio.
     * @param rangoPrecio {@link Float} precio introducido para buscar.
     * @return la lista de coches con un precio inferior.
     */
    List<CocheDTO> busquedaRangoPrecio(float rangoPrecio);

    /**
     * Busca en la lista todos los coches de un año.
     * @param anhoBusqueda {@link Integer} número de año a buscar.
     * @return la lista de coches de un año.
     */
    List<CocheDTO> busquedaAnho(int anhoBusqueda);

    /**
     * Busca un coche concreto.
     * @param matricula {@link String} la matrícula que se busca.
     * @return Un coche coincidente.
     */
    CocheDTO busquedaMatricula(String matricula);

    /**
     * Busca coches en base a unos requerimientos de entrada
     * @param marcaBusqueda
     * @param anhoBusqueda
     * @param rangoPrecio
     * @return una lista de coches.
     */
    List<CocheDTO> busquedaCompleja(String marcaBusqueda, int anhoBusqueda, float rangoPrecio);

    /**
     * Muestra una lista de coches.
     * @param lista {@link List} la lista de coches a mostrar.
     */
    void mostrarCoches(List<CocheDTO> lista);

    /**
     * Registra y añade un nuevo cliente
     * @param nuevoCliente {@link ClienteDTO} el cliente a registrar.
     */
    void registrarUnCliente(ClienteDTO nuevoCliente);

    /**
     * Busca un cliente.
     * @param dni {@link String} el dni del cliente a buscar.
     * @return un cliente.
     */
    ClienteDTO busquedaCliente(String dni);

    /**
     * Muestra una lista de clientes.
     * @param lista {@link List} la lista de clientes a mostrar.
     */
    void mostrarClientes(List<ClienteDTO> lista);

    /**
     * Comprueba si existe un dni.
     * @param clienteAComprobar {@link ClienteDTO} el cliente que se busca.
     * @return true en caso de que exista, false en el contrario.
     */
    boolean comprobarDni(ClienteDTO clienteAComprobar);

    /**
     * Registra y añade una venta.
     * @param venta {@link VentaDTO} la venta a registrar.
     */
    void registrarVenta(VentaDTO venta);

    /**
     * Muestra una lista de ventas.
     * @param lista {@link List} la lista de ventas a mostrar.
     */
    void mostrarVentas(List<VentaDTO> lista);


}
