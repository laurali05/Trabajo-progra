package Actividades;

/**
 * Clase: Lista de actividades
 * Autor: Laura Lope
 */
public class LlistaActividades {
    private int numElems;
    private Actividades[] llista;

    /**
     * Constructor con parámetros
     * 
     * @param mida
     */
    public LlistaActividades(int mida) {
        this.llista = new Actividades[mida];
        this.numElems = 0;
    }

    /**
     * Método para añadir una actividad
     * 
     * @param a
     */
    public void afegir(Actividades a) {
        if (numElems < llista.length) {
            llista[numElems] = a;
            numElems++;
        }
    }

    @Override
    public String toString() {
        String resultado = "";
        for (int i = 0; i < numElems; i++) {
            resultado += "Actividad " + (i + 1) + ":\n";
            String[] lineas = llista[i].toString().split("\n");
            for (int j = 0; j < lineas.length; j++) {
                resultado += " " + lineas[j] + "\n";
            }
        }
        return resultado;
    }

    /**
     * Métood que hace una copia de la lista
     * 
     * @return
     */
    public LlistaActividades copia() {
        LlistaActividades nueva = new LlistaActividades(llista.length);
        for (int i = 0; i < numElems; i++) {
            nueva.afegir(llista[i]); // Si quieres copia profunda: implementar método copia en Actividades
        }
        return nueva;
    }

    /**
     * Método que compara si dos listas son iguales
     * 
     * @param otra
     * @return
     */
    public boolean iguals(LlistaActividades otra) {
        if (this.numElems != otra.numElems)
            return false;
        for (int i = 0; i < numElems; i++) {
            if (!this.llista[i].iguals(otra.llista[i]))
                return false;
        }
        return true;
    }

}
