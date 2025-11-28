package Inscripcions;

import Actividades.Actividades;
import Data.Data;
import Usuari.Usuari;

public class Inscripcions{
    private Usuari persona;
    private Actividades activitat;
    private int valoracio;
    private Data data;

    /**
     * Constructor sense parametres
     */
    public Inscripcions (){
        this.persona=new Usuari();
        this.activitat=new Actividades();
        this.valoracio=7;
        this.data= new Data(4,10,2025);
    }
    
    /**
     * Constructor con parametros
     * @param persones personas que forman parte de la actividad
     * @param activitat actividad en cuestión
    */
    public Inscripcions (Usuari persona, Actividades activitat, int valoracio, Data data){
        this.persona=persona;
        this.activitat=activitat;
        this.valoracio=valoracio;
        this.data=data;
    }

    /**
     * Metodo copia
     * @return una copia de la inscripcion seleccionada
     */
    public Inscripcions copia(){
        Inscripcions novains = new Inscripcions(this.persona, this.activitat, this.valoracio, this.data);
        return novains;
    }

    @Override
    public String toString() {
        return "Inscripcio: \nUsuari: " + persona +
                "\nActivitat interesada: " + activitat +
                "\nValoració de l'activitat: " + valoracio +
                "\nData d'Inscripció: " + data;
    }

}