package Actividades;

import Data.Data;

public class ActividadesOnline extends Actividades {
    public Data fechaIn;
    public int periodoVis; // Periodo de visualización
    public String enlace;

    public ActividadesOnline(String nom, Data periodoIn, Data periodoFin, String[] colectivos, Data fechaIn,
            int periodoVis, String enlace) {
        super(nom, periodoIn, periodoFin, colectivos);
        
        if (fechaIn.getMes() >= 9 && fechaIn.getMes() <= 12) {
            this.fechaIn = fechaIn;
        } else {
            this.fechaIn = new Data(); // Si no cumple la condición devuelve la fecha por defecto
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
    public ActividadesOnline copia() {
        ActividadesOnline a = new ActividadesOnline(this.nom, this.periodoIn, this.periodoFin, this.colectivos,
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
    public boolean iguals(ActividadesOnline a) {
        super.iguals(a);
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

}
