package Pruebas;

import Excepcions.UsuariJaInscritException;
import Inscripcions.*;

/**
 * Classe UsaLlistaInscripcions Autor: Izan Flores
 */
public class UsaLlistaInscripcions {

    public static void main(String[] args) {
        System.out.println("------ PRUEBAS CLASE LLISTA INSCRIPCIONS ------\n");
        // --Les probes es faran amb els constructos sense parametres per validar la
        // seva funcionalitat.--

        System.out.println("Creació de la llista d'inscripcions:\n");
        LlistaInscripcio llista = new LlistaInscripcio(6);

        // Mostrem llista buida
        System.out.println(llista + "\n");

        // Afegim primera inscripció
        Inscripcio ins = new Inscripcio();

        try {

            llista.afegir(ins);
        } catch (UsuariJaInscritException e) {
            System.err.println("L'usuari " + e + " ja esta inscrit");
        }

        // Tornem a mostrar la llista pero ara amb la inscripció
        System.out.println("S'ha afegit una inscripció, tornem a mostrar la llista d'espera:\n");
        System.out.println(llista + "\n");

        LlistaInscripcio llistacop = llista.copia();


        // Mirem si la copia es efectiva
        System.out.println("---Proba métode cópia---\n");
        System.out.println("Tornem a mostrar la llista completa\n");
        // Tornem a mostrar la llista completa
        System.out.println(llista + "\n");

        System.out.println("Comprovem si la cópia realitzada es efectiva\n");
        System.out.println(llistacop);
    }
}
