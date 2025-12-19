package Inscripcions;

import Actividades.Actividades;

public class LlistaEspera {
    private final int maxPersones = 10;
    private int numElem;
    private Inscripcions[] llista;

    /**
     * Constructor de la llista d'espera
     */
    public LlistaEspera() {
        llista = new Inscripcions[maxPersones];
        numElem = 0;
    }

    /**
     * Métode cópia de la llista
     * 
     * @return llista copiada
     */
    public LlistaEspera copia() {
        LlistaEspera novaLlista = new LlistaEspera();
        for (int i = 0; i < numElem; i++) {
            novaLlista.afegir(llista[i]);
        }
        return novaLlista;
    }

    /**
     * Métode per afegir noves inscripcions a la llista
     * 
     * @param ins inscripcio a afegir
     */
    public void afegir(Inscripcions ins) {
        if (numElem < maxPersones) {
            llista[numElem] = ins.copia();
            numElem++;
        }
    }

    // Métode toString
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
     * @return
     */
    public Inscripcions buscarPrimerEspera(Actividades act) {
        boolean trobat = false;

        Inscripcions insPrimer = null;
        int i = 0;
        while (!trobat && i < numElem) {
            if (llista[i].getActivitat().equals(act)) {
                insPrimer = llista[i];
                for (int j = 0; j < numElem; j++) {
                    llista[j] = llista[j + 1];
                    numElem--;
                }
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

}