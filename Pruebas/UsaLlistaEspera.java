package Pruebas;

import Inscripcions.*;

/**
 * Classe UsaLlistaEspera
 * Autor: Izan Flores
 */
public class UsaLlistaEspera {
   public static void main(String[] args) {
      System.out.println("------ PRUEBAS CLASE LLISTA ESPERA ------\n");

      // --Les probes es faran amb els constructos sense parametres per validar la
      // seva funcionalitat.--

      System.out.println("Creació de la llista d'espera:\n");
      LlistaEspera llista = new LlistaEspera();

      // Mostrem llista buida

      System.out.println(llista + "\n");

      // Afegim primera inscripció

      Inscripcions ins = new Inscripcions();
      llista.afegir(ins);

      // Tornem a mostrar la llista pero ara amb la inscripció

      System.out.println("S'ha afegit una inscripció, tornem a mostrar la llista d'espera:\n");
      System.out.println(llista + "\n");

      // Afegim fins al limit (10) i realitzem una copia per veure si es efectiu el
      // métode

      llista.afegir(ins);
      llista.afegir(ins);
      llista.afegir(ins);
      llista.afegir(ins);
      llista.afegir(ins);
      llista.afegir(ins);
      llista.afegir(ins);
      llista.afegir(ins);
      llista.afegir(ins);
      LlistaEspera llistacop = llista.copia();

      // Tornem a mostrar la llista completa

      System.out.println(llista + "\n");

      // Intentarem afegir més del máxim, no s'hauria d'actualitzar.

      llista.afegir(ins);

      // Tornem a mostrar

      System.out.println(llista + "\n");

      // Comprovem si el métode cópia es efectiu

      System.out.println("---Proba métode cópia---\n");

      System.out.println("Comprovem si la cópia realitzada es efectiva\n");
      System.out.println(llistacop);

   }
}
