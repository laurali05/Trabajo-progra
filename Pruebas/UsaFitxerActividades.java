package Pruebas;

import Actividades.*;
import Excepcions.*;
import Lector.*;

/**
 * Clase UsaFitxerActividades
 * Autor: Laura lope
 */
public class UsaFitxerActividades {

    public static void main(String[] args) {
        System.out.println(" ------ PRUEBA CARGAR FICHERO DE ACTIVIDADES ------");

        try {
            ListaActividades lista = FitxerActividades.carregarFitxerActividades();

            System.out.println("Fichero cargado correctamente");
            System.out.println();
            System.out.println("Actividades del fichero:");
            System.out.println(lista.toString());
        } catch (CarregarFitxersException e) {
            System.out.println(e.getMessage());
        }
    }
}
