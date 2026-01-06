package Inscripcions;

import Actividades.Actividad;
import Data.Data;
import Usuari.Usuari;

public class Inscripcions{
    private Usuari persona;
    private Actividad activitat;
    private int valoracio;
    private Data data;

    /**
     * Constructor sense parametres
     */
    public Inscripcions (){
        this.persona=new Usuari();
        this.activitat=new Actividad();
        this.valoracio=0;
        this.data= new Data(4,10,2025);
    }
    
    /**
     * Constructor con parametros
     * @param persones personas que forman parte de la actividad
     * @param activitat actividad en cuestión
    */
    public Inscripcions (Usuari persona, Actividad activitat, int valoracio, Data data){
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

    //Getters y setters

    public Usuari getUsuari(){
        return persona;
    }

    public Actividad getActivitat(){
        return activitat;
    }

    public int getValoracio(){
        return valoracio;
    }

    public Data getData(){
        return data;
    }

    public void setUsuari(Usuari persona){
        this.persona = persona;
    }

    public void setActivitat(Actividad activitat){
        this.activitat = activitat;
    }

    public void setValoracio(int valoracio){
        this.valoracio = valoracio;
    }

    public void setData(Data data){
        this.data = data;
    }

    //toString
    @Override
    public String toString() {
        return "Inscripcio: \nUsuari: " + persona +
                "\nActivitat interesada: " + activitat +
                "\nValoració de l'activitat: " + valoracio +
                "\nData d'Inscripció: " + data;
    }

}