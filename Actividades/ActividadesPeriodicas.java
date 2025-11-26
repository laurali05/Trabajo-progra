package Actividades;

public class ActividadesPeriodicas extends Actividades {
    public String diaSemana;
    public int[] horario = new int[2];
    public int[] fechaIn = new int[3];
    public int numSemanas;
    public int limite;
    public double precio;
    public String centro;
    public String ciudad;

    public ActividadesPeriodicas(String nom, int[] periodoIn, int[] periodoFin, String[] colectivos, String diaSemana,
            int[] horario, int[] fechaIn, int numSemanas, int limite, double precio, String centro, String ciudad) {
        super(nom, periodoIn, periodoFin, colectivos);
        this.diaSemana = diaSemana;
        this.horario = horario;
        this.fechaIn = fechaIn;
        this.numSemanas = numSemanas;
        this.limite = limite;
        this.precio = precio;
        this.centro = centro;
        this.ciudad = ciudad;
    }

    //TODO 1: Hacer los setters
    //TODO 2: Hacer los getter
    //TODO 3: Hacer el toString
    //TODO 4: Hacer el copia
    //TODO 5: Hacer el iguals
}
