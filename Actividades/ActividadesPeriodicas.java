package Actividades;

public class ActividadesPeriodicas extends Actividades{
    public String diaSemana;
    public int[] horario = new int[2];
    public int[] fechain = new int[3];
    public int numSemanas;
    public int limite;
    public double precio;
    public String centro;
    public String ciudad;

    public ActividadesPeriodicas(String nom, int[] periodoin, int[] periodofin, String[] colectivos, String diaSemana,
            int[] horario, int[] fechain, int numSemanas, int limite, double precio, String centro, String ciudad) {
        super(nom, periodoin, periodofin, colectivos);
        this.diaSemana = diaSemana;
        this.horario = horario;
        this.fechain = fechain;
        this.numSemanas = numSemanas;
        this.limite = limite;
        this.precio = precio;
        this.centro = centro;
        this.ciudad = ciudad;
    }
}
