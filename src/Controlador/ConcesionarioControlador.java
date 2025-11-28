package Controlador;

import Modelo.ClienteDTO;
import Modelo.CocheDTO;
import Modelo.VentaDTO;
import Vista.VistaSimple;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ConcesionarioControlador {

    private VistaSimple vista;
    List<CocheDTO> listadoCoches;
    List<ClienteDTO> listadoClientes;
    List<VentaDTO> listadoVentas;

    enum opcionesEnum {
        ANHADIR_COCHE, MOSTRAR_COCHES, BUSCAR, REGISTRAR_CLIENTE, REGISTRAR_VENTA, LISTAR_VENTAS
    }

    public void run() {
        int op;
        while (true) {






        }


    }

    public void mostrarCoches() {
        for (CocheDTO coche : listadoCoches) {
            vista.mostrarCoche(coche);

        }
    }


    public ConcesionarioControlador(VistaSimple vista) {
        this.vista = vista;
        this.listadoCoches = cargarCoches();
    }

    private List<CocheDTO> cargarCoches() {
        List<CocheDTO> listadoCoches = new ArrayList<>();
        listadoCoches.add(new CocheDTO("SEAT", "IBIZA", "1088 BDG", 1365.0f, new Date(100, 01, 01), 186000.0f));
        listadoCoches.add(new CocheDTO("Toyota", "Corolla", "1234 ABC", 18500.00f, new Date(118, 4, 15), 45000.0f));
        listadoCoches.add(new CocheDTO("BMW", "Serie 3", "5678 XYZ", 32500.00f, new Date(121, 8, 10), 15000.0f));
        listadoCoches.add(new CocheDTO("Volkswagen", "Tiguan", "9012 DEF", 28900.0f, new Date(119, 11, 20), 32000.0f));
        listadoCoches.add(new CocheDTO("SEAT", "IBIZA", "GHI3456", 12500.00f, new Date(116, 1, 5), 68000.0f));

        return listadoCoches;

    }

}
