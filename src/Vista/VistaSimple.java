package Vista;

import Modelo.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Scanner;
import java.util.SortedMap;

public class VistaSimple {
    private Scanner sc = new Scanner(System.in);


    public TOpciones mostrarMenu() {
        imprimirSeparador("=");
        TOpciones[] opciones = TOpciones.values();
        for (int i = 0; i < opciones.length; i++) {
            System.out.println((i + 1) + ". " + opciones[i].getOpText());

        }
        imprimirSeparador("=");

        int opUsuario;

        while (true) {
            opUsuario = sc.nextInt();
            sc.nextLine();
            if (opUsuario >= 1 && opUsuario <= opciones.length) {
                return opciones[opUsuario - 1];
            }
            imprimirMensaje("Opción no válida. Por favor, introduzca una opción válida", TColores.RED);
        }


    }


    public TOpcionesMenu menuOpcionesBusqueda() {
        imprimirSeparador("=");
        imprimirMensaje("Escoge el método de búsqueda", TColores.GREEN);
        TOpcionesMenu[] opcionesMenu = TOpcionesMenu.values();
        for (int i = 0; i < opcionesMenu.length; i++) {
            System.out.println((i + 1) + ". " + opcionesMenu[i].getOpText());
        }
        imprimirSeparador("=");
        int opUsuario;
        while (true) {
            opUsuario = sc.nextInt();
            sc.nextLine();
            if (opUsuario >= 1 && opUsuario <= opcionesMenu.length) {
                return opcionesMenu[opUsuario - 1];
            }
            imprimirMensaje("Opción no válida. Por favor, introduzca una opción válida", TColores.RED);
        }
    }

    public TOpcionesRegistro menuOpcionesRegistro() {
        imprimirSeparador("=");
        imprimirMensaje("¿Deseas crear un nuevo cliente?", TColores.GREEN);
        TOpcionesRegistro[] opcionesMenu = TOpcionesRegistro.values();
        for (int i = 0; i < opcionesMenu.length; i++) {
            System.out.println((i + 1) + ". " + opcionesMenu[i].getOpText());
        }
        imprimirSeparador("=");
        int opUsuario;
        while (true) {
            opUsuario = sc.nextInt();
            sc.nextLine();
            if (opUsuario >= 1 && opUsuario <= opcionesMenu.length) {
                return opcionesMenu[opUsuario - 1];
            }
            imprimirMensaje("Opción no válida. Por favor, introduzca una opción válida", TColores.RED);
        }
    }

    public VentaDTO registrarVenta(int idVenta, ClienteDTO cliente, CocheDTO coche, float precioVenta) {

        Date fecha = Date.from(java.time.ZonedDateTime.now().toInstant());


        return new VentaDTO(idVenta, cliente, coche, fecha, precioVenta);
    }

    public CocheDTO anhadirCoche() {


        imprimirMensaje("Por favor, introduce los datos del coche a registrar", TColores.GREEN);
        String marca = pedirCadena("Introduce marca:");
        String modelo = pedirCadena("Introduce modelo:");
        String matricula = pedirCadena("Introduce matrícula:");
        float precio = pedirFloat("Introduce el precio:");
        int anhoUsuario = (pedirInt("Introduce el año:"));
        int mes = pedirInt("Introduce el numero de mes:");
        int dia = pedirInt("Introduce el día:");


        float kilometros = pedirFloat("Introduce los kilómetros:");
        return new CocheDTO(marca, modelo, matricula, precio, anhoUsuario, kilometros);
    }


    public ClienteDTO registrarCliente() {
        imprimirMensaje("Por favor, introduce los datos del cliente a registrar", TColores.GREEN);
        String dni = pedirCadena("Introduce el dni del cliente:");
        if (dni.length() == 9) {
            imprimirMensaje("El dni debe tener una longitud de 8 números y un caracter.", TColores.RED);
            dni = pedirCadena("Introduce el dni del cliente:");
        }
        String nombre = pedirCadena("Introduce el nombre del cliente:");
        int telefono = pedirInt("Introduce el teléfono del cliente:");
        if (telefono == 9) {
            imprimirMensaje("El teléfono sólo puede contener 9 números. No se admiten prefijos.", TColores.RED);
            dni = pedirCadena("Introduce el teléfono del cliente:");
        }


        return new ClienteDTO(dni, nombre, telefono);
    }

    public String pedirCadena(String mensaje) {


        while (true) {
            try {
                System.out.println(mensaje);
                return sc.nextLine();
            } catch (Exception e) {
                System.err.println("Sólo se admiten cadenas de texto");

            }
        }


    }

    public float pedirFloat(String mensaje) {
        while (true) {
            try {
                System.out.println(mensaje);
                float opcion = sc.nextFloat();
                sc.nextLine();
                return opcion;
            } catch (Exception e) {
                System.out.println("Sólo se admiten números en coma flotante");

            }
        }


    }

    public int pedirInt(String mensaje) {
        while (true) {
            try {
                System.out.println(mensaje);
                int opcion = sc.nextInt();
                sc.nextLine();
                return opcion;
            } catch (Exception e) {
                System.out.println("Sólo se admiten números enteros");

            }
        }
    }

    public void mostrarCoche(CocheDTO coche) {
        System.out.println(coche.getMarca() + " " + coche.getModelo() + " " + coche.getMatricula() + " "
                + coche.getAnho() + " " + (coche.getKilometros() + "Km.") + " " + (coche.getPrecio()) + "€");


    }

    public void mostrarVenta(VentaDTO venta) {

        System.out.println("ID " + venta.getIdVenta() + " " + venta.getCliente().getNombre() + " " + venta.getCliente().getDni()
                + " " + venta.getCoche().getMarca() + " " + venta.getCoche().getModelo() + " " + venta.getCoche().getPrecio() + "€"
                + " " + venta.getCoche().getAnho() + " " + venta.getCoche().getPrecio() + " " + "precio final de venta " +
                venta.getPrecioVenta() + "€" + " " + venta.getFecha());


    }

    public void mostrarCliente(ClienteDTO cliente) {

        System.out.println(cliente.getNombre() + " " + cliente.getDni() + " " + cliente.getTelefono());

    }

    private void imprimirSeparador(String mensaje) {
        System.out.println("\n" + "======================" + mensaje + "==========================="
                + "\n");
    }

    public void imprimirMensaje(String mensaje, TColores color) {
        System.out.println(color.getColor() + mensaje + TColores.RESET.getColor());

    }

    public void mensajeSalida() {
        System.out.println("Gracias por usar nuestro sistema." +
                "\n¡Hasta la próxima!");
        imprimirSeparador(" ;) ");
    }

    public void pulsaParaContinuar() {
        System.out.println("Pulsa una tecla para continuar.");
        sc.nextLine();
    }


}

