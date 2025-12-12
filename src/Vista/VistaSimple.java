package Vista;

import Modelo.*;

import java.util.Date;
import java.util.Scanner;

public class VistaSimple implements IVistaInterfaz {
    private Scanner sc = new Scanner(System.in);

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

   @Override
   public TOpcionesVendedor menuOpcionesVendedor(String mensaje) {
        imprimirSeparador("=");
        imprimirMensaje(mensaje, TColores.GREEN);
        TOpcionesVendedor[] opcionesMenu = TOpcionesVendedor.values();
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
    public VentaDTO registrarVenta(int idVenta, ClienteDTO cliente, CocheDTO coche, float precioVenta, VendedorDTO vendedor) {

        Date fecha = Date.from(java.time.ZonedDateTime.now().toInstant());


        return new VentaDTO(idVenta, cliente, coche, fecha, precioVenta, vendedor);
    }

    @Override
    public CocheDTO anhadirCoche() {


        imprimirMensaje("Por favor, introduce los datos del coche a registrar", TColores.GREEN);
        String marca = pedirCadena("Introduce marca:", false);
        String modelo = pedirCadena("Introduce modelo:", false);
        String matricula = pedirCadena("Introduce matrícula:", false);
        float precio = pedirFloat("Introduce el precio:");
        int anhoUsuario = (pedirInt("Introduce el año:"));


        float kilometros = pedirFloat("Introduce los kilómetros:");
        return new CocheDTO(marca, modelo, matricula, precio, anhoUsuario, kilometros);
    }


    @Override
    public ClienteDTO registrarCliente() {
        imprimirMensaje("Por favor, introduce los datos del cliente a registrar", TColores.GREEN);
        String dni = pedirCadena("Introduce el dni del cliente:", false);
        if (dni.length() != 9) {
            imprimirMensaje("El dni debe tener una longitud de 8 números y un caracter.", TColores.RED);
            dni = pedirCadena("Introduce el dni del cliente:", false);
        }
        String nombre = pedirCadena("Introduce el nombre del cliente:", false);
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
        String dni = pedirCadena("Introduce el dni del vendedor:", false);
        if (dni.length() != 9) {
            imprimirMensaje("El dni debe tener una longitud de 8 números y un caracter.", TColores.RED);
            dni = pedirCadena("Introduce el dni del vendedor:", false);
        }
        String nombre = pedirCadena("Introduce el nombre del vendedor:", false);

        return new VendedorDTO(nombre, numeroVendedor, dni);
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
                sc.nextLine();

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
                sc.nextLine();

            }
        }
    }

    @Override
    public void mostrarCoche(CocheDTO coche) {
        System.out.println(coche.getMarca() + " " + coche.getModelo() + " " + coche.getMatricula() + " "
                + coche.getAnho() + " " + (coche.getKilometros() + "Km.") + " " + (coche.getPrecio()) + "€");


    }

    @Override
    public void mostrarVenta(VentaDTO venta) {

        System.out.println("ID " + venta.getIdVenta() + " " + venta.getCliente().getNombre() + " " + venta.getCliente().getDni()
                + " " + venta.getCoche().getMarca() + " " + venta.getCoche().getModelo() + " " + venta.getCoche().getPrecio() + "€"
                + " " + venta.getCoche().getAnho() + " " + "precio final de venta " +
                venta.getPrecioVenta() + "€" + " " + venta.getFecha());


    }

    @Override
    public void mostrarVendedor(VendedorDTO vendedor) {
        System.out.printf("Número de vendedor " + vendedor.getNumeroVendedor() + "Nombre " + vendedor.getNombre());

    }

    @Override
    public void mostrarCliente(ClienteDTO cliente) {

        System.out.println(cliente.getNombre() + " " + cliente.getDni() + " " + cliente.getTelefono());

    }

    @Override
    public void imprimirSeparador(String mensaje) {
        System.out.println("\n" + "======================" + mensaje + "==========================="
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


}

