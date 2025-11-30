package Inscripcions;

import Actividades.Actividades;
import Data.Data;
import Usuari.Usuari;

public class UsaInscripcions {
    public static void main(String[] args) {
         System.out.println("------ PRUEBAS CLASE INSCRIPCIONS ------\n");

         // Generació de una inscripció

         System.out.println("\nGeneració de inscripció:\n");
         Inscripcions insini = new Inscripcions();
         System.out.println(insini.toString()+"\n"); 

         // Generació d'una inscripció amb parametres

         System.out.println("Generació de inscripció amb parametres:\n");
         Data datanova = new Data(6, 9 ,2025);
         Usuari persona = new Usuari("Robert", "robert@gmail.com");
         Actividades activitat = new Actividades("Robotica",new Data(3, 9,2025),new Data(3,11,2025),new String[] { "PDI", "Estudiants" });
         Inscripcions insparam = new Inscripcions(persona, activitat,8,datanova);
         System.out.println(insparam.toString());

         //Comprobació del métode cópia

         System.out.println("\n---Proba métode cópia---\n");
         
         Inscripcions inscopia = insparam.copia();
         System.out.println("Inscripcio copiada de la donada per parametere: \n"+inscopia); 

    }
}
