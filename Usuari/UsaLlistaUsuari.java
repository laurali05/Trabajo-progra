package Usuari;

public class UsaLlistaUsuari {
    public static void main(String[] args) {

        LlistaUsuari dades = new LlistaUsuari(100);

        Usuari persona1 = new Usuari();

        UsuariEstudiant estudiant1 = new UsuariEstudiant("Estudiant", "Estudiant.Cognom", "GEB", 2020);

        UsuariPTGAS PTGAS1 = new UsuariPTGAS("Treballador", "Treballador.Cognom", "Catalunya");

        UsuariPDI PDI1 = new UsuariPDI("Professor", "Professor.Cognom", "DEQ", "Catalunya");

        dades.afegir(persona1);
        dades.afegir(estudiant1);
        dades.afegir(PTGAS1);
        dades.afegir(PDI1);

        System.err.println(dades);

    }
}
