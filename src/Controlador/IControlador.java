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
     * Añade un vendedor a la lista de vendedores.
     *
     * @param nuevoVendedor el objeto {@link VendedorDTO} que contiene el nuevo vendedor a añadir.
     */
    void registrarUnVendedor(VendedorDTO nuevoVendedor);

    /**
     * Añade un coche a la lista de coches.
     *
     * @param nuevoCoche el objeto {@link CocheDTO} que contiene el nuevo coche a añadir.
     */
    void anhadirUnCoche(CocheDTO nuevoCoche);

    /**
     * Busca en la lista todos los coches de la misma marca.
     *
     * @param marcaBusqueda {@link String} contiene la marca de coche a buscar.
     * @return la lista de coches coincidentes.
     */
    List<CocheDTO> busquedaMarca(String marcaBusqueda);

    /**
     * Busca en la lista todos los coches inferiores a un precio.
     *
     * @param rangoPrecio {@link Float} precio introducido para buscar.
     * @return la lista de coches con un precio inferior.
     */
    List<CocheDTO> busquedaRangoPrecio(float rangoPrecio);

    /**
     * Busca en la lista todos los coches de un año.
     *
     * @param anhoBusqueda {@link Integer} número de año a buscar.
     * @return la lista de coches de un año.
     */
    List<CocheDTO> busquedaAnho(int anhoBusqueda);

    /**
     * Registra una venta en el sistema.
     */
    void registrarVenta();

    /**
     * Busca un vendedor en la lista de vendedores
     *
     * @param id el número {@link Integer} de id del vendedor a buscar.
     * @return un vendedor coincidente.
     */
    VendedorDTO busquedaVendedor(int id);

    /**
     * Busca un coche concreto.
     *
     * @param matricula {@link String} la matrícula que se busca.
     * @return Un coche coincidente.
     */
    CocheDTO busquedaMatricula(String matricula);


    /**
     * Ordena los coches de la lista de coches por orden alfabético.
     */
    void ordenarCochesAlfabetico();

    /**
     * Ordena los coches de la lista de coches por orden de precio.
     */
    void ordenarCochesPrecio();

    /**
     * Ordena los coches de la lista de coches por orden de año.
     */
    void ordenarCochesAnho();

    /**
     * Busca coches en base a unos requerimientos de entrada.
     *
     * @param marcaBusqueda      {@link String} la marca a buscar.
     * @param anhoBusqueda {@link Integer} el año de búsqueda.
     * @param rangoPrecio {@link Float} el precio con el que buscar.
     * @return una lista de coches.
     */
    List<CocheDTO> busquedaCompleja(String marcaBusqueda, int anhoBusqueda, float rangoPrecio);

    /**
     * Muestra una lista de coches.
     *
     * @param lista {@link List} la lista de coches a mostrar.
     */
    void mostrarCoches(List<CocheDTO> lista);

    /**
     * Registra y añade un nuevo cliente
     *
     * @param nuevoCliente {@link ClienteDTO} el cliente a registrar.
     */
    void registrarUnCliente(ClienteDTO nuevoCliente);

    /**
     * Busca un cliente.
     *
     * @param dni {@link String} el dni del cliente a buscar.
     * @return un cliente.
     */
    ClienteDTO busquedaCliente(String dni);

    /**
     * Muestra una lista de clientes.
     *
     * @param lista {@link List} la lista de clientes a mostrar.
     */
    void mostrarClientes(List<ClienteDTO> lista);

    /**
     * Comprueba si existe un dni.
     *
     * @param clienteAComprobar {@link ClienteDTO} el cliente que se busca.
     * @return true en caso de que exista, false en el contrario.
     */
    boolean comprobarDni(ClienteDTO clienteAComprobar);

    /**
     * Muestra una lista de vendedores.
     *
     * @param lista {@link List} la lista de vendedores a mostrar.
     */
    void mostrarVendedores(List<VendedorDTO> lista);

    /**
     * Registra y añade una venta.
     *
     * @param venta {@link VentaDTO} la venta a registrar.
     */
    void registrarVenta(VentaDTO venta);

    /**
     * Muestra una lista de ventas.
     *
     * @param lista {@link List} la lista de ventas a mostrar.
     */
    void mostrarVentas(List<VentaDTO> lista);

    /**
     * Comprueba si un DNI ya existe en la base de datos de vendedores.
     * @param vendedorAComprobar {@link VendedorDTO} el vendedor a comprobar.
     * @return true si existe en la base de datos, false en el caso contrario.
     */
    boolean comprobarDni(VendedorDTO vendedorAComprobar);


}
