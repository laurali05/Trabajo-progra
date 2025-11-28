package Usuari;


public class LlistaUsuari {
    private int nElem;
    private Usuari[] llista;

    /**
     * Constructor de la Classe Llista
     * @param max
     */
    public LlistaUsuari(int max) {
        llista = new Usuari[max];
        nElem = 0;
    }

    /**
     * Metode per afegir Usuaris a la llista
     * @param usu
     */
    public void afegir(Usuari usu) {
        if(nElem<llista.length){
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
            aux = aux + "\nPersona "+(i+1)+": \n\t" + llista[i];
        }
        return aux;
    }

    /**
     * Metode que retorna un duplicat de la llista
     * @return
     */
    public LlistaUsuari copia() {
        LlistaUsuari aux = new LlistaUsuari(llista.length);
        for (int i = 0; i < nElem; i++) {
            aux.afegir(llista[i]); 
        }
        return aux;
    }

}
