package Usuari;

import Excepcions.UsuariNoTrobatException;

public class UsaLlistaUsuari {
    public static void main(String[] args) {
        comprovarConstructor();
        ComprovarAfegir();
        comprovarCopia();
        comprobarEsIgual();
        comprobarExcepcio();

    }

    private static void comprobarEsIgual() {
        LlistaUsuari dades = new LlistaUsuari(100);

        Usuari persona1 = new Usuari();

        UsuariEstudiant estudiant1 = new UsuariEstudiant("Estudiant", "Estudiant.Cognom", "GEB", 2020);

        UsuariPTGAS PTGAS1 = new UsuariPTGAS("Treballador", "Treballador.Cognom", "Catalunya");

        UsuariPDI PDI1 = new UsuariPDI("Professor", "Professor.Cognom", "DEQ", "Catalunya");

        dades.afegir(persona1);
        dades.afegir(estudiant1);
        dades.afegir(PTGAS1);
        dades.afegir(PDI1);

        LlistaUsuari dades2 = dades.copia();
        System.err.println("\n4.1 Comprobar metode de comparació de dos insancies iguals");
        System.err.println("\nSon iguals? " + dades.esIgual(dades2));

        Usuari persona2 = new Usuari("pepe", "pepe.rivas");
        dades.afegir(persona2);
        System.err.println("\n4.2 Comprobar metode de comparació de dos insancies diferents");
        System.err.println("\nSon iguals? " + dades.esIgual(dades2));

    }

    private static void comprovarCopia() {

        LlistaUsuari dades = new LlistaUsuari(100);

        Usuari persona1 = new Usuari();

        UsuariEstudiant estudiant1 = new UsuariEstudiant("Estudiant", "Estudiant.Cognom", "GEB", 2020);

        UsuariPTGAS PTGAS1 = new UsuariPTGAS("Treballador", "Treballador.Cognom", "Catalunya");

        UsuariPDI PDI1 = new UsuariPDI("Professor", "Professor.Cognom", "DEQ", "Catalunya");

        dades.afegir(persona1);
        dades.afegir(estudiant1);
        dades.afegir(PTGAS1);
        dades.afegir(PDI1);
        LlistaUsuari duplicat = dades.copia();

        System.err.println("\n3. Comprobar Metode copia:\n");
        System.err.println(duplicat);
    }

    private static void comprovarConstructor() {
        LlistaUsuari dades = new LlistaUsuari(100);
        System.err.println("1. Comprobar contructor:\n");
        System.err.println(dades);
    }

    private static void ComprovarAfegir() {

        LlistaUsuari dades = new LlistaUsuari(100);

        Usuari persona1 = new Usuari();

        UsuariEstudiant estudiant1 = new UsuariEstudiant("Estudiant", "Estudiant.Cognom", "GEB", 2020);

        UsuariPTGAS PTGAS1 = new UsuariPTGAS("Treballador", "Treballador.Cognom", "Catalunya");

        UsuariPDI PDI1 = new UsuariPDI("Professor", "Professor.Cognom", "DEQ", "Catalunya");

        dades.afegir(persona1);
        dades.afegir(estudiant1);
        dades.afegir(PTGAS1);
        dades.afegir(PDI1);

        System.err.println("\n2. Comprobar els metodes afegir i toString:\n");
        System.err.println(dades);
    }

    private static void comprobarExcepcio() {
        LlistaUsuari dades = new LlistaUsuari(100);

        Usuari persona1 = new Usuari();

        UsuariEstudiant estudiant1 = new UsuariEstudiant("Estudiant", "Estudiant.Cognom", "GEB", 2020);

        UsuariPTGAS PTGAS1 = new UsuariPTGAS("Treballador", "Treballador.Cognom", "Catalunya");

        UsuariPDI PDI1 = new UsuariPDI("Professor", "Professor.Cognom", "DEQ", "Catalunya");

        dades.afegir(persona1);
        dades.afegir(estudiant1);
        dades.afegir(PTGAS1);
        dades.afegir(PDI1);

        try {
            System.err.println("\n5. Buscar Usuari i comprovar la classe excepcio");
            Usuari usuari = dades.buscarUsuari("Professor");
            System.err.println("\nL'Usuari és:\n" + usuari);

            Usuari usuari2 = dades.buscarUsuari("Nom Inexistent");
            System.err.println("\nL'Usuari és:\n" + usuari2);

        } catch (UsuariNoTrobatException e) {

            System.err.println("\nError: " + e);

        }

    }
}
