package Pruebas;

import Actividades.*;
import Data.Data;

import Excepcions.NomActivitatDuplicatException;

/**
 * Clase: UsaLlistaActividades
 * Autor: Laura Lope
 */
public class UsaLlistaActividades {
    public static void main(String[] args) throws NomActivitatDuplicatException {
        System.out.println("------ PRUEBAS CLASE LLISTA ACTIVIDADES ------");
        System.out.println();

        compruebaConstructorToStringLlistaActividades();
        compruebaAfegirLlistaActividades();
        compruebaCopiaLlistaActividades();
        compruebaEqualsLlistaActividades();
        compruebaExcepcionNomDuplicat();

    }

    private static void compruebaConstructorToStringLlistaActividades() throws NomActivitatDuplicatException {
        System.out.println("------ PRUEBA CONSTRUCTOR Y TOSTRING ------");
        LlistaActividades lista = new LlistaActividades(10);
        System.out.println("Lista vacía: ");
        System.out.println(lista);
        System.out.println();
    }

    private static void compruebaAfegirLlistaActividades() throws NomActivitatDuplicatException {
        System.out.println("------ PRUEBA AFEGIR ------");
        LlistaActividades lista = new LlistaActividades(5);

        ActividadesPeriodicas a = new ActividadesPeriodicas("Curso", new Data(14, 10, 2925), new Data(31, 10, 2025),
                 "Estudiants" , "Viernes", new int[] { 18, 00 }, new Data(5, 11, 2025), 4, 50, 0,
                "Sescelades", "Tarragona");

        ActividadesDia b = new ActividadesDia("Premios", new Data(29, 9, 2925), new Data(5, 10, 2025),
                 "PDI, PGTAS, Estudiants" , new Data(6, 10, 2025), new int[] { 18, 00 }, 20, 0,
                "Tarragona");

        ActividadesOnline c = new ActividadesOnline("Entrevista", new Data(15, 11, 2925), new Data(20, 11, 2025),
                 "PDI, Estudiants" , new Data(22, 11, 2025), 7,
                "https://www.universitat.cat/actividades/java2025");

        lista.afegir(a);
        lista.afegir(b);
        lista.afegir(c);

        System.out.println("Lista tras añadir 3 actividades:");
        System.out.println(lista);
        System.out.println();
    }

    private static void compruebaCopiaLlistaActividades() throws NomActivitatDuplicatException {
        System.out.println("------ PRUEBA COPIA ------");
        LlistaActividades lista = new LlistaActividades(5);

        Actividades a = new Actividades("Curso de especialización", new Data(4, 9, 2025), new Data(31, 10, 2025),
                 "PTGAS" );
        lista.afegir(a);

        LlistaActividades copia = lista.copia();

        System.out.println("Copia de la lista:");
        System.out.println(copia);
        System.out.println();
    }

    private static void compruebaEqualsLlistaActividades() throws NomActivitatDuplicatException {
        System.out.println("------ PRUEBA IGUALS ------");
        LlistaActividades lista1 = new LlistaActividades(5);
        LlistaActividades lista2 = new LlistaActividades(5);

        Actividades a = new Actividades("Curso de especialización", new Data(4, 10, 2025), new Data(31, 10, 2025),
               "PDI, Estudiants" );

        ActividadesDia b = new ActividadesDia("Premios", new Data(29, 9, 2925), new Data(5, 10, 2025),
                "Estudiants" , new Data(6, 10, 2025), new int[] { 18, 00 }, 20, 0, "Tarragona");

        lista1.afegir(a);
        lista2.afegir(a);

        System.out.println("¿Son iguales (mismo elemento)? " + lista1.iguals(lista2));

        lista2.afegir(b);
        System.out.println("¿Son iguales (diferente número de elementos)? " + lista1.iguals(lista2));
        System.out.println();
    }

    private static void compruebaExcepcionNomDuplicat() {
        System.out.println("------ PRUEBA EXCEPCIÓN -------");

        LlistaActividades lista = new LlistaActividades(200);

        Actividades a = new Actividades("Charla", new Data(15, 9, 2025), new Data(30, 9, 2025),
                "Estudiants" );

        Actividades b = new Actividades("Charla", new Data(20, 10, 2025), new Data(25, 10, 2025),
                 "PDI" );

        try {
            lista.afegir(a);
            System.out.println("Primera actividad añadidad correctamente: " + a.getNom());
            System.out.println();
            lista.afegir(b);
            System.out.println("No se lanzó la excepción esperada.");
        } catch (NomActivitatDuplicatException e) {
            System.out.println(e.getMessage());
        }
    }
}
