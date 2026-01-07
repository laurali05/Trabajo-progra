package Pruebas;

import Usuari.LlistaUsuari;
import Excepcions.*;
import Lector.*;

/**
 * Classe UsaFitxerUsuari
 * Autor: Jordi Vivas
 */

public class UsaFitxerUsuari {

    public static void main(String[] args) {

        try {
            LlistaUsuari dades = new LlistaUsuari(100);
            dades = FitxerUsuari.carregarFitxeUsuari();
            System.out.println(dades);

        } catch (CarregarFitxersException e) {

            System.err.println("\nError: " + e);

        }

    }
}
