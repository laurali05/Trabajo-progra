package Inscripcions;

import Data.Data;

public class Inscripcions{
    private int persones;
    private String activitat;
    private int valoracio;
    private Data data;

    /**
     * Constructor sense parametres
     */
    public Inscripcions (){
        this.persones=5;
        this.activitat="Robotica";
        this.valoracio=7;
        this.data= new Data(4,5,2025);
    }
    
    /**
     * Constructor con parametros
     * @param persones personas que forman parte de la actividad
     * @param activitat actividad en cuestión
    */
    public Inscripcions (int persones, String activitat, int valoracio, Data data){
        this.persones=persones;
        this.activitat=activitat;
        this.valoracio=valoracio;
        this.data=data;
    }

    /**
     * Metodo copia
     * @return una copia de la inscripcion seleccionada
     */
    public Inscripcions copia(){
        Inscripcions novains = new Inscripcions(this.persones, this.activitat, this.valoracio, this.data);
        return novains;
    }

}