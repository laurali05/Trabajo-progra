package Actividades;
import Data.Data;

public class ActividadesOnline extends Actividades {
    public int[] fechaIn = new int[3];
    public int periodoVis;
    public String enlace;

    public ActividadesOnline(String nom, Data periodoIn, Data periodoFin, String[] colectivos, int[] fechaIn,
            int periodoVis, String enlace) {
        super(nom, periodoIn, periodoFin, colectivos);
        this.fechaIn = fechaIn;
        this.periodoVis = periodoVis;
        this.enlace = enlace;
    }
    
    //TODO 1: Hacer los setters
    //TODO 2: Hacer los getter
    //TODO 3: Hacer el toString
    //TODO 4: Hacer el copia
    //TODO 5: Hacer el iguals
}
