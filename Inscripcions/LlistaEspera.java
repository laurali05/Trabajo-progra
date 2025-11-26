package Inscripcions;

public class LlistaEspera{
    private final int maxPersones=10;
    private int numElem;
    private Inscripcions[] llista;

    public LlistaEspera (){
        llista= new Inscripcions[maxPersones];
        numElem=0;
    }

    public LlistaEspera copia() {
        LlistaEspera novaLlista = new LlistaEspera();
        for (int i = 0; i < numElem; i++) {
            novaLlista.afegir(llista[i]);
        }
        return novaLlista;
    }

    public void afegir(Inscripcions ins) {
        if (numElem < maxPersones) {
            llista[numElem] = ins.copia();
            numElem++;
        }
    }

}