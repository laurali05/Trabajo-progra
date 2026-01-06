package Lector;

import Excepcions.*;
import Inscripcions.LlistaInscripcio;
import java.io.*;

/**
 * Clase FitxerSerialitzat
 * Autor: Izan Flores
 */
public class FitxerSerialitzat {

    /**
     * Mètode que carrega les dades de l'arxiu fitxerInscripcio.bin a un objecte
     * LlistaInscripcions
     * 
     * @return Les inscripcions de l'arxiu fitxerInscripcio en forma de llista
     * @throws CarregarFitxersException
     */
    public static LlistaInscripcio carregarInscripcions() throws CarregarFitxersException {
        LlistaInscripcio llista = null;
        ObjectInputStream inputFile;
        try {
            inputFile = new ObjectInputStream(new FileInputStream("Fitxers/fitxerInscripcions.bin"));
            llista = (LlistaInscripcio) inputFile.readObject();
            inputFile.close();
        } catch (ClassNotFoundException | IOException e) {
            throw new CarregarFitxersException();
        }
        return llista;
    }

    /**
     * Mètode que guarda les dades passades per paràmtre de la llista d'inscripcions
     * a l'arxiu fitxerInscripcions.bin
     * 
     * @param llista la llista d'Inscripcions que volem guardar
     * @throws GuardarFitxersException
     */
    public static void guardarInscripcions(LlistaInscripcio llista) throws GuardarFitxersException {
        ObjectOutputStream outputFile;
        try {
            outputFile = new ObjectOutputStream(new FileOutputStream("Fitxers/fitxerInscripcions.bin"));
            outputFile.writeObject(llista);
            outputFile.close();
        } catch (IOException e) {
            throw new GuardarFitxersException();
        }
    }
}
