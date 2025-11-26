package Actividades;

public class ActividadesOnline extends Actividades {
    public int[] fechaIn = new int[3];
    public int periodoVis;
    public String enlace;

    public ActividadesOnline(String nom, int[] periodoIn, int[] periodoFin, String[] colectivos, int[] fechaIn,
            int periodoVis, String enlace) {
        super(nom, periodoIn, periodoFin, colectivos);
        this.fechaIn = fechaIn;
        this.periodoVis = periodoVis;
        this.enlace = enlace;
    }
    
}
