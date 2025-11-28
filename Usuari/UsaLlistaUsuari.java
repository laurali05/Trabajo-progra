package Usuari;

public class UsaLlistaUsuari {
    public static void main(String[] args) {
        comprovarConstructor();
        ComprovarAfegir();
        comprovarCopia();

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
}
