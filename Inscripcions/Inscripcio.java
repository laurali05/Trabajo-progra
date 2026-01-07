package Inscripcions;

import Actividades.*;
import Data.Data;
import Usuari.Usuari;

/**
 * Clase: Inscripcio
 * Autor: Izan Flores
 */
public class Inscripcio {

    private Usuari persona;
    private Actividad activitat;
    private int valoracio;
    private Data data;

    /**
     * Constructor sense parametres
     */
    public Inscripcio() {
        this.persona = new Usuari();
        this.activitat = new ActividadDia();
        this.valoracio = -1;
        this.data = new Data(4, 10, 2025);
    }

    /**
     * Constructor con parametros
     *
     * @param persones personas que forman parte de la actividad
     * @param activitat actividad en cuestión
     */
    public Inscripcio(Usuari persona, Actividad activitat, Data data) {
        this.persona = persona;
        this.activitat = activitat;
        this.valoracio = -1;
        this.data = data;
    }

    /**
     * Metodo copia
     *
     * @return una copia de la inscripcion seleccionada
     */
    public Inscripcio copia() {
        Inscripcio novains = new Inscripcio(this.persona, this.activitat, this.data);
        return novains;
    }

    //Getters y setters

    /**
     * Getter de l'usuari
     * 
     * @return usuari en concret
     */
    public Usuari getUsuari() {
        return persona;
    }

     /**
     * Getter de l'activitat
     * 
     * @return activitat en concret
     */
    public Actividad getActivitat() {
        return activitat;
    }

     /**
     * Getter de la valoracio
     * 
     * @return valoracio en concret
     */
    public int getValoracio() {
        return valoracio;
    }

     /**
     * Getter de la data
     * 
     * @return data en concret
     */
    public Data getData() {
        return data;
    }

    /**
     * Setter de l'usuari
     * 
     * @param persona usuari que es vol posar
     */
    public void setUsuari(Usuari persona) {
        this.persona = persona;
    }

    /**
     * Setter de la activitat
     * 
     * @param activitat activitat que es vol posar
     */
    public void setActivitat(Actividad activitat) {
        this.activitat = activitat;
    }

    /**
     * Setter de la valoració
     * 
     * @param valoracio valoracio que es vol posar
     */
    public void setValoracio(int valoracio) {
        if (valoracio >= 0 && valoracio <= 10) {
            this.valoracio = valoracio; 
        }else {
            System.out.println("La valoracio ha de ser un rang del 0 al 10");
        }
    }

    /**
     * Setter de la data
     * 
     * @param data data que es vol posar
     */
    public void setData(Data data) {
        this.data = data;
    }

    /**
     * Métode toString
     */
    @Override
    public String toString() {
        return "Inscripcio: \nUsuari: " + persona
                + "\nActivitat interesada: " + activitat
                + "\nValoració de l'activitat: " + valoracio
                + "\nData d'Inscripció: " + data;
    }

}
