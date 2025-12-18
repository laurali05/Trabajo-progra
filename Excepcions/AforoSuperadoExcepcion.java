package Excepcions;

public class AforoSuperadoExcepcion extends Exception{

    public AforoSuperadoExcepcion(String nomActividad) {
        super("El aforo de la actividad '" + nomActividad + "' ha sido superado");
    }
}
