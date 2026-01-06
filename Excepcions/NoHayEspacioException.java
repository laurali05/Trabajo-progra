package Excepcions;

public class NoHayEspacioException extends Exception{
    public NoHayEspacioException() {
        super("No hay más espacio en la lista donde se intenta añadir la actividad");
    }
}
