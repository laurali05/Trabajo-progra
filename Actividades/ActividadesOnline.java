package Actividades;

public class ActividadesOnline extends Actividades{
    public int[] fechain = new int[3];
    public int periodovis;
    public String enlace;

    public ActividadesOnline(String nom, int[] periodoin, int[] periodofin, String[] colectivos, int[] fechain,
            int periodovis, String enlace) {
        super(nom, periodoin, periodofin, colectivos);
        this.fechain = fechain;
        this.periodovis = periodovis;
        this.enlace = enlace;
    }

}

