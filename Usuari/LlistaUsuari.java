package Usuari;

public class LlistaUsuari {
    private int nElem;
    private Usuari[] llista;

    public LlistaUsuari(int max) {
        llista = new Usuari[max];
        nElem = 0;
    }

    public void afegir(Usuari usu) {
        llista[nElem] = usu;
        nElem++;

    }

}
