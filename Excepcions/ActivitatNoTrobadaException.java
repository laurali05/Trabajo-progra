package Excepcions;

public class ActivitatNoTrobadaException extends Exception {

    public ActivitatNoTrobadaException(String nomActividad) {
        super("No se ha podido encontrar la actividad '" + nomActividad + "' en la lista");

    }

}
