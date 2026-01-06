package Pruebas;

import Actividades.Actividad;
import Data.Data;
import Excepcions.FechaIncorrectaExcepcion;
import Inscripcions.Inscripcio;
import Usuari.Usuari;

/**
 * Classe UsaInscripcions
 * Autor: Izan Flores
 */
public class UsaInscripcions {
    public static void main(String[] args) throws FechaIncorrectaExcepcion {
        System.out.println("------ PRUEBAS CLASE INSCRIPCIONS ------\n");

        // Generació de una inscripció

        System.out.println("\nGeneració de inscripció:\n");
        Inscripcio insini = new Inscripcio();
        System.out.println(insini.toString() + "\n");

        // Generació d'una inscripció amb parametres

        System.out.println("Generació de inscripció amb parametres:\n");
        Data datanova = new Data(6, 9, 2025);
        Usuari persona = new Usuari("Robert", "robert@gmail.com");
        Actividad activitat = new Actividad("Dia", "Robotica", new Data(3, 9, 2025), new Data(3, 11, 2025),
                ("PDI, Estudiants"));
        Inscripcio insparam = new Inscripcio(persona, activitat, datanova);
        System.out.println(insparam.toString());

        // Comprobació del métode cópia

        System.out.println("\n---Proba métode cópia---\n");

        Inscripcio inscopia = insparam.copia();
        System.out.println("Inscripcio copiada de la donada per parametere: \n" + inscopia);

    }
}
