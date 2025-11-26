package Usuari;

public class UsuariPTGAS extends Usuari {

  private String campus;

  /**
   * constructor subclasse amb parametres
   * 
   * @param nom
   * @param correu
   * @param campus
   */
  public UsuariPTGAS(String nom, String correu, String campus) {
    super(nom, correu);
    this.campus = campus;
  }

  /**
   * getter del campus
   * 
   * @return
   */
  public String getCampus() {
    return campus;
  }

  /**
   * Metode toString de la subclasse
   */
  public String toString() {
    return super.toString() + "@urv.cat \nCampus: " + campus;
  }
}