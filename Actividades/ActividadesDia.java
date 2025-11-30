package Actividades;

import Data.Data;

/**
 * Clase: Actividades día
 * Autor: Laura Lope
 */
public class ActividadesDia extends Actividades {
    public Data fecha;
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
    public ActividadesDia(String nom, Data periodoIn, Data periodoFin, String[] colectivos, Data fecha,
            int[] horario, int limite, double precio, String ciudad) {
        super(nom, periodoIn, periodoFin, colectivos);

        if (fecha.getMes() >= 9 && fecha.getMes() <= 12) {
            this.fecha = fecha;
        } else {
            this.fecha = new Data(); // Si no cumple la condición devuelve la fecha por defecto
        }

        if (horario[0] <= 23 && horario[0] >= 0 && horario[1] <= 59 && horario[1] >= 0) {
            this.horario = horario;
        } else {
            this.horario = new int[] { 00, 00 };
        }
        this.limite = limite;
        this.precio = precio;
        this.ciudad = ciudad;
    }

    /**
     * Setter: fija el día de la actividad
     * 
     * @param fecha
     */
    public void setFecha(Data fecha) {
        if (fecha.getMes() >= 9 && fecha.getMes() <= 12) {
            this.fecha = fecha;
        } else {
            this.fecha = new Data();
        }
    }

    /**
     * Setter: fija cuando será la actividad
     * 
     * @param hora
     * @param min
     */
    public void setHorario(int hora, int min) {
        if (hora <= 23 && hora >= 0 && min <= 59 && min >= 0) {
            horario[0] = hora;
            horario[1] = min;
        } else {
            horario[0] = 0;
            horario[1] = 0;
        }
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

    /**
     * Getter: devulve la fecha de la actividad
     * 
     * @return
     */
    public Data getFecha() {
        return this.fecha;
    }

    /**
     * Getter: devuelve en que horario es la actividad
     * 
     * @return
     */
    public String getHorario() {
        String resultado = "";
        for (int i = 0; i < horario.length; i++) {
            if (horario[i] < 10) {
                resultado += "0" + horario[i];
            } else {
                resultado += horario[i];
            }
            if (i < horario.length - 1) {
                resultado += ":";
            }
        }
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

    @Override
    public String toString() {
        return super.toString() + "\nFecha: " + fecha +
                "\nHorario: " + getHorario() +
                "\nLímite de asistentes: " + limite +
                "\nPrecio de la actividad: " + precio +
                "\nCiudad: " + ciudad;
    }

    /**
     * Método que hace una copia
     * 
     * @return
     */
    public ActividadesDia copia() {
        ActividadesDia a = new ActividadesDia(this.nom, this.periodoIn, this.periodoFin, this.colectivos, this.fecha,
                this.horario, this.limite, this.precio, this.ciudad);
        return a;
    }

    /**
     * Método que comprueba si dos actividades son iguales
     * 
     * @param a
     * @return
     */
    public boolean iguals(ActividadesDia a) {
        super.iguals(a);
        if (!a.fecha.esIgual(this.fecha)) {
            return false;
        }

        if (this.horario.length != a.horario.length) {
            return false;
        } else {
            for (int i = 0; i < this.horario.length; i++) {
                if (this.horario[i] != this.horario[i]) {
                    return false;
                }
            }
        }

        if (this.limite != a.limite) {
            return false;
        }

        if (this.precio != a.precio) {
            return false;
        }

        if (!this.ciudad.equalsIgnoreCase(a.ciudad)) {
            return false;
        }

        return true;
    }
}
