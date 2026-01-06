package Usuari;

/**
 * Clase: Usuari
 * Autor: Jordi Vivas
 */
public class Usuari {

    private String nom;
    private String correu;

    /**
     * Constructor sense parametres
     */
    public Usuari() {
        nom = "Nom";
        correu = "Nom.Cognom";
    }

    /**
     * Contructor per parametres, del correu nomes guardem el nom i el cognom ja que l'@ el crea el propi programa depenent del colectiu
     * 
     * @param nom
     * @param correu
     */
    public Usuari(String nom, String correu) {
        this.nom = nom;
        this.correu = correu;

    }

    /**
     * Metode Copia de la classe Usuari
     * 
     * @return
     */
    public Usuari copia() {
        Usuari usu = new Usuari(nom, correu);
        return usu;
    }

    /**
     * Metode toString
     */
    public String toString() {
        return "Nom: " + nom + " \nCorreu: " + correu;
    }

    /**
     * getter del nom
     * 
     * @return
     */
    public String getNom() {
        return nom;
    }

    /**
     * getter del correu
     * 
     * @return
     */
    public String getCorreu() {
        return correu;
    }

    /**
     * Setter del nom
     * 
     * @param nom
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Setter del correu
     * 
     * @param correu
     */
    public void setCorreu(String correu) {
        this.correu = correu;
    }

    /**
     * Metode que comproba si dos instancies son iguals
     * @param usu
     * @return
     */
    public boolean esIgual(Usuari usu){
        if(usu==null){
            return false;
        }
        else if(this.getNom().equals(usu.getNom()) && this.getCorreu().equals(usu.getCorreu())){

            return true;
        }
        else return false;
    }

}
