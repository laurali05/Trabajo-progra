package Actividades;

public class ActividadesDia extends Actividades{
    public int[] fecha = new int[3];
    public int[] horario = new int[2];
    public int limite;
    public double precio;
    public String ciudad;
    
    public ActividadesDia(String nom, int[] periodoin, int[] periodofin, String[] colectivos, int[] fecha,
            int[] horario, int limite, double precio, String ciudad) {
        super(nom, periodoin, periodofin, colectivos);
        this.fecha = fecha;
        this.horario = horario;
        this.limite = limite;
        this.precio = precio;
        this.ciudad = ciudad;
    }

}
