package Usuari;



public class UsuariEstudiant extends Usuari {
   private String grau;
   private int anyIni;

   public UsuariEstudiant(String nom, String correu,String grau,int anyIni){
  super(nom,correu);
  this.grau=grau;
  this.anyIni=anyIni;
   }
    
}
