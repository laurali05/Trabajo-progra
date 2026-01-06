package Usuari;

/**
 * Clase: UsuariEstudiant
 * Autor: Jordi Vivas
 */
public class UsuariEstudiant extends Usuari {
   private String grau;
   private int anyIni;

   /**
    * constructor subclasse amb parametres, del correu nomes guardem el nom i el
    * cognom ja que l'@ el crea el propi programa depenent del colectiu
    * 
    * @param nom
    * @param correu
    * @param grau
    * @param anyIni
    */
   public UsuariEstudiant(String nom, String correu, String grau, int anyIni) {
      super(nom, correu);
      this.grau = grau;
      if(anyIni>1950 && anyIni<=2025)
      this.anyIni = anyIni;
      else this.anyIni=2000;
   }

   /**
    * metode copia de la subclasse
    * 
    * @return
    */
   public UsuariEstudiant copia() {
      UsuariEstudiant usu = new UsuariEstudiant(grau, grau, grau, anyIni);
      return usu;
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
