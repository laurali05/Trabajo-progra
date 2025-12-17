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
        LlistaInscripcions llista = new LlistaInscripcions(6);

        // Mostrem llista buida
        System.out.println(llista + "\n");

        // Afegim primera inscripció
        Inscripcions ins = new Inscripcions();
        try {
            llista.afegir(ins);
        } catch (UsuariJaInscritException e) {
            System.err.println("L'usuari " + e + " ja esta inscrit");
        }

        // Tornem a mostrar la llista pero ara amb la inscripció
        System.out.println("S'ha afegit una inscripció, tornem a mostrar la llista d'espera:\n");
        System.out.println(llista + "\n");

        // Afegim fins al limit (6) i realitzem una copia per veure si es efectiu el
        // métode
        System.out.println("S'han afegit les inscripcions necesaries per omplir la llista, la tornem a mostrar:\n");

        try {
            llista.afegir(ins);
            llista.afegir(ins);
            llista.afegir(ins);
            llista.afegir(ins);
            llista.afegir(ins);
        } catch (UsuariJaInscritException e) {
            System.err.println("L'usuari " + e + " ja esta inscrit");
        }
        
        LlistaInscripcions llistacop = llista.copia();

        // Tornem a mostrar la llista completa
        System.out.println(llista + "\n");

        // Intentarem afegir més del máxim, no s'hauria d'actualitzar.
        System.out.println(
                "S'intenta afegir una més per veure si s'afegeix, es torna a mostrar per veure si s'afegeix o no i es realitza una cópia:\n");
        try {
            llista.afegir(ins);
        } catch (UsuariJaInscritException e) {
            System.err.println("L'usuari" + e + "ja esta inscrit");
        }

        // Tornem a mostrar
        System.out.println(llista + "\n");

        // Mirem si la copia es efectiva
        System.out.println("---Proba métode cópia---\n");

        System.out.println("Comprovem si la cópia realitzada es efectiva\n");
        System.out.println(llistacop);
    }
}
