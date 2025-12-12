package Controlador;

import Modelo.*;
import Vista.IVistaInterfaz;

import java.util.*;

public class ConcesionarioControlador implements IControlador {

    //Atributos
    private static final int CNT_ORIGIN = 0;
    public static final float IVA = 1.21f;
    public static final float COMISION_VENTA = 0.0f;
    private IVistaInterfaz vista;
    private int cnt = CNT_ORIGIN;
    List<CocheDTO> listadoCoches;
    List<ClienteDTO> listadoClientes;
    List<VentaDTO> listadoVentas;
    List<VendedorDTO> listadoVendedores;
    int contadorVentas = 3;
    int numeroVendedor = 4;


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
                        mostrarCoches(busquedaMarca(vista.pedirCadena("Introduce la marca de coche.", false)));
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
                mostrarCoches(busquedaCompleja((vista.pedirCadena("Introduce la marca de coche.", false)),
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

                registrarVenta();
            }

            if (opciones == TOpciones.LISTAR_CLIENTES) {
                mostrarClientes(listadoClientes);
                vista.pulsaParaContinuar();
            }

            if (opciones == TOpciones.CREAR_VENDEDOR) {
                VendedorDTO nuevoVendedor = vista.registrarVendedor(numeroVendedor);

                if (comprobarDni(nuevoVendedor)) {
                    vista.imprimirMensaje("El vendedor ya existe en la base de datos.", TColores.RED);

                } else {
                    registrarUnVendedor(nuevoVendedor);
                    numeroVendedor++;
                    vista.pulsaParaContinuar();
                }

            }

            if (opciones == TOpciones.LISTAR_VENDEDORES) {
                mostrarVendedores(listadoVendedores);
                vista.pulsaParaContinuar();
            }
            if (opciones == TOpciones.ESTADISTICAS_VENDEDOR) {
                VendedorDTO vendedorMenu = busquedaVendedor(vista.pedirInt("Introduce el número de vendedor"));

                while (true) {
                    TOpcionesVendedor opcionesMenu = vista.menuOpcionesVendedor("Escoge opción:");
                    if (opcionesMenu == TOpcionesVendedor.COCHE_CARO) {
                        mostrarCoches(busquedaMarca(vista.pedirCadena("Introduce la marca de coche.", false)));
                        vista.menuOpcionesBusqueda();

                    }

                    if (opcionesMenu == TOpcionesVendedor.SALIR) {
                        break;

                    }
                }


            }


            if (opciones == TOpciones.SALIR) {
                vista.mostrarMensajeSalida();
                break;
            }

        }

    }


    @Override
    public void registrarVenta() {
        //Preguntar cómo extraer esto
        vista.imprimirMensaje("Por favor, introduce los datos de la venta", TColores.GREEN);


        String dni = vista.pedirCadena("Introduce el DNI del cliente.", false);
        ClienteDTO clienteBusqueda = busquedaCliente(dni);
        int comprobar = comprobarClienteExiste(clienteBusqueda);
        if (comprobar == -1) {
            return;
        }
        if (comprobar == 1) {
            clienteBusqueda = busquedaCliente(dni);
        }


        String matricula = vista.pedirCadena("Introduce la matrícula del coche", false);
        CocheDTO cocheBusqueda = busquedaMatricula(matricula);


        if (!comprobarMatriculaExiste(cocheBusqueda)) {
            return;
        }
        if (!comprobarVendido(cocheBusqueda)) {
            return;
        }


        float precio = calcularTotal(vista.pedirFloat("Introduce el precio de venta"));

        int id = vista.pedirInt("Introduce el ID del vendedor.");
        VendedorDTO vendedorBusqueda = busquedaVendedor(id);

        if (vendedorBusqueda == null) {
            return;
        }


        VentaDTO nuevaVenta = vista.registrarVenta(contadorVentas, clienteBusqueda, cocheBusqueda, precio, vendedorBusqueda);


        //VentaDTO nuevaVenta = vista.registrarVenta((contadorVentas), busquedaCliente(vista.pedirCadena("Introduce el DNI del cliente.")),
        //        busquedaMatricula(vista.pedirCadena("Introduce la matrícula del coche")),
        //        calcularTotal(vista.pedirFloat("Introduce el precio de venta")));

        contadorVentas++;
        registrarVenta(nuevaVenta);
    }

    @Override
    public VendedorDTO busquedaVendedor(int id) {
        for (VendedorDTO vendedor : listadoVendedores) {
            if (vendedor.getNumeroVendedor() == id) {
                return vendedor;
            }


        }
        return null;
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
            if (cliente.getDni().equalsIgnoreCase(dni)) {
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
            if (coche.getMarca().equalsIgnoreCase(marcaBusqueda)) {
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
    public void ordenarCoches() {
        List<CocheDTO> ordenPorMarca = new ArrayList<>();
        List<CocheDTO> listaOrdenada = new ArrayList<>();

        boolean cocheExiste = false;

        for (CocheDTO coche : listadoCoches) {
            String matricula = coche.getMatricula();
            String marca = coche.getMarca();
            int anho = coche.getAnho();

            if (ordenPorMarca == null) {

            } else {
                for (CocheDTO cocheComprobacion : ordenPorMarca) {
                    if (matricula.equalsIgnoreCase(cocheComprobacion.getMatricula())) {
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
            for (CocheDTO coche : ordenPorMarca) {
                vista.mostrarCoche(coche);
            }
        }


    }

    public void ordenarCochesAlfabetico() {
        List coches = listadoCoches.subList(0, listadoCoches.size() - 1);

        coches.sort(new Comparator<CocheDTO>() {
            @Override
            public int compare(CocheDTO o1, CocheDTO o2) {
                return o1.getMarca().compareTo(o2.getMarca());
            }
        });


    }

    @Override
    public List<CocheDTO> busquedaCompleja(String marcaBusqueda, int anhoBusqueda, float rangoPrecio) {

        List<CocheDTO> listadoOcurrencias = new ArrayList<>();


        for (CocheDTO coche : listadoCoches) {
            if (coche.getMarca().equalsIgnoreCase(marcaBusqueda) && (coche.getAnho() == anhoBusqueda) && coche.getPrecio() <= rangoPrecio) {
                listadoOcurrencias.add(coche);

            }

        }
        return listadoOcurrencias;


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
    public void mostrarVendedores(List<VendedorDTO> lista) {

        if (lista == null) {
            vista.imprimirMensaje("No existen ventas registradas", TColores.RED);
        } else {
            for (VendedorDTO vendedor : lista) {
                vista.mostrarVendedor(vendedor);
            }
        }
    }

    @Override
    public void registrarVenta(VentaDTO venta) {
        listadoVentas.add(venta);

    }

    @Override
    public void registrarUnVendedor(VendedorDTO nuevoVendedor) {
        listadoVendedores.add(nuevoVendedor);
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

    @Override
    public boolean comprobarDni(VendedorDTO vendedorAComprobar) {
        for (VendedorDTO vendedor : listadoVendedores) {
            if (vendedor.getDni().equals(vendedorAComprobar.getDni())) {
                return true;
            }

        }
        return false;
    }


    //Constructor

    public ConcesionarioControlador(IVistaInterfaz vista) {
        this.vista = vista;
        this.listadoVendedores = cargarVendedores();
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
        listadoCoches.add(new CocheDTO("SEAT", "LEON", "2234 FGH", 18200.00f, 2019, 42000.0f));
        listadoCoches.add(new CocheDTO("Toyota", "RAV4", "4456 IJK", 34500.00f, 2022, 15000.0f));
        listadoCoches.add(new CocheDTO("BMW", "X5", "6678 LMN", 68500.00f, 2023, 8000.0f));
        listadoCoches.add(new CocheDTO("Volkswagen", "Golf", "8890 OPQ", 21500.00f, 2020, 38000.0f));
        listadoCoches.add(new CocheDTO("Audi", "Q5", "1123 RST", 52000.00f, 2021, 22000.0f));
        listadoCoches.add(new CocheDTO("Ford", "Kuga", "3345 UVW", 28900.00f, 2022, 19000.0f));
        listadoCoches.add(new CocheDTO("Mercedes", "Clase C", "5567 XYZ", 45200.00f, 2021, 27000.0f));
        listadoCoches.add(new CocheDTO("Renault", "Kadjar", "7789 ABC", 19700.00f, 2018, 61000.0f));
        listadoCoches.add(new CocheDTO("Hyundai", "i30", "9901 DEF", 16800.00f, 2020, 46000.0f));
        listadoCoches.add(new CocheDTO("SEAT", "ATECA", "1124 GHI", 27600.00f, 2021, 32000.0f));
        listadoCoches.add(new CocheDTO("Audi", "A4", "7890 JKL", 34200.50f, 2020, 35000.0f));
        listadoCoches.add(new CocheDTO("Ford", "Focus", "1122 MMN", 16750.00f, 2017, 89000.0f));
        listadoCoches.add(new CocheDTO("Mercedes", "Clase A", "3344 OPP", 41500.00f, 2022, 12000.0f));
        listadoCoches.add(new CocheDTO("Renault", "Clio", "5566 QRS", 14500.00f, 2019, 55000.0f));
        listadoCoches.add(new CocheDTO("Hyundai", "Tucson", "7788 TUV", 23900.00f, 2021, 18000.0f));
        listadoCoches.add(new CocheDTO("Opel", "Corsa", "9900 WXY", 13200.00f, 2018, 72000.0f));
        listadoCoches.add(new CocheDTO("Peugeot", "308", "2233 ZZZ", 18750.00f, 2020, 41000.0f));
        listadoCoches.add(new CocheDTO("Volvo", "XC60", "4455 AAA", 48900.00f, 2023, 5000.0f));
        listadoCoches.add(new CocheDTO("Kia", "Sportage", "6677 BBB", 27600.00f, 2021, 29000.0f));
        listadoCoches.add(new CocheDTO("Fiat", "500", "8899 CCC", 12500.00f, 2019, 63000.0f));

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
        listadoVentas.add(new VentaDTO(1, listadoClientes.get(1), listadoCoches.get(0), new Date(125, 05, 24), 120, listadoVendedores.get(1)));
        listadoVentas.add(new VentaDTO(2, listadoClientes.get(0), listadoCoches.get(2), new Date(125, 05, 24), 16000, listadoVendedores.get(2)));
        return listadoVentas;
    }

    /**
     * Carga una lista de vendedores prehecha.
     *
     * @return la lista de ventas cargada en memoria.
     */
    private List<VendedorDTO> cargarVendedores() {
        List<VendedorDTO> listadoVendedores = new ArrayList<>();
        listadoVendedores.add(new VendedorDTO("José", 0001, "555555668A"));
        listadoVendedores.add(new VendedorDTO("Marta", 0002, "559455668Z"));
        listadoVendedores.add(new VendedorDTO("Julia", 0003, "659555668F"));

        return listadoVendedores;
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
