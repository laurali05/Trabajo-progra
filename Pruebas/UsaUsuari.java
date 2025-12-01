package Pruebas;

import Usuari.*;

/**
 * Classe UsaUsuari 
 * Autor: Jordi Vivas
 */
public class UsaUsuari {
    public static void main(String[] args) {

        Usuari persona1 = new Usuari();
        System.err.println("1. Comprovar construcor i toString de la classe pare:\n" + persona1);

        UsuariEstudiant estudiant1 = new UsuariEstudiant("Estudiant", "Estudiant.Cognom", "GEB", 2020);
        System.err.println("\n1.2 Comprovar construcor i toString de la subclasse Estudiants:\n" + estudiant1);

        UsuariPTGAS PTGAS1 = new UsuariPTGAS("Treballador", "Treballador.Cognom", "Catalunya");
        System.err.println("\n1.3 Comprovar construcor i toString de la subclasse PTGAS:\n" + PTGAS1);

        UsuariPDI PDI1 = new UsuariPDI("Professor", "Professor.Cognom", "DEQ", "Catalunya");
        System.err.println("\n1.4 Comprovar construcor i toString de la subclasse PDI:\n" + PDI1);

        System.err.println(
                "\n2. Comprovar getters classe principal: \n" + persona1.getNom() + "\n" + persona1.getCorreu());
        System.err.println("\n2.2 Comprovar getters subclasse estudiants: \n" + estudiant1.getNom() + "\n"
                + estudiant1.getCorreu() + "\n" + estudiant1.getGrau() + "\n" + estudiant1.getAnyIni());
        System.err.println("\n2.3 Comprovar getters subclasse PTGAS: \n" + PTGAS1.getNom() + "\n" + PTGAS1.getCorreu()
                + "\n" + PTGAS1.getCampus());
        System.err.println("\n2.4 Comprovar getters subclasse PDI: \n" + PDI1.getNom() + "\n" + PDI1.getCorreu() + "\n"
                + PDI1.getDepartament() + "\n" + PDI1.getCampus());

        System.err.println("\n3. Metode copia\n" + persona1.copia());

        System.err.println("\n4. Comprovar setter, getNom original:\n" + persona1.copia().getNom());
        persona1.setNom("Pepe");
        System.err.println("getNom després del setter:\n" + persona1.copia().getNom());

        System.err.println("\nSon iguals? " + persona1.esIgual(PDI1));
        System.err.println("\nSon iguals? " + persona1.esIgual(persona1.copia()));

    }
}
