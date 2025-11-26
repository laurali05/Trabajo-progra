package Usuari;

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
     * Contructor per parametres
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

}
