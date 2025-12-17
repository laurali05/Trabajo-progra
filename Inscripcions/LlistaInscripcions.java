package Inscripcions;

import Actividades.LlistaActividades;
import Lector.FitxerUsuari;
import Usuari.LlistaUsuari;
import Data.*;

public class LlistaInscripcions{
    private static int limit;
    private int numElem;
    private Inscripcions[] llista;

    /**
     * Constructor de la llista d'inscripcions
     * @param mida tamany de la llista
     */
    public LlistaInscripcions(int mida){
        llista= new Inscripcions[mida];
        limit=mida;
        numElem=0;
    }

    /**
     * Métode cópia de la llista
     * @return llista copiada
     */
    public LlistaInscripcions copia() {
        LlistaInscripcions novaLlista = new LlistaInscripcions(numElem);
        for (int i = 0; i < numElem; i++) {
            novaLlista.afegir(llista[i]);
        }
        return novaLlista;
    }

    /**
     * Métode per afegir noves inscripcions a la llista
     * @param ins inscripcio a afegir
     */
    public void afegir(Inscripcions ins) {
        if (numElem < limit) {
            llista[numElem] = ins.copia();
            numElem++;
        }
    }

    public LlistaInscripcions crearLlista(LlistaUsuari usuaris, LlistaActividades activitats){
        LlistaInscripcions novaLlista = null;
        Data avui = new Data();
        for (int i=0; i<numElem;i++){
            Inscripcions ins = new Inscripcions(usuaris[i],activitats[i],null,avui);
            novaLlista.afegir(ins);
        }
        return novaLlista;
    }

    //Métode toString
    @Override
    public String toString() {
        String text = "Llista Inscripcions -> Inscripcions a la llista:" + numElem + "\n";
        for (int i = 0; i < numElem; i++) {
            text = text + "\nInscripcio "+(i+1)+": \n" + llista[i];
        }
        return text;
    }
}    

