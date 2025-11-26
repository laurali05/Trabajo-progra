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
        this.nom = "'Charla'";
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
    public Actividades(String nom, int[] periodoIn, int[] periodoFin, String[] colectivos) { // TODO: Cambiar el
                                                                                             // constructor para no
                                                                                             // tener que meter "new" en
                                                                                             // ningún sitio
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
     * Setter: Asigna los colectivos que pueden estar en la actividad
     * 
     * @param nombres
     */
    public void setColectivos(String[] nombres) {
        this.colectivos = nombres;
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

}