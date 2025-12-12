package Vista;

import Modelo.*;

import java.util.Date;
import java.util.Scanner;

public class VistaCompleja implements IVistaInterfaz {
    private Scanner sc = new Scanner(System.in);
    private static final int TAMANHO_TABLA = 50;
    private static final int CUADRICULA = TAMANHO_TABLA / 2;
    private static final int ANHO = 1900;

    @Override
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

    @Override
    public TOpcionesOrdenar menuOpcionesOrdenar() {
        imprimirSeparador("=");
        imprimirMensaje("Escoge el método de ordenación", TColores.GREEN);
        TOpcionesOrdenar[] opcionesMenu = TOpcionesOrdenar.values();
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


    @Override
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

    @Override
    public TOpcionesRegistro menuOpcionesRegistro(String mensaje) {
        imprimirSeparador("=");
        imprimirMensaje(mensaje, TColores.GREEN);
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

 // @Override
 // public TOpcionesVendedor menuOpcionesVendedor(String mensaje) {
 //     imprimirSeparador("=");
 //     imprimirMensaje(mensaje, TColores.GREEN);
 //     TOpcionesVendedor[] opcionesMenu = TOpcionesVendedor.values();
 //     for (int i = 0; i < opcionesMenu.length; i++) {
 //         System.out.println((i + 1) + ". " + opcionesMenu[i].getOpText());
 //     }
 //     imprimirSeparador("=");
 //     int opUsuario;
 //     while (true) {
 //         opUsuario = sc.nextInt();
 //         sc.nextLine();
 //         if (opUsuario >= 1 && opUsuario <= opcionesMenu.length) {
 //             return opcionesMenu[opUsuario - 1];
 //         }
 //         imprimirMensaje("Opción no válida. Por favor, introduzca una opción válida", TColores.RED);
 //     }
 // }

    @Override
    public VentaDTO registrarVenta(int idVenta, ClienteDTO cliente, CocheDTO coche, float precioVenta, VendedorDTO vendedor) {

        Date fecha = Date.from(java.time.ZonedDateTime.now().toInstant());


        return new VentaDTO(idVenta, cliente, coche, fecha, precioVenta, vendedor);
    }

    @Override
    public CocheDTO anhadirCoche() {


        imprimirMensaje("Por favor, introduce los datos del coche a registrar", TColores.GREEN);
        String marca = pedirCadena("Introduce marca:",false);
        String modelo = pedirCadena("Introduce modelo:",false);
        String matricula = pedirCadena("Introduce matrícula:",false);
        float precio = pedirFloat("Introduce el precio:");
        int anhoUsuario = (pedirInt("Introduce el año:"));
        float kilometros = pedirFloat("Introduce los kilómetros:");
        return new CocheDTO(marca, modelo, matricula, precio, anhoUsuario, kilometros);
    }


    @Override
    public ClienteDTO registrarCliente() {
        imprimirMensaje("Por favor, introduce los datos del cliente a registrar", TColores.GREEN);
        String dni = pedirCadena("Introduce el dni del cliente:",false);
        if (dni.length() != 9) {
            imprimirMensaje("El dni debe tener una longitud de 8 números y un caracter.", TColores.RED);
            dni = pedirCadena("Introduce el dni del cliente:",false);
        }
        String nombre = pedirCadena("Introduce el nombre del cliente:",false);
        int telefono = pedirInt("Introduce el teléfono del cliente:");
        if (telefono == 9) {
            imprimirMensaje("El teléfono sólo puede contener 9 números. No se admiten prefijos.", TColores.RED);
            telefono = pedirInt("Introduce el teléfono del cliente:");
        }


        return new ClienteDTO(dni, nombre, telefono);
    }

    @Override
    public VendedorDTO registrarVendedor(int numeroVendedor) {
        imprimirMensaje("Por favor, introduce los datos del vendedor a registrar", TColores.GREEN);
        String dni = pedirCadena("Introduce el dni del vendedor:",false);
        if (dni.length() != 9) {
            imprimirMensaje("El dni debe tener una longitud de 8 números y un caracter.", TColores.RED);
            dni = pedirCadena("Introduce el dni del vendedor:",false);
        }
        String nombre = pedirCadena("Introduce el nombre del vendedor:",false);

        return new VendedorDTO(nombre,numeroVendedor,dni);
    }

    @Override
    public String pedirCadena(String mensaje, boolean permiteVacio) {

        String cadena = "";
        while (true) {

            try {
                System.out.println(mensaje);

                cadena = sc.nextLine();

                if((cadena.isBlank() && permiteVacio)){
                    break;
                }

                if(!cadena.isBlank()){
                    break;
                }
                imprimirMensaje("La cadena de texto no puede estar vacía.",TColores.RED);
            } catch (Exception e) {
                System.err.println("Sólo se admiten cadenas de texto");
                sc.nextLine();

            }
        }

        return cadena;

    }

    @Override
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

    @Override
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

    @Override
    public void mostrarCoche(CocheDTO coche) {


        System.out.println("=".repeat(TAMANHO_TABLA));

        System.out.println("|" + pad("", TAMANHO_TABLA) + "|");
        System.out.println("|" + pad("Datos del vehículo ", TAMANHO_TABLA) + "|");
        System.out.println("|" + pad("Marca: ", CUADRICULA) + pad(coche.getMarca(), CUADRICULA) + "|");
        System.out.println("|" + pad("Modelo: ", CUADRICULA) + pad(coche.getModelo(), CUADRICULA) + "|");
        System.out.println("|" + pad("Matrícula: ", CUADRICULA) + pad(coche.getMatricula(), CUADRICULA) + "|");
        System.out.println("|" + pad("Año: ", CUADRICULA) + pad(String.valueOf(coche.getAnho()), CUADRICULA) + "|");
        System.out.println("|" + pad("Kilómetros: ", CUADRICULA) + pad(String.valueOf(coche.getKilometros()), CUADRICULA) + "|");
        System.out.println("|" + pad("Precio: ", CUADRICULA) + pad(String.valueOf(coche.getPrecio()) + " €", CUADRICULA) + "|");


        System.out.println("=".repeat(TAMANHO_TABLA));


    }

    @Override
    public void mostrarVenta(VentaDTO venta) {


        System.out.println("=".repeat(TAMANHO_TABLA));

        System.out.println("|" + pad("", TAMANHO_TABLA) + "|");
        System.out.println("|" + pad("ID: ", CUADRICULA) + pad(String.valueOf(venta.getIdVenta()), CUADRICULA) + "|");
        System.out.printf("\n");
        System.out.println("|" + pad("Datos del cliente ", TAMANHO_TABLA) + "|");
        System.out.println("|" + pad("Nombre del Cliente: ", CUADRICULA) + pad(venta.getCliente().getNombre(), CUADRICULA) + "|");
        System.out.println("|" + pad("DNI del Cliente: ", CUADRICULA) + pad(venta.getCliente().getDni(), CUADRICULA) + "|");
        System.out.printf("\n");
        System.out.println("|" + pad("Datos del vehículo ", TAMANHO_TABLA) + "|");
        System.out.println("|" + pad("Marca: ", CUADRICULA) + pad(venta.getCoche().getMarca(), CUADRICULA) + "|");
        System.out.println("|" + pad("Modelo: ", CUADRICULA) + pad(venta.getCoche().getModelo(), CUADRICULA) + "|");
        System.out.println("|" + pad("Año: ", CUADRICULA) + pad(String.valueOf(venta.getCoche().getAnho()), CUADRICULA) + "|");
        System.out.println("|" + pad("Precio: ", CUADRICULA) + pad(String.valueOf(venta.getCoche().getPrecio()) + " €", CUADRICULA) + "|");
        System.out.printf("\n");
        System.out.println("|" + pad("Precio final de venta: ", CUADRICULA) + pad(String.valueOf(venta.getPrecioVenta()) + " €", CUADRICULA) + "|");
        System.out.println("|" + pad("Fecha: ", CUADRICULA) + pad(String.valueOf(venta.getFecha().getDay()) + ", "
                + String.valueOf(venta.getFecha().getMonth()) + ", " + String.valueOf(venta.getFecha().getYear() + ANHO), CUADRICULA) + "|");


        System.out.println("=".repeat(TAMANHO_TABLA));


    }

    @Override
    public void mostrarVendedor(VendedorDTO vendedor) {

        System.out.println("=".repeat(TAMANHO_TABLA));

        System.out.println("|" + pad("", TAMANHO_TABLA) + "|");
        System.out.println("|" + pad("Número de vendedor: ", CUADRICULA) + pad(String.valueOf(vendedor.getNumeroVendedor()), CUADRICULA) + "|");
        System.out.println("|" + pad("Nombre: ", CUADRICULA) + pad(vendedor.getNombre(), CUADRICULA) + "|");
        System.out.println("|" + pad("Nombre: ", CUADRICULA) + pad(vendedor.getDni(), CUADRICULA) + "|");



        System.out.println("=".repeat(TAMANHO_TABLA));
    }

    @Override
    public void mostrarCliente(ClienteDTO cliente) {



        System.out.println("=".repeat(TAMANHO_TABLA));

        System.out.println("|" + pad("", TAMANHO_TABLA) + "|");
        System.out.println("|" + pad("Datos del cliente ", TAMANHO_TABLA) + "|");
        System.out.println("|" + pad("Nombre: ", CUADRICULA) + pad(cliente.getNombre(),  CUADRICULA) + "|");
        System.out.println("|" + pad("DNI: ", CUADRICULA) + pad(cliente.getDni(),  CUADRICULA) + "|");
        System.out.println("|" + pad("Teléfono: ", CUADRICULA) + pad(String.valueOf(cliente.getTelefono()),  CUADRICULA) + "|");

        System.out.println("=".repeat(TAMANHO_TABLA));

    }

    @Override
    public void imprimirSeparador(String mensaje) {
        System.out.println("\n" + "=".repeat(TAMANHO_TABLA / 2)  + mensaje + "=".repeat((TAMANHO_TABLA / 2) - mensaje.length())
                + "\n");
    }



    @Override
    public void imprimirMensaje(String mensaje, TColores color) {
        System.out.println(color.getColor() + mensaje + TColores.RESET.getColor());

    }

    @Override
    public void mostrarMensajeSalida() {
        System.out.println("Gracias por usar nuestro sistema." +
                "\n¡Hasta la próxima!");
        imprimirSeparador(" ;) ");
    }

    @Override
    public void pulsaParaContinuar() {
        System.out.println("Pulsa una tecla para continuar.");
        sc.nextLine();
    }

    @Override
    public void imprimirLogo() {
        System.out.println("                                                                                                     \n" +
                "                                                                                                                        \n" +
                "                                                      ...,*//(((/**,,..                                                 \n" +
                "                                         ,@(..                                 .*&&                                     \n" +
                "                                       (*                                           %.                                  \n" +
                "                                     ..                                               &                                 \n" +
                "                           .&@@@&.  #                                                  ./  #&@@@(                       \n" +
                "                           *%&&@(##.    *&*                                      .(@     #/*@&&&#                       \n" +
                "                           ,%, ,@,         (#(//**,,,..              ...,,***/((#,         @&  *%                       \n" +
                "                         ,       .%                                                      ,#       ,                     \n" +
                "                        ,            .                                                 ,           ,                    \n" +
                "                        #@@@@@&%,.                        ..,,,,...                        ./%@@@@@@.                   \n" +
                "                        /@@@@@@@@@    *@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@%    #@@@@@@@@@                    \n" +
                "                                    #@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@                                \n" +
                "                                   .@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@%                               \n" +
                "                                                                                                                        \n" +
                "                                                                                                                        \n" +
                "               %%%(      #&%   *%&    &%%%%%%%     .%@@&/       (&@@#      &%*   #%%      (&@@#.     .%%&%#,            \n" +
                "              .@@@@      %@&   *@@      .@@*      @@@  *@@*   .@@/ .@@*    @@/   %@@    /@@,  @@@    .&@. %@@           \n" +
                "              %@(#@&     %@&   *@@      .@@*      @@.   @@%    @@#         @@/   %@@    %@@   .@@.   .&@.  @@*          \n" +
                "             .@@ .@@.    %@&   *@@      .@@*      @@.   @@%     #@@@/      @@@@@@@@@    %@@   .@@.   .&@,.%@@           \n" +
                "             %@%  &@%    %@&   *@@      .@@*      @@.   @@%        (@@*    @@/   %@@    %@@   .@@.   .&@@@(             \n" +
                "            .@@(//(@@.   #@@   (@@      .@@*      @@,   @@#   ,@@   @@(    @@/   %@@    #@@   *@@    .&@.               \n" +
                "            &@@    @@#    %@@@@@&       .@@*      .&@@@@@#     #@@&@@&     @@/   %@@     #@@@@@&.    .&@.               \n" +
                "                                                                                                                        \n");


    }


    private String pad(String s, int size) {
        int offset = s.length() % 2;
        return " ".repeat((size - s.length()) / 2) + s + " ".repeat((size - s.length()) / 2 + offset);
    }


}