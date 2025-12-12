import Controlador.ConcesionarioControlador;
import Vista.IVistaInterfaz;
import Vista.VistaCompleja;
import Vista.VistaSimple;

public class Programa {

    public static void main(String[] args) {
        IVistaInterfaz vista = new VistaCompleja();
        ConcesionarioControlador controlador = new ConcesionarioControlador(vista);
        controlador.run();



    }
}
