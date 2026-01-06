package Usuari;

/**
 * Clase: UsuariPTGAS
 * Autor: Jordi Vivas
 */
public class UsuariPTGAS extends Usuari {

  private String campus;

  /**
   * constructor subclasse amb parametres, del correu nomes guardem el nom i el
   * cognom ja que l'@ el crea el propi programa depenent del colectiu
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
   * metode copia de la subclasse
   * 
   * @return
   */
  public UsuariPTGAS copia() {
    UsuariPTGAS usu = new UsuariPTGAS(campus, campus, campus);
    return usu;
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