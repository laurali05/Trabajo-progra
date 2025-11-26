package Actividades;

import java.util.Arrays;

public class ActividadesDia extends Actividades {
    public int[] fecha = new int[3];
    public int[] horario = new int[2];
    public int limite;
    public double precio;
    public String ciudad;

    /**
     * Constructor con parámetros
     * 
     * @param nom
     * @param periodoIn
     * @param periodoFin
     * @param colectivos
     * @param fecha      Fecha en la que tendrá lugar la actividad
     * @param horario    En que horario tendrá lugar la actividad
     * @param limite     Límite de personas que pueden acceder a la actividad
     * @param precio     Precio de la actividdd
     * @param ciudad     En que ciudad es la actividad
     */
    public ActividadesDia(String nom, int[] periodoIn, int[] periodoFin, String[] colectivos, int[] fecha,
            int[] horario, int limite, double precio, String ciudad) {
        super(nom, periodoIn, periodoFin, colectivos);
        this.fecha = fecha;
        this.horario = horario;
        this.limite = limite;
        this.precio = precio;
        this.ciudad = ciudad;
    }

    // TODO 1: Hacer los setters

    /**
     * Setter: fija la fecha en la que tendrá lugar la actividad
     * 
     * @param dia
     * @param mes
     * @param año
     */
    public void setFecha(int dia, int mes, int año) {
        fecha[0] = dia;
        fecha[1] = mes;
        fecha[2] = año;
    }

    /**
     * Setter: fija cuando será la actividad
     * 
     * @param horas
     * @param min
     */
    public void setHorario(int horas, int min) {
        horario[0] = horas;
        horario[1] = min;
    }

    /**
     * Setter: fija el número máximo de personas que pueden acceder a esa actividad
     * 
     * @param limite
     */
    public void setLimite(int limite) {
        this.limite = limite;
    }

    /**
     * Setter: fija el precio de la actividad
     * 
     * @param precio
     */
    public void setPrecio(double precio) {
        this.precio = precio;
    }

    /**
     * Setter: fija la ciudad de la actividad
     * 
     * @param ciudad
     */
    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    // TODO 2: Hacer los getter

    /**
     * Getter: devulve la fecha de la actividad
     * 
     * @return
     */
    public String getFecha() {
        String resultado = "[";

        for (int i = 0; i < fecha.length; i++) {
            resultado += fecha[i];
            if (i < fecha.length - 1) {
                resultado += ", ";
            }
        }
        resultado += "]";
        return resultado;
    }

    /**
     * Getter: devuelve en que horario es la actividad
     * 
     * @return
     */
    public String getHorario() {
        String resultado = "[";

        for (int i = 0; i < horario.length; i++) {
            resultado += horario[i];
            if (i < horario.length - 1) {
                resultado += ", ";
            }
        }
        resultado += "]";
        return resultado;
    }

    /**
     * Getter: devuelve el límite de personas que pueden ir a la actividad
     * 
     * @return
     */
    public int getLimite() {
        return this.limite;
    }

    /**
     * Getter: devuelve el precio de la actividad
     * 
     * @return
     */
    public double getPrecio() {
        return this.precio;
    }

    /**
     * Getter: devuelve la ciudad donde tendrá lugar la actividad
     * 
     * @return
     */
    public String getCiudad() {
        return this.ciudad;
    }

    // TODO 3: Hacer el toString
    @Override
    public String toString() {
        return super.toString() + "\nFecha: " + getFecha() +
                "\nHorario: " + getHorario() +
                "\nLímite de asistentes: " + limite +
                "\nPrecio actividad: " + precio +
                "\nCiudad " + ciudad;
    }

    // TODO 4: Hacer el copia

    /** */
    public ActividadesDia copia(){
        ActividadesDia a = this;
        return a;
    }

    // TODO 5: Hacer el iguals

}
