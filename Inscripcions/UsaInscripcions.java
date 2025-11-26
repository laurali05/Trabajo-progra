package Inscripcions;

import Data.Data;

public class UsaInscripcions {
    public static void main(String[] args) {
         System.out.println("------ PRUEBAS CLASE INSCRIPCIONS ------\n");
         System.out.println("Generació de inscripció:\n");
         Inscripcions insini = new Inscripcions();
         System.out.println(insini.toString()+"\n"); 
         System.out.println("Generació de inscripció amb parametres:\n");
         Data datanova = new Data(6, 9 ,2025);
         Inscripcions insparam = new Inscripcions(4,"Natació",9,datanova);
         System.out.println(insparam.toString());
    }
}
