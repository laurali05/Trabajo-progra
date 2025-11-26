package Inscripcions;

public class Inscripcions{
    private int persones;
    private String activitat;
    
    public Inscripcions (int persones, String activitat){
        this.persones=persones;
        this.activitat=activitat;
    }

    public Inscripcions copia(){
        Inscripcions a = this;
        return a;
    }
}