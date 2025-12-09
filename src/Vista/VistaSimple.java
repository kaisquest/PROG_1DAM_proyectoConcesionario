package Vista;

import Modelo.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Scanner;
import java.util.SortedMap;

public class VistaSimple {
    public static final int ANHO_RESTA = 1900;
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

    public VentaDTO registrarVenta(int idVenta, ClienteDTO cliente, CocheDTO coche, float precioVenta) {

        Date fecha = Date.from(java.time.ZonedDateTime.now().toInstant());


        return new VentaDTO(idVenta, cliente, coche, fecha, precioVenta);
    }

    public CocheDTO anhadirCoche() {

        try {
            imprimirMensaje("Por favor, introduce los datos del coche a registrar", TColores.GREEN);
            String marca = pedirCadena("Introduce marca:");
            String modelo = pedirCadena("Introduce modelo:");
            String matricula = pedirCadena("Introduce matrícula:");
            float precio = pedirFloat("Introduce el precio:");
            int anhoUsuario = (pedirInt("Introduce el año:") - ANHO_RESTA);
            int mes = pedirInt("Introduce el numero de mes:");
            int dia = pedirInt("Introduce el día:");
            Date anho = new Date(anhoUsuario, mes, dia);

            float kilometros = pedirFloat("Introduce los kilómetros:");
            return new CocheDTO(marca, modelo, matricula, precio, anho, kilometros);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


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

        try {
            System.out.println(mensaje);
            return sc.nextLine();
        } catch (Exception e) {
            System.err.println("Sólo se admiten cadenas de texto");
            throw new RuntimeException(e);
        }


    }

    public float pedirFloat(String mensaje) {

        try {
            System.out.println(mensaje);
            float opcion = sc.nextFloat();
            sc.nextLine();
            return (opcion + 0.0f);
        } catch (Exception e) {
            System.out.println("Sólo se admiten números en coma flotante");
            throw new RuntimeException(e);
        }


    }

    public int pedirAnhoFecha(String mensaje) {
        try {
            System.out.println(mensaje);
            int opcion = sc.nextInt();
            sc.nextLine();
            return (opcion - ANHO_RESTA);
        } catch (Exception e) {
            System.out.println("Sólo se admiten números enteros");
            throw new RuntimeException(e);
        }
    }

    public int pedirInt(String mensaje) {
        try {
            System.out.println(mensaje);
            int opcion = sc.nextInt();
            sc.nextLine();
            return opcion;
        } catch (Exception e) {
            System.out.println("Sólo se admiten números enteros");
            throw new RuntimeException(e);
        }
    }

    public void mostrarCoche(CocheDTO coche) {
        System.out.println(coche.getMarca() + " " + coche.getModelo() + " " + coche.getMatricula() + " "
                + coche.getAnho() + " " + (coche.getKilometros() + "Km.") + " " + (coche.getPrecio()) + "€");


    }

    public void mostrarVenta(VentaDTO venta) {

        System.out.println("ID " + venta.getIdVenta() + " " + venta.getCliente().getNombre() + " " + venta.getCliente().getDni()
                + " " + venta.getCoche().getMarca() + " " + venta.getCoche().getModelo() + " " + venta.getCoche().getPrecio() +"€"
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

