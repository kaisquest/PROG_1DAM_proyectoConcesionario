package Controlador;

import Modelo.*;
import Vista.VistaSimple;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ConcesionarioControlador {

    private static final int CNT_ORIGIN = 0;
    public static final float IVA = 1.21f;
    public static final float COMISION_VENTA = 0.0f;
    private VistaSimple vista;
    private int cnt = CNT_ORIGIN;
    List<CocheDTO> listadoCoches;
    List<ClienteDTO> listadoClientes;
    List<VentaDTO> listadoVentas;
    int contadorVentas = 0;


    public void run() {
        vista.imprimirMensaje("\n" + "Bienvenido al programa de gestión concesionario.", TColores.GREEN);
        while (true) {
            TOpciones opciones = vista.mostrarMenu();
            if (opciones == TOpciones.ANHADIR_COCHE) {
                try {
                    CocheDTO nuevoCoche = vista.anhadirCoche();
                    anhadirUnCoche(nuevoCoche);
                    vista.pulsaParaContinuar();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }

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
                        mostrarCoches(busquedaAnho(vista.pedirAnhoFecha("Introduce el año del coche.")));
                    }
                    if (opcionesMenu == TOpcionesMenu.SALIR) {
                        break;

                    }
                }
            }
            if (opciones == TOpciones.LISTAR_VENTAS) {
                mostrarVentas(listadoVentas);
                vista.pulsaParaContinuar();

            }
            if (opciones == TOpciones.MOSTRAR_COCHES) {
                mostrarCoches(listadoCoches);
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
                if (listadoVentas == null) {
                    this.listadoVentas = cargarVentas();
                }
                vista.imprimirMensaje("Por favor, introduce los datos de la venta", TColores.GREEN);
                contadorVentas++;
                VentaDTO nuevaVenta = vista.registrarVenta((contadorVentas), busquedaCliente(vista.pedirCadena("Introduce el DNI del cliente.")),
                        busquedaMatricula(vista.pedirCadena("Introduce la matrícula del coche")),
                        calcularTotal(vista.pedirFloat("Introduce el precio de venta")));
                registrarVenta(nuevaVenta);


            }

            if (opciones == TOpciones.LISTAR_CLIENTES) {
                mostrarClientes(listadoClientes);
                vista.pulsaParaContinuar();
            }
            if (opciones == TOpciones.SALIR) {
                vista.mensajeSalida();
                break;
            }

        }

    }

    private float calcularTotal(float precioInicial) {

        return (precioInicial * IVA) + COMISION_VENTA;

    }

    public CocheDTO busquedaMatricula(String matricula) {

        for (CocheDTO coche : listadoCoches) {
            if (coche.getMatricula().equals(matricula)) {
                return coche;
            }

        }

        return null;


    }

    public ClienteDTO busquedaCliente(String dni) {

        for (ClienteDTO cliente : listadoClientes) {
            if (cliente.getDni().equals(dni)) {
                return cliente;
            }

        }

        return null;


    }

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

    public List<CocheDTO> busquedaAnho(int anhoBusqueda) {
        List<CocheDTO> listadoOcurrencias = new ArrayList<>();
        cnt = CNT_ORIGIN;
        for (CocheDTO coche : listadoCoches) {
            if (coche.getAnho().getYear() == anhoBusqueda) {
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

    public void mostrarCoches(List<CocheDTO> lista) {
        if (lista == null) {
            vista.imprimirMensaje("No existen coches registrados", TColores.RED);
        } else {
            for (CocheDTO coche : lista) {
                vista.mostrarCoche(coche);

            }
        }
    }

    public void mostrarVentas(List<VentaDTO> lista) {
        if (lista == null) {
            vista.imprimirMensaje("No existen ventas registradas", TColores.RED);
        } else {
            for (VentaDTO venta : lista) {
                vista.mostrarVenta(venta);
            }

        }
    }

    public void mostrarClientes(List<ClienteDTO> lista) {
        if (lista == null) {
            vista.imprimirMensaje("No existen ventas registradas", TColores.RED);
        } else {
            for (ClienteDTO cliente : lista) {
                vista.mostrarCliente(cliente);
            }
        }
    }

    public void registrarVenta(VentaDTO venta) {
        listadoVentas.add(venta);

    }

    public void anhadirUnCoche(CocheDTO nuevoCoche) {
        listadoCoches.add(nuevoCoche);


    }

    public void registrarUnCliente(ClienteDTO nuevoCliente) {
        listadoClientes.add(nuevoCliente);

    }

    public boolean comprobarDni(ClienteDTO clienteAComprobar) {
        for (ClienteDTO cliente : listadoClientes) {
            if (cliente.getDni().equals(clienteAComprobar.getDni())) {
                return true;
            }

        }
        return false;
    }

    public ConcesionarioControlador(VistaSimple vista) {
        this.vista = vista;
        this.listadoCoches = cargaCoches();
        this.listadoClientes = cargarClientes();


    }

    private List<CocheDTO> cargaCoches() {
        List<CocheDTO> listadoCoches = new ArrayList<>();
        listadoCoches.add(new CocheDTO("SEAT", "IBIZA", "1078 BDG", 1365.0f, new Date(100, 01, 01), 186000.0f));
        listadoCoches.add(new CocheDTO("Toyota", "Corolla", "1234 ABC", 18500.00f, new Date(118, 4, 15), 45000.0f));
        listadoCoches.add(new CocheDTO("BMW", "Serie 3", "5678 XYZ", 32500.00f, new Date(121, 8, 10), 0000.0f));
        listadoCoches.add(new CocheDTO("Volkswagen", "Tiguan", "9012 DEF", 28900.0f, new Date(119, 11, 20), 2000.0f));
        listadoCoches.add(new CocheDTO("SEAT", "IBIZA", "3456 GHI", 12500.00f, new Date(116, 1, 5), 68000.0f));
        listadoCoches.add(new CocheDTO("Suzuki", "Swift", "0501 SW", 1500.0f, new Date(98, 01, 01), 186000.0f));

        return listadoCoches;

    }

    private List<ClienteDTO> cargarClientes() {
        List<ClienteDTO> listadoClientes = new ArrayList<>();
        listadoClientes.add(new ClienteDTO("1111111A", "Pedro González Vázquez", 894586210));
        listadoClientes.add(new ClienteDTO("22222222B", "Iván Fernández López", 567154960));
        return listadoClientes;
    }

    private List<VentaDTO> cargarVentas() {
        return new ArrayList<>();
    }

}
