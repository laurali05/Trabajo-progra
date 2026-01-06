package Excepcions;
import Data.Data;

public class FechaIncorrectaExcepcion extends Exception {
    public FechaIncorrectaExcepcion(Data data) {
        super("La fecha '" + data + "' no está entre septiembre y diciembre. \n Se ha añadido automaticamente la fecha por defecto");
    }
}
