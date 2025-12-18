package Actividades;

import Excepcions.ActivitatNoTrobadaException;
import Excepcions.NomActivitatDuplicatException;

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
     * Método que devuelve el número de elemenetos de la lista
     * 
     * @return
     */
    public int getNumElems() {
        return numElems;
    }

    public Actividades getActividad(int index) {
        if (index >= 0 && index < numElems) {
            return llista[index];
        }
        return null;
    }

    /**
     * Método para añadir una actividad
     * 
     * @param a
     */
    public void afegir(Actividades a) throws NomActivitatDuplicatException {
        for (int i = 0; i < numElems; i++) {
            if (llista[i].getNom().equalsIgnoreCase(a.getNom())) {
                throw new NomActivitatDuplicatException("Ya existe una actividad con este nombre: " + a.getNom());
            }
        }

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
    public LlistaActividades copia() throws NomActivitatDuplicatException {
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

    /**
     * Metode que retorna una llista especifica de una subclasse
     * Laura no te asustes de este metodo lo necesito para la opcio 2
     * (Jordi)
     * @param opcio
     * @return
     */
    public LlistaActividades llistaTipus(int opcio) {
        LlistaActividades llistaEsp = new LlistaActividades(numElems);
        if (opcio == 1) {
            for (int i = 0; i < numElems; i++) {
                if (llista[i] instanceof ActividadesDia) {
                    try {
                        llistaEsp.afegir(llista[i]);
                    } catch (NomActivitatDuplicatException e) {
                        System.err.println(e + "Activitat duplicada");
                    }
                }
            }

        }
        if (opcio == 2) {
            for (int i = 0; i < numElems; i++) {
                if (llista[i] instanceof ActividadesOnline) {
                    try {
                        llistaEsp.afegir(llista[i]);
                    } catch (NomActivitatDuplicatException e) {
                        System.err.println(e + "Activitat duplicada");
                    }
                }
            }

        }
        if (opcio == 3) {
            for (int i = 0; i < numElems; i++) {
                if (llista[i] instanceof ActividadesPeriodicas) {
                    try {
                        llistaEsp.afegir(llista[i]);
                    } catch (NomActivitatDuplicatException e) {
                        System.err.println(e + "Activitat duplicada");
                    }
                }
            }

        }
        return llistaEsp;

    }

   
    /**
     * Metode que retorna una activitat especifica de una subclasse
     * Laura no te asustes de este metodo lo necesito para la opcio 10
     * (Izan)
     * @param activitat
     * @return activitat en concreto
     */
    public Actividades buscarActividades(String activitat) throws ActivitatNoTrobadaException {
    
        int i = 0;
        boolean trobat = false;

        while (i < numElems && !trobat) {
            if (llista[i].getNom().equalsIgnoreCase(activitat)) {
                trobat = true;
            } else {
                i++;
            }
        }

        if (!trobat) {
            throw new ActivitatNoTrobadaException(activitat);

        }
        return llista[i];
    }

}
