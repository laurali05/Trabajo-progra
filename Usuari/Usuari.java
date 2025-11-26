package Usuari;
public class Usuari {

    private String nom;
    private String correu;

    /**
     * Constructor sense parametres
     */
    public Usuari(){
        nom ="Nom";
        correu ="Nom.Cognom";
    }
    
    
    /**
     * Contructor per parametres
     * @param nom
     * @param correu
     */
    public Usuari(String nom,String correu) {
        this.nom=nom;
        this.correu=correu;
        
    }

    public Usuari copia(){
        Usuari usu= new Usuari(nom,correu);
         return usu;
    }
}
