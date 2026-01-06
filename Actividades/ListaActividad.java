package Actividades;

import Data.*;
import Excepcions.*;
import Inscripcions.*;

/**
 * Clase: Lista de actividades
 * Autor: Laura Lope
 */
public class ListaActividad {
    private int numElems;
    private Actividad[] llista;

    /**
     * Constructor con parámetros
     * 
     * @param mida
     */
    public ListaActividad(int mida) {
        this.llista = new Actividad[mida];
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

    public Actividad getActividad(int index) {
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
    public void afegir(Actividad a) throws NomActivitatDuplicatException, NoHayEspacioException {
        for (int i = 0; i < numElems; i++) {
            if (llista[i].getNom().equalsIgnoreCase(a.getNom())) {
                throw new NomActivitatDuplicatException("Ya existe una actividad con este nombre: " + a.getNom());
            }
        }

        if (numElems < llista.length) {
            llista[numElems] = a;
            numElems++;
        } else {
            throw new NoHayEspacioException();
        }
    }

    @Override
    public String toString() {
        String resultado = "";
        for (int i = 0; i < numElems; i++) {
            resultado += "\nActividad " + (i + 1) + ":\n";
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
    public ListaActividad copia() throws NomActivitatDuplicatException, NoHayEspacioException {
        ListaActividad nueva = new ListaActividad(llista.length);
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
    public boolean iguals(ListaActividad otra) {
        if (this.numElems != otra.numElems)
            return false;
        for (int i = 0; i < numElems; i++) {
            if (!this.llista[i].iguals(otra.llista[i]))
                return false;
        }
        return true;
    }

    /**
     * Método que elimina una actividad según su posición
     * 
     * @param pos
     */
    public void eliminarActividad(int pos) {
        for (int i = pos; i < numElems - 1; i++) {
            llista[i] = llista[i + 1];
        }
        llista[numElems - 1] = null;
        numElems--;
    }

    /**
     * Metode que retorna una llista especifica de una subclasse
     * Autor Jordi Vivas
     * 
     * @param opcio
     * @return
     */
    public ListaActividad llistaTipus(int opcio) throws NoHayEspacioException {
        ListaActividad llistaEsp = new ListaActividad(numElems);
        if (opcio == 1) {
            for (int i = 0; i < numElems; i++) {
                if (llista[i] instanceof ActividadDia) {
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
                if (llista[i] instanceof ActividadOnline) {
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
                if (llista[i] instanceof ActividadPeriodicas) {
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
     * Autor Izan Flores
     * 
     * @param activitat
     * @return activitat en concreto
     */
    public Actividad buscarActividades(String activitat) throws ActivitatNoTrobadaException {

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

    /**
     * Método que devuelve la lista de actividades en periodo de inscripción
     * Autor Laura Lope
     * 
     * @param avui
     * @return
     * @throws NomActivitatDuplicatException
     */
    public ListaActividad llistaPeriodo(Data avui) throws NomActivitatDuplicatException, NoHayEspacioException {
        ListaActividad lista = new ListaActividad(numElems);
        for (int i = 0; i < numElems; i++) {

            Actividad a = this.getActividad(i);
            if ((a.getPeriodoIn().esDataInferiorOigual(avui)) && (avui.esDataInferiorOigual(a.getPeriodoFin()))) {
                lista.afegir(a);
            }
        }

        return lista;

    }

    /**
     * Método que indica las actividades que tienen clase hoy
     * Autor Laura Lope
     * 
     * @param avui
     * @param a
     * @return
     * @throws NomActivitatDuplicatException
     */
    public ListaActividad llistaClasseAvui(Data avui, LlistaInscripcio a)
            throws NomActivitatDuplicatException, NoHayEspacioException {
        ListaActividad lista = new ListaActividad(numElems);

        int contador = 0;

        for (int i = 0; i < this.getNumElems(); i++) {
            Actividad act = llista[i];

            if (act.claseHoy(avui)) {
                contador++;
                lista.afegir(act);
                try {
                    boolean plazas = this.plazasDisponibles(act.getNom(), a);
                    boolean listEspera = a.teLlistaEspera(act);
                    System.out.println("\nActividad " + contador + ": \n\n" + act.toString() + "\nPlazas disponibles: "
                        + plazas + "\nLista de espera: " + listEspera);
                } catch (ActivitatNoTrobadaException e) {
                        System.out.println("No se ha encontrado una actividad con ese nombre");
                }
            }
        }
        return lista;
    }

    /**
     * Método que indica si hay plazas disponibles en una actividad
     * 
     * @param nom
     * @return
     */
    public boolean plazasDisponibles(String nom, LlistaInscripcio a) throws ActivitatNoTrobadaException {
        boolean resultado = false;
        boolean trobat = false;

        for (int i = 0; i < numElems; i++) {
            if (llista[i].getNom().equalsIgnoreCase(nom)) {
                trobat = true;
                if (llista[i] instanceof ActividadDia) {
                    ActividadDia dia = (ActividadDia) llista[i];
                    if (dia.getLimite() > a.contarInscritos(nom)) {
                        resultado = true;
                    }
                } else if (llista[i] instanceof ActividadPeriodicas) {
                    ActividadPeriodicas periodica = (ActividadPeriodicas) llista[i];
                    if (periodica.getLimite() > a.contarInscritos(nom)) {
                        resultado = true;
                    }
                } else if (llista[i] instanceof ActividadOnline) {
                    if (20 > a.contarInscritos(nom)) {
                        resultado = true;
                    }
                }
            }
        }
        if (!trobat) {
            throw new ActivitatNoTrobadaException(nom);
        }
        return resultado;
    }

    /**
     * Método que devuelve el nombre de las actividades activas
     * 
     * @param avui
     * @return
     */
    public String actActivas(Data avui) {
        String resultado = "";

        for (int i = 0; i < numElems; i++) {
            if (llista[i] instanceof ActividadDia) {
                ActividadDia dia = (ActividadDia) llista[i];
                if (avui.esIgual(dia.getFechaIn())) {
                    resultado = resultado + "- " + dia.getNom() + "\n";
                }

            } else if (llista[i] instanceof ActividadPeriodicas) {
                ActividadPeriodicas periodica = (ActividadPeriodicas) llista[i];

                Data fechaFin = periodica.getFechaIn().sumarDias(periodica.getNumSemanas() * 7);

                if (periodica.getFechaIn().esDataInferiorOigual(avui) && (avui.esDataInferiorOigual(fechaFin))) {
                    resultado = resultado + "- " + periodica.getNom() + "\n";
                }
            } else if (llista[i] instanceof ActividadOnline) {
                ActividadOnline online = (ActividadOnline) llista[i];

                Data fechaFin = online.getFechaIn().sumarDias(online.getPeriodoVis());

                if (online.getFechaIn().esDataInferiorOigual(avui) && (avui.esDataInferiorOigual(fechaFin))) {
                    resultado = resultado + "- " + online.getNom() + "\n";
                }
            }
        }

        return resultado;
    }

    /**
     * Método que devuelve el nombre de las actividades con plazas disponibles
     * 
     * @param a
     * @return
     * @throws NomActivitatDuplicatException
     * @throws ActivitatNoTrobadaException
     */
    public ListaActividad actPlazasDisponibles(LlistaInscripcio a)
            throws NomActivitatDuplicatException, ActivitatNoTrobadaException, NoHayEspacioException {
        ListaActividad act = new ListaActividad(numElems);

        for (int i = 0; i < numElems; i++) {
            if (this.plazasDisponibles(llista[i].getNom(), a)) {
                act.afegir(llista[i]);
            }
        }
        return act;
    }

    /**
     * Lista de nombres de las actividades
     * 
     * @return
     */
    public String nomActividades() {
        String nomActividades = " ";

        for (int i = 0; i < this.getNumElems(); i++) {
            nomActividades += "\n\t - " + llista[i].getNom();
        }

        return nomActividades;
    }

    /**
     * Elimina las actividades que están fuera del periodo de inscripción y que no
     * han llegado a un mínimo de inscritos
     * 
     * @param a
     * @param avui
     */
    public void elActNoCondiciones(LlistaInscripcio a, Data avui) {
        for (int i = this.getNumElems() - 1; i >= 0; i--) {

            if (llista[i] instanceof ActividadDia) {
                ActividadDia dia = (ActividadDia) llista[i];

                if ((!avui.esDataInferiorOigual(dia.getPeriodoFin()))
                        && (a.contarInscritos(dia.getNom()) < (0.1 * dia.getLimite()))) {
                    this.eliminarActividad(i);
                    System.out.println("La actividad '" + dia.getNom() + "' se ha eliminado correctamente");
                }
            } else if (llista[i] instanceof ActividadPeriodicas) {
                ActividadPeriodicas periodica = (ActividadPeriodicas) llista[i];

                if ((!avui.esDataInferiorOigual(periodica.getPeriodoFin()))
                        && (a.contarInscritos(periodica.getNom()) < (0.1 * periodica.getLimite()))) {
                    this.eliminarActividad(i);
                    System.out.println("La actividad '" + periodica.getNom() + "' se ha eliminado correctamente");

                }
            } else if (llista[i] instanceof ActividadOnline) {
                ActividadOnline online = (ActividadOnline) llista[i];

                if (a.contarInscritos(online.getNom()) < 20) {
                    this.eliminarActividad(i);
                    System.out.println("La actividad '" + online.getNom() + "' se ha eliminado correctamente");

                }
            }
        }
    }
}
