package Inscripcions;

public class LlistaInscripcions {
    private static int limit;
    private int numElem;
    private Inscripcions[] llista;

    /**
     * Constructor 
     * 
     * @param mida
     */
    public void LlistaInscripicons(int mida){
        llista = new Inscripcions[mida];
        limit = mida;
        numElem = 0;
    }

    public LlistaInscripcions copia() {
        LlistaInscripcions novaLlista = new LlistaInscripcions();
        for (int i = 0; i < numElem; i++) {
            novaLlista.afegir(llista[i]);
        }
        return novaLlista;
    }

    public void afegir(Inscripcions ins) {
        if (numElem < limit) {
            llista[numElem] = ins.copia();
            numElem++;
        }
    }
}
