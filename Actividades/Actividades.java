package Actividades;

import Data.Data;

/**
 * Clase: Actividades 
 * Autor: Laura Lope
 */
public class Actividades {
    public String nom;
    public Data periodoIn;
    public Data periodoFin;
    public String[] colectivos;

    /**
     * Constructor sin parámetros
     */
    public Actividades() {
        this.nom = "Charla";
        this.periodoIn = new Data(1, 9, 2025);
        this.periodoFin = new Data(1, 12, 2025);
        this.colectivos = new String[] { "PDI", "PTGAS", "Estudiants" };
    }

    /**
     * Constructor con parámetros
     * 
     * @param nom        Nombre de la actividad
     * @param periodoin  Fecha en la que se inicia el periodo de inscripciones
     * @param periodofin Fecha en la que finaliza el periodo de inscripciones
     * @param colectivos Colectivos que pueden formar parte de la actvidad
     */
    public Actividades(String nom, Data periodoIn, Data periodoFin, String[] colectivos) {
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
     * @param PDI
     * @param PTGAS
     * @param Estudiants
     */
    public void setColectivos(Boolean PDI, Boolean PTGAS, Boolean Estudiants) {
        int n = 0;
        if (PDI == true) {
            n++;
        }

        if (PTGAS) {
            n++;
        }

        if (Estudiants) {
            n++;
        }

        colectivos = new String[n];
        int index = 0;
        if (PDI) {
            colectivos[index] = "PDI";
            index++;
        }
        if (PTGAS) {
            colectivos[index] = "PTGAS";
            index++;
        }
        if (Estudiants) {
            colectivos[index] = "Estudiants";
            index++;
        }
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
        if (colectivos == null || colectivos.length == 0) {
            return "[]";
        }

        String resultado = "[";

        for (int i = 0; i < colectivos.length; i++) {
            resultado += colectivos[i]; // Va añadiendo los distintos colectivos pero comprobando que no se pasa de la
                                        // longitud del array
            if (i < colectivos.length - 1) {
                resultado += ", "; // Añade una coma al final de cada posición exceptuando la última
            }
        }
        resultado += "]";
        return resultado;
    }

    @Override
    public String toString() {
        return "Actividad: " + nom +
                "\nPeriodo de inicio: " + periodoIn +
                "\nPeriodo de fin: " + periodoFin +
                "\nColectivos: " + getColectivos();
    }

    /**
     * Método que hace una copia
     * 
     * @return
     */
    public Actividades copia() {
        Actividades a = new Actividades();
        a.nom = this.nom;
        a.periodoIn = this.periodoIn;
        a.periodoFin = this.periodoFin;
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
        if (!this.nom.equalsIgnoreCase(a.nom)) {
            return false;
        }

        if (!this.periodoIn.esIgual(a.periodoIn)) {
            return false;
        }

        if (!this.periodoFin.esIgual(a.periodoFin)) {
            return false;
        }

        if (this.colectivos.length != a.colectivos.length) {
            return false;
        } else {
            for (int i = 0; i < this.colectivos.length; i++) {
                if (!this.colectivos[i].equalsIgnoreCase(a.colectivos[i])) {
                    return false;
                }
            }
        }

        return true;
    }
}