package Usuari;

import Excepcions.UsuariNoTrobatException;

/**
 * Clase: LlistaUsuari
 * Autor: Jordi Vivas
 */
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

    /**
     * Metode que retorna un usuari per un nom concret
     * 
     * @param nom
     * @return
     * @throws UsuariNoTrobatException
     */
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
            throw new UsuariNoTrobatException(nom);

        }
        return llista[i];
    }

    /**
     * Metode que retorna una llista especifica de una subclasse
     * 
     * @param opcio
     * @return
     */
    public LlistaUsuari llistaTipus(int opcio) {
        LlistaUsuari llistaEsp = new LlistaUsuari(nElem);
        if (opcio == 1) {
            for (int i = 0; i < nElem; i++) {
                if (llista[i] instanceof UsuariEstudiant) {
                    llistaEsp.afegir(llista[i]);
                }
            }

        }
        if (opcio == 2) {
            for (int i = 0; i < nElem; i++) {
                if (llista[i] instanceof UsuariPDI) {
                    llistaEsp.afegir(llista[i]);
                }
            }

        }
        if (opcio == 3) {
            for (int i = 0; i < nElem; i++) {
                if (llista[i] instanceof UsuariPTGAS) {
                    llistaEsp.afegir(llista[i]);
                }
            }

        }
        return llistaEsp;

    }

    /**
     * Metode que retorna el numero d'elements de la llista
     * 
     * @return
     */
    public int getNumElems() {
        return nElem;
    }

    /**
     * Metode que retorna un usuari a partir de la seva posició
     * 
     * @param i
     * @return
     */
    public Usuari buscarUsuariPerPos(int i) {
        return llista[i];
    }

    /**
     * Lista de nombres de los usuarios
     * Autor Laura Lope
     * 
     * @return
     */
    public String nomUsuarios() {
        String nomUsuarios = " ";

        for (int i = 0; i < this.getNumElems(); i++) {
            nomUsuarios += "\n\t - " + llista[i].getNom();
        }

        return nomUsuarios;
    }

}
