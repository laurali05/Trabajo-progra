package Excepcions;

public class NomActivitatDuplicatException extends Exception {
    public NomActivitatDuplicatException(String nomActividad) {
        super("La actividad con el nombre '" + nomActividad + "' ya esxite en la lista");

    }
}