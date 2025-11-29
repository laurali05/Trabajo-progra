package Usuari;

import Excepcions.UsuariNoTrobatException;

public class LlistaUsuari {
    private int nElem;
    private Usuari[] llista;

    /**
     * Constructor de la Classe Llista
     * 
     * @param max
     */
    public LlistaUsuari(int max) {
        llista = new Usuari[max];
        nElem = 0;
    }

    /**
     * Metode per afegir Usuaris a la llista
     * 
     * @param usu
     */
    public void afegir(Usuari usu) {
        if (nElem < llista.length) {
            llista[nElem] = usu;
            nElem++;
        }

    }

    /**
     * Metode toString per representar la llista
     */
    public String toString() {
        String aux = "LlistaUsuari -> numero d'elements " + nElem;
        for (int i = 0; i < nElem; i++) {
            aux = aux + "\nPersona " + (i + 1) + ": \n\t" + llista[i];
        }
        return aux;
    }

    /**
     * Metode que retorna un duplicat de la llista
     * 
     * @return
     */
    public LlistaUsuari copia() {
        LlistaUsuari aux = new LlistaUsuari(llista.length);
        for (int i = 0; i < nElem; i++) {
            aux.afegir(llista[i]);
        }
        return aux;
    }

    /**
     * Metode que compara si dues llistes son iguals
     * 
     * @param aux
     * @return
     */
    public boolean esIgual(LlistaUsuari aux) {
        if (this.nElem != aux.nElem)
            return false;
        for (int i = 0; i < nElem; i++) {
            if (!this.llista[i].esIgual(aux.llista[i]))
                return false;
        }
        return true;
    }

    public Usuari buscarUsuari(String nom) throws UsuariNoTrobatException {

        int i = 0;
        boolean trobat = false;

        while (i < nElem && !trobat) {
            if (llista[i].getNom().equalsIgnoreCase(nom)) {
                trobat = true;
            } else {
                i++;
            }
        }

        if (!trobat) {
            throw new UsuariNoTrobatException();

        }
        return llista[i];
    }

}
