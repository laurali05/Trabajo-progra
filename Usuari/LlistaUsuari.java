package Usuari;

public class LlistaUsuari {
    private int nElem;
    private Usuari[] llista;

    public LlistaUsuari(int max) {
        llista = new Usuari[max];
        nElem = 0;
    }

    public void afegir(Usuari usu) {
        if(nElem<llista.length){
        llista[nElem] = usu;
        nElem++;
        }

    }

     public String toString() {
        String aux = "LlistaUsuari -> numero d'elements " + nElem;
        for (int i = 0; i < nElem; i++) {
            aux = aux + "\nPersona "+(i+1)+": \n\t" + llista[i];
        }
        return aux;
    }

}
