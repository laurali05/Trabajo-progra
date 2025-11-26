package Usuari;

public class UsuariPTGAS extends Usuari {

  private String campus;

  public UsuariPTGAS(String nom, String correu, String campus) {
    super(nom, correu);
    this.campus = campus;
  }
}