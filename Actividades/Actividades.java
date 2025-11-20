package Actividades;

public class Actividades{
    public String nom;
    public int[] periodoin = new int[3];
    public int[] periodofin = new int[3]; 
    public String[] colectivos = {"PDI", "PTGAS","Estudiants"};
    
    public Actividades(String nom, int[] periodoin, int[] periodofin, String[] colectivos) {
        this.nom = nom;
        this.periodoin = periodoin;
        this.periodofin = periodofin;
        this.colectivos = colectivos;
    }

}