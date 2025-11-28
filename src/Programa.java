import Controlador.ConcesionarioControlador;
import Vista.VistaSimple;

public class Programa {

    public static void main(String[] args) {
        VistaSimple vista = new VistaSimple();
        ConcesionarioControlador controlador = new ConcesionarioControlador(vista);


        controlador.run();

    }
}
