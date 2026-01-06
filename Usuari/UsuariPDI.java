package Usuari;

public class UsuariPDI extends Usuari {
  private String departament;
  private String campus;

  /**
   * Constructor per parametres de la subclasse, del correu nomes guardem el nom i
   * el cognom ja que l'@ el crea el propi programa depenent del colectiu
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
   * metode copia de la subclasse
   * 
   * @return
   */
  public UsuariPDI copia() {
    UsuariPDI usu = new UsuariPDI(campus, campus, departament, campus);
    return usu;
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