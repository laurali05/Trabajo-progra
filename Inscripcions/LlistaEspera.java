package Inscripcions;

import Actividades.Actividad;
import Excepcions.UsuariJaInscritException;
import Usuari.LlistaUsuari;

/**
 * Clase: LlistaEspera
 * Autor: Izan Flores
 */
public class LlistaEspera {
    private final int maxPersones = 10;
    private int numElem;
    private Inscripcio[] llista;

    /**
     * Constructor de la llista d'espera
     */
    public LlistaEspera() {
        llista = new Inscripcio[maxPersones];
        numElem = 0;
    }

    /**
     * Devuelve el número de elementos
     * Autor Laura Lope
     * 
     * @return
     */
    public int getNumElems(){
        return numElem;
    }

    /**
     * Métode cópia de la llista
     * 
     * @return cópia de la llista
     */
    public LlistaEspera copia() {
        LlistaEspera novaLlista = new LlistaEspera();
        for (int i = 0; i < numElem; i++) {
            try{
            novaLlista.afegir(llista[i]);
            } catch (UsuariJaInscritException e){
                System.err.println("L'usuari "+e+" ja estava inscrit.");
            }
        }
        return novaLlista;
    }

    /**
     * Métode per afegir noves inscripcions a la llista
     * 
     * @param ins 
     */
    public void afegir(Inscripcio ins) throws UsuariJaInscritException{
        int i=0;
        boolean trobat = false;
        while(!trobat && i<numElem){
            if(llista[i].getUsuari().esIgual(ins.getUsuari())){
                trobat=true;
                throw new UsuariJaInscritException(ins.getUsuari().getNom());
            } else i++;
        }
        if (numElem < maxPersones) {
            llista[numElem] = ins.copia();
            numElem++;
        }
    }

    /**
     * Métode toString
     */
    @Override
    public String toString() {
        String text = "Llista Espera -> Inscripcions a la llista:" + numElem + "\n";
        for (int i = 0; i < numElem; i++) {
            text = text + "\nInscripcio " + (i + 1) + ": \n" + llista[i];
        }
        return text;
    }

    /**
     * Metode que retorna la primera inscripcio d'una activitat i la elimina de la
     * llista d'espera necessari per l'opció 12
     * (Jordi)
     * 
     * @param act
     * @return primera inscripcio de la llista espera
     */
    public Inscripcio buscarPrimerEspera(Actividad act) {
        boolean trobat = false;

        Inscripcio insPrimer = null;
        int i = 0;
        while (!trobat && i < numElem) {
            if (llista[i].getActivitat().equals(act)) {
                insPrimer = llista[i];
                for (int j = i; j < numElem; j++) {
                    llista[j] = llista[j + 1];
                }
                numElem--;
                llista[numElem]=null;
                trobat = true;
            } else
                i++;
        }
        if (!trobat) {
            System.out.println("No hi ha ningú en espera");
            return null;
        } else {
            System.out.println("L'usuari en espera " + insPrimer.getUsuari().getNom() + " Substituirà l'anterior");
            return insPrimer;
        }
    }

    /**
     * Métode que retorna una llista d'usuaris apuntats a una activitat
     * 
     * @param act 
     * @return llista d'usuaris apuntats a l'activitat
     */
    public LlistaUsuari usuarisApuntats(Actividad act){
        LlistaUsuari aux = new LlistaUsuari(100);
        for (int i=0;i<numElem;i++){
            if (llista[i].getActivitat().iguals(act)){
                aux.afegir(llista[i].getUsuari());
            }
        }
        return aux;
    }

}