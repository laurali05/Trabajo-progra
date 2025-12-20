package Actividades;

import Data.Data;

/**
 * Clase: Actividades
 * Autor: Laura Lope
 */
public class Actividades {
    public String tipo;
    public String nom;
    public Data periodoIn;
    public Data periodoFin;
    public String colectivos;

    /**
     * Constructor sin parámetros
     */
    public Actividades() {
        this.tipo = "Dia";
        this.nom = "Charla";
        this.periodoIn = new Data(1, 9, 2025);
        this.periodoFin = new Data(1, 12, 2025);
        this.colectivos = ("PDI, PTGAS, Estudiants");
    }

    /**
     * Constructor con parámetros
     * 
     * @param nom        Nombre de la actividad
     * @param periodoin  Fecha en la que se inicia el periodo de inscripciones
     * @param periodofin Fecha en la que finaliza el periodo de inscripciones
     * @param colectivos Colectivos que pueden formar parte de la actvidad
     */
    public Actividades(String nom, Data periodoIn, Data periodoFin, String colectivos) {
        this.nom = nom;

        if (periodoIn.getMes() >= 9 && periodoIn.getMes() <= 12) {
            this.periodoIn = periodoIn;
        } else {
            this.periodoIn = new Data(); // Si no cumple la condición devuelve la fecha por defecto
        }

        if (periodoFin.getMes() >= 9 && periodoFin.getMes() <= 12) {
            this.periodoFin = periodoFin;
        } else {
            this.periodoFin = new Data();
        }

        this.colectivos = colectivos;
    }

    /**
     * Setter: Asigna el tipo del que es la actividad
     * 
     * @param tipo
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * Setter: Asigna el nombre de la actividad
     * 
     * @param actividad
     */
    public void setNom(String actividad) {
        this.nom = actividad;
    }

    /**
     * Setter: Asigna la fecha de inicio del periodo de inscripciones
     * 
     * @param periodoIn
     */
    public void setPeriodoIn(Data periodoIn) {
        if (periodoIn.getMes() >= 9 && periodoIn.getMes() <= 12) {
            this.periodoIn = periodoIn;
        } else {
            this.periodoIn = new Data();
        }
    }

    /**
     * Setter: Asigna la fecha de finalización del periodo de inscripciones
     * 
     * @param periodoFin
     */
    public void setPeriodoFin(Data periodoFin) {
        if (periodoFin.getMes() >= 9 && periodoFin.getMes() <= 12) {
            this.periodoFin = periodoFin;
        } else {
            this.periodoFin = new Data();
        }
    }

    /**
     * Setter: Asigna los colectivos que pueden asistir a la actividad
     * 
     */
    public void setColectivos(String colectivos) {
        this.colectivos = colectivos;
    }

    /**
     * Getter: Devuelve el tipo de la actividad
     * 
     * @return
     */
    public String getTipo() {
        return this.tipo;
    }

    /**
     * Getter: Devuelve el nombre de la actividad
     * 
     * @return
     */
    public String getNom() {
        return this.nom;
    }

    /**
     * Getter: Devuelve la fecha en la que se inicia el periodo de inscripciones
     * 
     * @return
     */
    public Data getPeriodoIn() {
        return periodoIn;
    }

    /**
     * Getter: Devuelve la fecha en la que finaliza el periodo de inscripciones
     * 
     * @return
     */
    public Data getPeriodoFin() {
        return periodoFin;
    }

    /**
     * Getter: Devuelve los distintos colectivos que pueden asistir a la actividad
     * actual
     * 
     * @return
     */
    public String getColectivos() {
        if (colectivos == null) {
            return "[]";
        }

        String resultado = this.colectivos;

        return resultado;
    }

    @Override
    public String toString() {
        return "Tipo de Actividad: " + tipo +
                "\nActividad: " + nom +
                "\nPeriodo de inicio: " + periodoIn +
                "\nPeriodo de fin: " + periodoFin +
                "\nColectivos: " + colectivos;
    }

    /**
     * Método que hace una copia
     * 
     * @return
     */
    public Actividades copia() {
        Actividades a = new Actividades();
        a.tipo = this.tipo;
        a.nom = this.nom;
        a.periodoIn = this.periodoIn.copia();
        a.periodoFin = this.periodoFin.copia();
        a.colectivos = this.colectivos;
        return a;
    }

    /**
     * Método que compara si dos actividades son iguales
     * 
     * @param a
     * @return
     */
    public boolean iguals(Actividades a) {
        if (!this.tipo.equalsIgnoreCase(a.tipo)) {
            return false;
        }

        if (!this.nom.equalsIgnoreCase(a.nom)) {
            return false;
        }

        if (!this.periodoIn.esIgual(a.periodoIn)) {
            return false;
        }

        if (!this.periodoFin.esIgual(a.periodoFin)) {
            return false;
        }

        if (!this.colectivos.equalsIgnoreCase(a.colectivos)) {
            return false;
        }

        return true;
    }
}