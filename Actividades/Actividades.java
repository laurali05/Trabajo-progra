package Actividades;

public class Actividades {
    public String nom;
    public int[] periodoIn = new int[3];
    public int[] periodoFin = new int[3];
    public String[] colectivos;

    /**
     * Constructor sin parámetros
     */
    public Actividades() {
        this.nom = "Charla";
        this.periodoIn = new int[] { 1, 9, 2025 };
        this.periodoFin = new int[] { 1, 12, 2025 };
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
    public Actividades(String nom, int[] periodoIn, int[] periodoFin, String[] colectivos) {
        this.nom = nom;
        this.periodoIn = periodoIn;
        this.periodoFin = periodoFin;
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
     * @param fecha
     */
    public void setPeriodoIn(int dia, int mes, int año) {
        this.periodoIn[0] = dia;
        this.periodoIn[1] = mes;
        this.periodoIn[2] = año;
    }

    /**
     * Setter: Asigna la fecha de finalización del periodo de inscripciones
     * 
     * @param fecha
     */
    public void setPeriodoFin(int dia, int mes, int año) {
        this.periodoFin[0] = dia;
        this.periodoFin[1] = mes;
        this.periodoFin[2] = año;
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
    public String getPeriodoIn() {
        String resultado = "[";

        for (int i = 0; i < periodoIn.length; i++) {
            resultado += periodoIn[i];
            if (i < periodoIn.length - 1) {
                resultado += ", ";
            }
        }
        resultado += "]";
        return resultado;
    }

    /**
     * Getter: Devuelve la fecha en la que finaliza el periodo de inscripciones
     * 
     * @return
     */
    public String getPeriodoFin() {
        String resultado = "[";

        for (int i = 0; i < periodoFin.length; i++) {
            resultado += periodoFin[i];
            if (i < periodoFin.length - 1) {
                resultado += ", ";
            }
        }
        resultado += "]";
        return resultado;
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
                "\nPeriodo de inicio: " + getPeriodoIn() +
                "\nPeriodo de fin: " + getPeriodoFin() +
                "\nColectivos: " + getColectivos();
    }

    /**
     * Método que hace una copia
     * 
     * @return
     */
    public Actividades copia() {
        Actividades a = this;
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

        if (this.periodoIn.length != a.periodoIn.length) {
            return false;
        } else {
            for (int i = 0; i < this.periodoIn.length; i++) {
                if (this.periodoIn[i] != a.periodoIn[i]) {
                    return false;
                }
            }
        }

        if (this.periodoFin.length != a.periodoFin.length) {
            return false;
        } else {
            for (int j = 0; j < this.periodoFin.length; j++) {
                if (this.periodoFin[j] != a.periodoFin[j]) {
                    return false;
                }
            }
        }

        if (this.colectivos.length != a.colectivos.length) {
            return false;
        } else {
            for (int k = 0; k < this.colectivos.length; k++) {
                if (!this.colectivos[k].equalsIgnoreCase(a.colectivos[k])) {
                    return false;
                }
            }
        }

        return true;
    }
}