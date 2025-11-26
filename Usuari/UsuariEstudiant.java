package Usuari;

public class UsuariEstudiant extends Usuari {
   private String grau;
   private int anyIni;

   /**
    * constructor subclasse amb parametres
    * 
    * @param nom
    * @param correu
    * @param grau
    * @param anyIni
    */
   public UsuariEstudiant(String nom, String correu, String grau, int anyIni) {
      super(nom, correu);
      this.grau = grau;
      this.anyIni = anyIni;
   }

   /**
    * getter del grau
    * 
    * @return
    */
   public String getGrau() {
      return grau;
   }

   /**
    * getter del any d'inici de la carrera
    * 
    * @return
    */
   public int getAnyIni() {
      return anyIni;
   }

   /**
    * Metode toString de la subclasse
    */
   public String toString() {
      return super.toString() + "@estudiants.urv.cat \ngrau: " + grau + "\nAny inici carrera: " + anyIni;
   }

}
