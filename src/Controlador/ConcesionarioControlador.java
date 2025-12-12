package Controlador;

import Modelo.*;
import Vista.VistaSimple;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ConcesionarioControlador implements IControlador {

    //Atributos
    private static final int CNT_ORIGIN = 0;
    public static final float IVA = 1.21f;
    public static final float COMISION_VENTA = 0.0f;
    private VistaSimple vista;
    private int cnt = CNT_ORIGIN;
    List<CocheDTO> listadoCoches;
    List<ClienteDTO> listadoClientes;
    List<VentaDTO> listadoVentas;
    int contadorVentas = 3;


    //Funciones públicas

    @Override
    public void run() {
        vista.imprimirLogo();
        vista.imprimirMensaje("\n" + "Bienvenido al programa de gestión concesionario.", TColores.GREEN);
        while (true) {
            TOpciones opciones = vista.mostrarMenu();
            if (opciones == TOpciones.ANHADIR_COCHE) {
                CocheDTO nuevoCoche = vista.anhadirCoche();
                anhadirUnCoche(nuevoCoche);
                vista.pulsaParaContinuar();


            }
            if (opciones == TOpciones.BUSCAR) {

                while (true) {
                    TOpcionesMenu opcionesMenu = vista.menuOpcionesBusqueda();
                    if (opcionesMenu == TOpcionesMenu.MARCA) {
                        mostrarCoches(busquedaMarca(vista.pedirCadena("Introduce la marca de coche.")));
                        vista.menuOpcionesBusqueda();

                    }
                    if (opcionesMenu == TOpcionesMenu.RANGO) {
                        mostrarCoches(busquedaRangoPrecio(vista.pedirFloat("Introduce un precio en €.")));
                    }
                    if (opcionesMenu == TOpcionesMenu.ANHO) {
                        mostrarCoches(busquedaAnho(vista.pedirInt("Introduce el año del coche.")));
                    }
                    if (opcionesMenu == TOpcionesMenu.SALIR) {
                        break;

                    }
                }
            }
            if (opciones == TOpciones.BUSQUEDA_COMPLEJA) {
                mostrarCoches(busquedaCompleja((vista.pedirCadena("Introduce la marca de coche.")),
                        (vista.pedirInt("Introduce el año del coche.")),
                        (vista.pedirFloat("Introduce un precio en €."))));
                vista.pulsaParaContinuar();

            }

            if (opciones == TOpciones.LISTAR_VENTAS) {
                mostrarVentas(listadoVentas);
                vista.pulsaParaContinuar();

            }
            if (opciones == TOpciones.MOSTRAR_COCHES) {
                mostrarCoches(listadoCoches);
                vista.pulsaParaContinuar();
            }
            if (opciones == TOpciones.MOSTRAR_ORDENADOS) {
                ordenarCoches();
                vista.pulsaParaContinuar();
            }
            if (opciones == TOpciones.REGISTRAR_CLIENTE) {
                ClienteDTO nuevoCliente = vista.registrarCliente();
                if (comprobarDni(nuevoCliente)) {
                    vista.imprimirMensaje("El cliente ya existe en la base de datos.", TColores.RED);

                } else {
                    registrarUnCliente(nuevoCliente);
                    vista.pulsaParaContinuar();
                }

            }
            if (opciones == TOpciones.REGISTRAR_VENTA) {

                //Preguntar cómo extraer esto
                vista.imprimirMensaje("Por favor, introduce los datos de la venta", TColores.GREEN);


                String dni = vista.pedirCadena("Introduce el DNI del cliente.");
                ClienteDTO clienteBusqueda = busquedaCliente(dni);
                int comprobar = comprobarClienteExiste(clienteBusqueda);
                if (comprobar == -1) {
                    continue;
                }
                if (comprobar == 1) {
                    clienteBusqueda = busquedaCliente(dni);
                }


                String matricula = vista.pedirCadena("Introduce la matrícula del coche");
                CocheDTO cocheBusqueda = busquedaMatricula(matricula);
                ;

                if (!comprobarMatriculaExiste(cocheBusqueda)) {
                    continue;
                }
                if (!comprobarVendido(cocheBusqueda)) {
                    continue;
                }


                float precio = calcularTotal(vista.pedirFloat("Introduce el precio de venta"));
                VentaDTO nuevaVenta = vista.registrarVenta(contadorVentas, clienteBusqueda, cocheBusqueda, precio);


                //VentaDTO nuevaVenta = vista.registrarVenta((contadorVentas), busquedaCliente(vista.pedirCadena("Introduce el DNI del cliente.")),
                //        busquedaMatricula(vista.pedirCadena("Introduce la matrícula del coche")),
                //        calcularTotal(vista.pedirFloat("Introduce el precio de venta")));

                contadorVentas++;
                registrarVenta(nuevaVenta);


            }

            if (opciones == TOpciones.LISTAR_CLIENTES) {
                mostrarClientes(listadoClientes);
                vista.pulsaParaContinuar();
            }
            if (opciones == TOpciones.SALIR) {
                vista.mostrarMensajeSalida();
                break;
            }

        }

    }

    @Override
    public CocheDTO busquedaMatricula(String matricula) {

        for (CocheDTO coche : listadoCoches) {
            if (coche.getMatricula().equalsIgnoreCase(matricula)) {
                return coche;
            }

        }

        return null;


    }

    @Override
    public ClienteDTO busquedaCliente(String dni) {

        for (ClienteDTO cliente : listadoClientes) {
            if (cliente.getDni().equals(dni)) {
                return cliente;
            }

        }

        return null;


    }

    @Override
    public List<CocheDTO> busquedaRangoPrecio(float rangoPrecio) {

        List<CocheDTO> listadoOcurrencias = new ArrayList<>();
        cnt = CNT_ORIGIN;
        for (CocheDTO coche : listadoCoches) {
            if (rangoPrecio >= coche.getPrecio()) {
                listadoOcurrencias.add(coche);
                cnt++;
            }

        }
        if (cnt == CNT_ORIGIN) {
            return null;
        } else {
            return listadoOcurrencias;
        }


    }

    @Override
    public List<CocheDTO> busquedaAnho(int anhoBusqueda) {
        List<CocheDTO> listadoOcurrencias = new ArrayList<>();
        cnt = CNT_ORIGIN;
        for (CocheDTO coche : listadoCoches) {
            if (coche.getAnho() == anhoBusqueda) {
                listadoOcurrencias.add(coche);
                cnt++;
            }

        }
        if (cnt == CNT_ORIGIN) {
            return null;
        } else {
            return listadoOcurrencias;
        }

    }

    @Override
    public List<CocheDTO> busquedaMarca(String marcaBusqueda) {
        List<CocheDTO> listadoOcurrencias = new ArrayList<>();
        cnt = CNT_ORIGIN;
        for (CocheDTO coche : listadoCoches) {
            if (coche.getMarca().equals(marcaBusqueda)) {
                listadoOcurrencias.add(coche);
                cnt++;
            }

        }
        if (cnt == CNT_ORIGIN) {
            return null;
        } else {
            return listadoOcurrencias;
        }

    }

    public void ordenarCoches() {
        List<CocheDTO> ordenPorMarca = new ArrayList<>();
        List<CocheDTO> listaOrdenada = new ArrayList<>();

        boolean cocheExiste = false;

        for (CocheDTO coche : listadoCoches) {
            String marca = coche.getMarca();
            int anho = coche.getAnho();

            if (ordenPorMarca == null) {

            } else {
                for (CocheDTO cocheComprobacion : ordenPorMarca) {
                    if (marca.equalsIgnoreCase(cocheComprobacion.getMarca())) {
                        cocheExiste = true;


                    }

                }
            }
            if (!cocheExiste) {
                for (CocheDTO cocheMarca : listadoCoches) {
                    if (marca.equalsIgnoreCase(cocheMarca.getMarca())) {
                        ordenPorMarca.add(cocheMarca);


                    }
                }
            }


        }
        if (ordenPorMarca == null) {
        } else {
            for (CocheDTO coche : ordenPorMarca){
                vista.mostrarCoche(coche);
            }
        }


    }

    @Override
    public List<CocheDTO> busquedaCompleja(String marcaBusqueda, int anhoBusqueda, float rangoPrecio) {
        List<CocheDTO> marcaCoincidente = busquedaMarca(marcaBusqueda);
        List<CocheDTO> anhoCoincidente = busquedaAnho(anhoBusqueda);
        List<CocheDTO> rangoCoincidente = busquedaRangoPrecio(rangoPrecio);
        List<CocheDTO> listadoOcurrencias = new ArrayList<>();

        List<CocheDTO> marcaAnho = new ArrayList<>();


        for (CocheDTO coche : marcaCoincidente) {
            String marca = coche.getMarca();
            int anho = coche.getAnho();
            for (CocheDTO cocheDTO : anhoCoincidente) {
                if (marca.equalsIgnoreCase(cocheDTO.getMarca())) {
                    if (anho == cocheDTO.getAnho()) {
                        marcaAnho.add(cocheDTO);


                    }
                }

            }


        }
        if (marcaAnho == null) {
            return null;
        } else {
            for (CocheDTO coche : rangoCoincidente) {
                float precio = coche.getPrecio();
                for (CocheDTO cocheDTO : marcaAnho) {
                    if (precio <= cocheDTO.getPrecio()) {
                        listadoOcurrencias.add(cocheDTO);


                    }
                }

            }
            if (listadoOcurrencias == null) {
                return null;
            } else {
                return listadoOcurrencias;
            }

        }


    }

    @Override
    public void mostrarCoches(List<CocheDTO> lista) {
        if (lista == null) {
            vista.imprimirMensaje("No existen coches registrados", TColores.RED);
        } else {
            for (CocheDTO coche : lista) {
                vista.mostrarCoche(coche);

            }
        }
    }

    @Override
    public void mostrarVentas(List<VentaDTO> lista) {
        if (lista == null) {
            vista.imprimirMensaje("No existen ventas registradas", TColores.RED);
        } else {
            for (VentaDTO venta : lista) {
                vista.mostrarVenta(venta);
            }

        }
    }

    @Override
    public void mostrarClientes(List<ClienteDTO> lista) {
        if (lista == null) {
            vista.imprimirMensaje("No existen ventas registradas", TColores.RED);
        } else {
            for (ClienteDTO cliente : lista) {
                vista.mostrarCliente(cliente);
            }
        }
    }

    @Override
    public void registrarVenta(VentaDTO venta) {
        listadoVentas.add(venta);

    }

    @Override
    public void anhadirUnCoche(CocheDTO nuevoCoche) {
        listadoCoches.add(nuevoCoche);


    }

    @Override
    public void registrarUnCliente(ClienteDTO nuevoCliente) {
        listadoClientes.add(nuevoCliente);

    }

    @Override
    public boolean comprobarDni(ClienteDTO clienteAComprobar) {
        for (ClienteDTO cliente : listadoClientes) {
            if (cliente.getDni().equals(clienteAComprobar.getDni())) {
                return true;
            }

        }
        return false;
    }


    //Constructor

    public ConcesionarioControlador(VistaSimple vista) {
        this.vista = vista;
        this.listadoCoches = cargaCoches();
        this.listadoClientes = cargarClientes();
        this.listadoVentas = cargarVentas();


    }


    //Funciones privadas

    /**
     * Comprueba que un cliente exista en la base de datos.
     *
     * @param clienteBusqueda {@link ClienteDTO} el cliente que se va a buscar.
     * @return 0 si el cliente existia en el sistema, -1 si no existia y no se ha creado y 1 si no existia y se ha creado
     */
    private int comprobarClienteExiste(ClienteDTO clienteBusqueda) {
        if (clienteBusqueda == null) {
            vista.imprimirMensaje("El cliente no existe en la base de datos", TColores.RED);
            TOpcionesRegistro opcionesRegistro = vista.menuOpcionesRegistro("¿Dese registrar un nuevo cliente?");

            if (opcionesRegistro == TOpcionesRegistro.SI) {
                registrarUnCliente(vista.registrarCliente());
                vista.imprimirMensaje("Cliente registrado.", TColores.GREEN);
                vista.pulsaParaContinuar();
                return 1;
            }
            if (opcionesRegistro == TOpcionesRegistro.NO) {
                return -1;
            }

        }
        return 0;
    }

    /**
     * Comprueba que un coche exista en la base de datos.
     *
     * @param matriculaBusqueda {@link CocheDTO} el coche que se va a buscar.
     * @return true si el coche existia en el sistema, false si no existia.
     */
    private boolean comprobarMatriculaExiste(CocheDTO matriculaBusqueda) {
        if (matriculaBusqueda == null) {
            vista.imprimirMensaje("El coche no existe en la base de datos.", TColores.RED);
            vista.pulsaParaContinuar();
            return false;
        } else {
            return true;
        }


    }

    /**
     * Comprueba que un coche esté disponible en la base de datos.
     *
     * @param matriculaBusqueda {@link CocheDTO} el coche que se va a buscar.
     * @return true si el coche está disponible en el sistema, false si no lo está.
     */
    private boolean comprobarVendido(CocheDTO matriculaBusqueda) {
        if (matriculaBusqueda.isVendido()) {
            vista.imprimirMensaje("El coche ya está vendido.", TColores.RED);
            vista.pulsaParaContinuar();
            return false;
        } else {
            return true;
        }


    }

    /**
     * Calcula el precio final de venta
     *
     * @param precioInicial {@link Float} para calcular.
     * @return el precio final de la venta.
     */
    private float calcularTotal(float precioInicial) {

        return (precioInicial * IVA) + COMISION_VENTA;

    }

    /**
     * Carga una lista de coches prehecha.
     *
     * @return la lista de coches cargada en memoria.
     */
    private List<CocheDTO> cargaCoches() {
        List<CocheDTO> listadoCoches = new ArrayList<>();
        listadoCoches.add(new CocheDTO("SEAT", "IBIZA", "1078 BDG", 1365.0f, 2000, 186000.0f));
        listadoCoches.add(new CocheDTO("Toyota", "Corolla", "1234 ABC", 18500.00f, 2018, 45000.0f));
        listadoCoches.add(new CocheDTO("BMW", "Serie 3", "5678 XYZ", 32500.00f, 2021, 0000.0f));
        listadoCoches.add(new CocheDTO("Volkswagen", "Tiguan", "9012 DEF", 28900.0f, 2019, 2000.0f));
        listadoCoches.add(new CocheDTO("SEAT", "IBIZA", "3456 GHI", 12500.00f, 2016, 68000.0f));
        listadoCoches.add(new CocheDTO("Suzuki", "Swift", "0501 SW", 1500.0f, 1998, 186000.0f));

        listadoCoches.get(0).setVendido(true);
        listadoCoches.get(3).setVendido(true);

        return listadoCoches;

    }

    /**
     * Carga una lista de clientes prehecha.
     *
     * @return la lista de clientes cargada en memoria.
     */
    private List<ClienteDTO> cargarClientes() {
        List<ClienteDTO> listadoClientes = new ArrayList<>();
        listadoClientes.add(new ClienteDTO("1111111A", "Pedro González Vázquez", 894586210));
        listadoClientes.add(new ClienteDTO("22222222B", "Iván Fernández López", 567154960));
        return listadoClientes;
    }

    /**
     * Carga una lista de ventas prehecha.
     *
     * @return la lista de ventas cargada en memoria.
     */
    private List<VentaDTO> cargarVentas() {
        List<VentaDTO> listadoVentas = new ArrayList<>();
        listadoVentas.add(new VentaDTO(1, listadoClientes.get(1), listadoCoches.get(0), new Date(125, 05, 24), 120));
        listadoVentas.add(new VentaDTO(2, listadoClientes.get(0), listadoCoches.get(2), new Date(125, 05, 24), 16000));
        return listadoVentas;
    }

    //private int actualizarContador() {
    //    if (listadoVentas == null) {
    //    } else {
    //        return contadorVentas = listadoVentas.size() +1;
    //    }
    //    return contadorVentas;
    //}

    //Preguntar a Pedro:
    //if(a.replaceAll("\\s+","").equalsIgnoreCase(b.replaceAll("\\s+",""))) {
    //    // this will also take care of spaces like tabs etc.
    //}
}
