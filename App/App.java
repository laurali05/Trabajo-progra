package App;

import Data.*;
import Excepcions.*;
import Fitxers.*;
import Inscripcions.*;
import Lector.*;
import Usuari.*;
import Actividades.*;
import java.util.Scanner;

import Usuari.LlistaUsuari;

public class App {

    static Scanner teclat = new Scanner(System.in);// Ens permetrà llegir el número que el usuari introdueixi per
                                                   // teclat.

    public static void main(String[] args) {

        LlistaUsuari llistaUsuari = null;
        LlistaActividades llistaActividades = null;
        LlistaInscripcions llistaInscripcions = null;
        

        try {
             llistaUsuari = FitxerUsuari.carregarFitxeUsuari();
             llistaActividades = FitxerActividades.carregarFitxerActividades();
             llistaInscripcions = FitxerSerialitzat.carregarInscripcions();
             
        } catch (CarregarFitxersException e) {
            System.out.println(e + "   Error al cargar el fitxer");
        }

        int opcio = 0;

        try {
            mostrarMenu();
            opcio = Integer.parseInt(teclat.nextLine());
        } catch (NumberFormatException e) {// En cas de introdüir per exemple lletres en lloc d'un nùmero es
                                           // mostra aquesta excepciò.
            System.out.println(e + "   Es necessari introduir un numero.");
        }
        if (opcio > 22) {
            System.out.println("Numero més gran dels valors del menu");
        }
        while (opcio != 22) {

            switch (opcio) {

                case 1:
                    opcio1(llistaUsuari);
                    break;
                case 2:
                    opcio2(llistaUsuari);
                    break;
                case 3:
                    opcio3(llistaUsuari);
                    break;
                case 4:
                    opcio4(llistaUsuari);
                    break;
                case 5:
                    opcio5(llistaUsuari);
                    break;
                case 6:
                    opcio6(llistaUsuari);
                    break;
                case 7:
                    opcio7(llistaUsuari);
                    break;
                case 8:
                    opcio8(llistaUsuari);
                    break;
                case 9:
                    opcio9(llistaUsuari);
                    break;
                case 10:
                    opcio10(llistaUsuari);
                    break;
                case 11:
                    opcio11(llistaUsuari);
                    break;
                case 12:
                    opcio12(llistaUsuari);
                    break;
                case 13:
                    opcio13(llistaUsuari);
                    break;
                case 14:
                    opcio14(llistaUsuari);
                    break;
                case 15:
                    opcio15(llistaUsuari);
                    break;
                case 16:
                    opcio16(llistaUsuari);
                    break;
                case 17:
                    opcio17(llistaUsuari);
                    break;
                case 18:
                    opcio18(llistaUsuari);
                    break;
                case 19:
                    opcio19(llistaUsuari);
                    break;
                case 20:
                    opcio20(llistaUsuari);
                    break;
                case 21:
                    opcio21(llistaUsuari);
                    break;
            }
            try {
                mostrarMenu();
                opcio = Integer.parseInt(teclat.nextLine());
            } catch (NumberFormatException e) {// En cas de introdüir per exemple lletres en lloc d'un nùmero es
                                               // mostra aquesta excepciò.
                System.out.println(e + "   Es necessari introduir un numero.");
            }
        }

        System.out.println("Has sortit del programa");

        teclat.close();
    }

    private static void opcio21(LlistaUsuari llistaUsuari) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'opcio21'");
    }

    private static void opcio20(LlistaUsuari llistaUsuari) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'opcio20'");
    }

    private static void opcio19(LlistaUsuari llistaUsuari) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'opcio19'");
    }

    private static void opcio18(LlistaUsuari llistaUsuari) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'opcio18'");
    }

    private static void opcio17(LlistaUsuari llistaUsuari) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'opcio17'");
    }

    private static void opcio16(LlistaUsuari llistaUsuari) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'opcio16'");
    }

    private static void opcio15(LlistaUsuari llistaUsuari) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'opcio15'");
    }

    private static void opcio14(LlistaUsuari llistaUsuari) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'opcio14'");
    }

    private static void opcio13(LlistaUsuari llistaUsuari) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'opcio13'");
    }

    private static void opcio12(LlistaUsuari llistaUsuari) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'opcio12'");
    }

    private static void opcio11(LlistaUsuari llistaUsuari) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'opcio11'");
    }

    private static void opcio10(LlistaUsuari llistaUsuari) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'opcio10'");
    }

    private static void opcio9(LlistaUsuari llistaUsuari) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'opcio9'");
    }

    private static void opcio8(LlistaUsuari llistaUsuari) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'opcio8'");
    }

    private static void opcio7(LlistaUsuari llistaUsuari) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'opcio7'");
    }

    private static void opcio6(LlistaUsuari llistaUsuari) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'opcio6'");
    }

    private static void opcio5(LlistaUsuari llistaUsuari) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'opcio5'");
    }

    private static void opcio4(LlistaUsuari llistaUsuari) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'opcio4'");
    }

    private static void opcio3(LlistaUsuari llistaUsuari) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'opcio3'");
    }

    private static void opcio2(LlistaUsuari llistaUsuari) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'opcio2'");
    }

    private static void opcio1(LlistaUsuari llistaUsuari) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'opcio1'");
    }

    private static void mostrarMenu() {
        System.out.println("\n\tMENÚ:");
        System.out.println("  1. Quina es la data d'avui?");
        System.out.println("  2. Mostrar dades d'una llista");
        System.out.println("  3. Informació de les activitats en període d'inscripció");
        System.out.println("  4. Activitats amb classe avui");
        System.out.println("  5. Nom de les activitats actives");
        System.out.println("  6. Activitats amb places disponibles");
        System.out.println("  7. Detall d'una activitat en qüestió");
        System.out.println("  8. Detall d'un usuari en qüestió");
        System.out.println("  9. Activitats a la que esta apuntat un usuari en qüestió");
        System.out.println("  10. Inscribir-me a una activitat");
        System.out.println("  11. Consultar usurais apuntats i en llista d'espera a una activitat en qüestió");
        System.out.println("  12. Eliminar un usuari d'una activitat");
        System.out.println("  13. Afegir nova activitat d'un día");
        System.out.println("  14. Afegir nova activitat periòdica");
        System.out.println("  15. Afegir nova activitat en línia");
        System.out.println("  16. Valorar activitat per part d'un assistent");
        System.out.println("  17. Valoracions de les activitats que han acabat");
        System.out.println("  18. Valoracions que ha fet un usuari");
        System.out.println("  19. Han valorat igual els usuaris de diferent col·lectius?");
        System.out.println("  20. Usuari més actiu d'un col·lectiu");
        System.out.println("  21. Baixa a les activitats que no arriben al mínim");
        System.out.println("  22. Sortir de la aplicació");
        System.out.println("Què desitges fer?");
        System.out.println("(Introdueix per teclat el nombre de l'opció desitjada)");
    }

}
