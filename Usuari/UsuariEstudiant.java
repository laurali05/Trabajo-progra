package Usuari;



public class UsuariEstudiant extends Usuari {
   private String grau;

   public UsuariEstudiant(String nom, String correu,String grau){
  super(nom,correu);
  this.grau=grau;
   }
    
}
