package Actividades;

import Data.Data;
import Excepcions.FechaIncorrectaExcepcion;

/**
 * Clase: Actividades online
 * Autor: Laura Lope
 */
public class ActividadOnline extends Actividad {
    private Data fechaIn;
    private int periodoVis; // Periodo de visualización
    private String enlace;

    /**
     * Constructor sin parámetros
     * 
     */
    public ActividadOnline() {
        super();
        this.fechaIn = new Data(2, 2, 2020);
        this.periodoVis = 30;
        this.enlace = "https://urv.cat/formacion-digital";
    }

    /**
     * Constructor
     * 
     * 
     * @param nom
     * @param periodoIn
     * @param periodoFin
     * @param colectivos
     * @param fechaIn
     * @param periodoVis
     * @param enlace
     */
    public ActividadOnline(String tipo, String nom, Data periodoIn, Data periodoFin, String colectivos, Data fechaIn,
            int periodoVis, String enlace) throws FechaIncorrectaExcepcion {
        super(tipo, nom, periodoIn, periodoFin, colectivos);

        setTipo("Online");

        if (fechaIn.getMes() >= 9 && fechaIn.getMes() <= 12) {
            this.fechaIn = fechaIn;
        } else {
            this.fechaIn = new Data(); // Si no cumple la condición devuelve la fecha por defecto
            throw new FechaIncorrectaExcepcion(fechaIn);
        }

        this.periodoVis = periodoVis;
        this.enlace = enlace;
    }

    /**
     * Setter: determina la fecha de inicio de la actividad
     * 
     * @param fechaIn
     */
    public void setFechaIn(Data fechaIn) {
        if (fechaIn.getMes() >= 9 && fechaIn.getMes() <= 12) {
            this.fechaIn = fechaIn;
        } else {
            this.fechaIn = new Data(); // Si no cumple la condición devuelve la fecha por defecto
        }
    }

    /**
     * Setter: determina cuantos días se puede acceder a la actividad
     * 
     * @param dias
     */
    public void setPeriodoVis(int dias) {
        this.periodoVis = dias;
    }

    /**
     * Setter: nos dice cual es el enlace de la actividad
     * 
     * @param enlace
     */
    public void setEnlace(String enlace) {
        this.enlace = enlace;
    }

    /**
     * Getter: devuelve la fecha de inicio de la actividad
     * 
     * @return
     */
    public Data getFechaIn() {
        return fechaIn;
    }

    /**
     * Getter: devuelve el periodo de visualización de la actividad
     * 
     * @return
     */
    public int getPeriodoVis() {
        return periodoVis;
    }

    /**
     * Getter: devuelve el enlace para acceder a la actividad
     * 
     * @return
     */
    public String getEnlace() {
        return enlace;
    }

    @Override
    public String toString() {
        return super.toString() + "\nFecha de inicio: " + fechaIn +
                "\nPeriodo de visualización: " + periodoVis +
                "\nEnlace de la actividad: " + enlace;
    }

    /**
     * Método que hace una copia
     * 
     * @return
     */
    public ActividadOnline copia() throws FechaIncorrectaExcepcion {
        ActividadOnline a = new ActividadOnline(this.getTipo(), this.getNom(), this.getPeriodoIn(),
                this.getPeriodoFin(),
                this.getColectivos(),
                this.fechaIn,
                this.periodoVis, this.enlace);
        return a;
    }

    /**
     * Método que comprueba si dos actividades son iguales
     * 
     * @param a
     * @return
     */
    public boolean iguals(ActividadOnline a) {
        if (super.iguals(a) == false) {
            return false;
        }
        if (!a.fechaIn.esIgual(this.fechaIn)) {
            return false;
        }

        if (this.periodoVis != a.periodoVis) {
            return false;
        }

        if (!this.enlace.equalsIgnoreCase(a.enlace)) {
            return false;
        }

        return true;
    }

    @Override
    public boolean claseHoy(Data avui) {
        Data fechaFin = this.fechaIn.sumarDias(this.periodoVis);
        return this.fechaIn.esDataInferiorOigual(avui) && avui.esDataInferiorOigual(fechaFin);
    }

}
