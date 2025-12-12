package App;

/*import Data.*;
import Excepcions.*;
import Fitxers.*;
import Inscripcions.*;
import Lector.*;
import Usuari.*;
import Actividades.*;*/
import java.util.Scanner;



public class App {

    static Scanner teclat = new Scanner(System.in);// Ens permetrà llegir el que el usuari introdueixi per teclat.

    public static void main(String[] args) {

        int opcio = 0; // Inicialització de la variable opció
        do {
            try {
                mostrarMenu();
                opcio = Integer.parseInt(teclat.nextLine());
            } catch (NumberFormatException e) {// En cas de introdüir per exemple lletres en lloc d'un nùmero es
                                               // mostra aquesta excepciò.
                System.out.println(e + "   Es necessari introduir un numero");
            }
        } while (opcio < 22);// Mentre opcio<15 mostrar menú

        teclat.close();
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
