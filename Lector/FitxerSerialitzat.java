package Lector;

import Excepcions.*;
import Inscripcions.LlistaInscripcions;
import java.io.*;

public class FitxerSerialitzat {

    /**
     * Mètode que carrega les dades de l'arxiu fitxerAssociacio.bin a un objecte
     * LlistaAssociacions
     * 
     * @return Les associacions de l'arxiu fitxerAssociacio en forma de llista
     * @throws CarregarFitxersException
     */
    public static LlistaInscripcions carregarInscripcions() throws CarregarFitxersException {
        LlistaInscripcions llista = null;
        ObjectInputStream inputFile;
        try {
            inputFile = new ObjectInputStream(new FileInputStream("Fitxers/fitxerInscripcions.bin"));
            llista = (LlistaInscripcions) inputFile.readObject();
            inputFile.close();
        } catch (ClassNotFoundException | IOException e) {
            throw new CarregarFitxersException();
        }
        return llista;
    }

    /**
     * Mètode que guarda les dades passades per paràmtre de la llista d'associacions
     * a l'arxiu fitxerAssociacions.bin
     * 
     * @param llista la llista d'associacions que volem guardar
     * @throws GuardarFitxersException
     */
    public static void guardarInscripcions(LlistaInscripcions llista) throws GuardarFitxersException {
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
