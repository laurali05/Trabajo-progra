package Inscripcions;

import Actividades.LlistaActividades;
import Excepcions.NomActivitatDuplicatException;
import Excepcions.UsuariJaInscritException;
import Usuari.Usuari;

public class LlistaInscripcions {
    private static int limit;
    private int numElem;
    private Inscripcions[] llista;

    /**
     * Constructor de la llista d'inscripcions
     * 
     * @param mida tamany de la llista
     */
    public LlistaInscripcions(int mida) {
        llista = new Inscripcions[mida];
        limit = mida;
        numElem = 0;
    }

    /**
     * Métode cópia de la llista
     * 
     * @return llista copiada
     */
    public LlistaInscripcions copia() {
        LlistaInscripcions novaLlista = new LlistaInscripcions(numElem);
        for (int i = 0; i < numElem; i++) {
            try {
                novaLlista.afegir(llista[i]);
            } catch (UsuariJaInscritException e) {
                System.err.println("L'usuari" + e + "ja esta inscrit");
            }
        }
        return novaLlista;
    }

    /**
     * Métode per afegir noves inscripcions a la llista
     * 
     * @param ins inscripcio a afegir
     */
    public void afegir(Inscripcions ins) throws UsuariJaInscritException {
        int i = 0;
        boolean trobat = false;
        while (!trobat && i < numElem) {
            if (llista[i].getUsuari().esIgual(ins.getUsuari()) && llista[i].getActivitat().iguals(ins.getActivitat())) {
                trobat = true;
            } else {
                i++;
            }
        }

        if (!trobat && numElem < limit) {
            llista[numElem] = ins.copia();
            numElem++;
        } else if (trobat) {
            throw new UsuariJaInscritException(ins.getUsuari().getNom());
        }

    }

    // Métode toString
    @Override
    public String toString() {
        String text = "Llista Inscripcions -> Inscripcions a la llista:" + numElem + "\n";
        for (int i = 0; i < numElem; i++) {
            text = text + "\nInscripcio " + (i + 1) + ": \n" + llista[i];
        }
        return text;
    }

    public LlistaActividades ActivitatsUsuari(Usuari nom) {
        LlistaActividades aux = new LlistaActividades(limit);
        try {
            for (int i = 0; i < numElem; i++) {

                if (llista[i].getUsuari().esIgual(nom)) {

                    aux.afegir(llista[i].getActivitat());
                }
            }
        } catch (NomActivitatDuplicatException e) {
            System.out.println(e + " Duplicat");
        }
        return aux;
    }
}
