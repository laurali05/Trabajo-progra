package Usuari;

public class UsuariPDI extends Usuari {
  private String departament;
  private String campus;

  /**
   * Constructor per parametres de la subclasse
   * 
   * @param nom
   * @param correu
   * @param departament
   * @param campus
   */
  public UsuariPDI(String nom, String correu, String departament, String campus) {
    super(nom, correu);
    this.departament = departament;
    this.campus = campus;
  }

  /**
   * getter del departament
   * 
   * @return
   */
  public String getDepartament() {
    return departament;
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
    return super.toString() + "@urv.cat \nDepartament: " + departament + "\nCampus: " + campus;
  }
}