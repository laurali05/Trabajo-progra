package Lector;
import java.io.*;
import java.util.Scanner;
import Usuari.*;
import Excepcions.*;



public class FitxerUsuari {
    
    public static LlistaUsuari carregarFitxeUsuari() throws CarregarFitxersException{
        try{
            Scanner lectura = new Scanner(new File("fitxerUsuari.txt"));

            LlistaUsuari llista = new LlistaUsuari(1000);

            while (lectura.hasNextLine()) {
                String linea = lectura.nextLine();
                String[] parts = linea.split(";");
                String tipus = parts[0];
                String nom = parts[1];
                String correu = parts[2];
                Usuari u;
                if(tipus.equals("Estudiant")){
                    String grau = parts[3];
                    int anyIni = Integer.parseInt(parts[4]);
                    u = new UsuariEstudiant(nom,correu,grau,anyIni);
                    llista.afegir(u);

                }
                else if(tipus.equals("Professor")){
                    String departament = parts[3];
                    String campus = parts[4];
                    u = new UsuariPDI(nom,correu,departament,campus);

                }
                else if(tipus.equals("Treballador")){
                    String campus = parts[3];
                    u = new UsuariPTGAS(nom,correu,campus);
                }
                
            }
            lectura.close();
            return llista;
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            throw new CarregarFitxersException();
        }

    }
}
