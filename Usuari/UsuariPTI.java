package Usuari;

public class UsuariPTI extends Usuari {
  private String departament;

  public UsuariPTI(String nom, String correu, String departament) {
    super(nom, correu);
    this.departament = departament;
  }
}