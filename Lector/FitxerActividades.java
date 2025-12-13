package Lector;

import java.io.*;
import java.util.*;
import Actividades.*;
import Data.Data;
import Excepcions.*;

/**
 * Clase FitxerActividades
 * Autor: Laura Lope
 */
public class FitxerActividades {

    /**
     * Método para cargar los datos del fichero de actividades
     * 
     * @return
     * @throws CarregarFitxersException
     */
    public static LlistaActividades carregarFitxerActividades() throws CarregarFitxersException {
        try {
            Scanner leer = new Scanner(new File("Fitxers/fitxerActividades.txt"), "UTF-8");

            LlistaActividades lista = new LlistaActividades(1000);

            while (leer.hasNextLine()) {
                String linea = leer.nextLine();
                String[] secciones = linea.split(";");

                String tipo = secciones[0];
                String nombre = secciones[1];

                String[] partesPeriodoIn = secciones[2].split("/");
                Data periodoIn = new Data(Integer.parseInt(partesPeriodoIn[0]),
                        Integer.parseInt(partesPeriodoIn[1]),
                        Integer.parseInt(partesPeriodoIn[2]));

                String[] partesPeriodoFin = secciones[3].split("/");
                Data periodoFin = new Data(Integer.parseInt(partesPeriodoFin[0]),
                        Integer.parseInt(partesPeriodoFin[1]),
                        Integer.parseInt(partesPeriodoFin[2]));

                String[] colectivos = secciones[4].split(",");

                Actividades a;
                if (tipo.equals("Dia")) {
                    String[] partesFecha = secciones[5].split("/");
                    Data fecha = new Data(Integer.parseInt(partesFecha[0]),
                            Integer.parseInt(partesFecha[1]),
                            Integer.parseInt(partesFecha[2]));

                    String[] PartesHorario = secciones[6].split(":");
                    int[] horario = new int[2];
                    horario[0] = Integer.parseInt(PartesHorario[0]);
                    horario[1] = Integer.parseInt(PartesHorario[1]);

                    int limite = Integer.parseInt(secciones[7]);

                    double precio = Double.parseDouble(secciones[8]);

                    String ciudad = secciones[9];

                    a = new ActividadesDia(nombre, periodoIn, periodoFin, colectivos, fecha, horario, limite, precio,
                            ciudad);
                    lista.afegir(a);

                } else if (tipo.equals("Periodica")) {
                    String diaSemana = secciones[5];

                    String[] PartesHorario = secciones[6].split(":");
                    int[] horario = new int[2];
                    horario[0] = Integer.parseInt(PartesHorario[0]);
                    horario[1] = Integer.parseInt(PartesHorario[1]);

                    String[] partesFechaIn = secciones[7].split("/");
                    Data fechaIn = new Data(Integer.parseInt(partesFechaIn[0]),
                            Integer.parseInt(partesFechaIn[1]),
                            Integer.parseInt(partesFechaIn[2]));

                    int numSemanas = Integer.parseInt(secciones[8]);

                    int limite = Integer.parseInt(secciones[9]);

                    double precio = Double.parseDouble(secciones[10]);

                    String centro = secciones[11];

                    String ciudad = secciones[12];

                    a = new ActividadesPeriodicas(nombre, periodoIn, periodoFin, colectivos, diaSemana, horario,
                            fechaIn, numSemanas, limite, precio, centro, ciudad);
                    lista.afegir(a);

                } else if (tipo.equals("Online")) {
                    String[] partesFechaIn = secciones[5].split("/");
                    Data fechaIn = new Data(Integer.parseInt(partesFechaIn[0]),
                            Integer.parseInt(partesFechaIn[1]),
                            Integer.parseInt(partesFechaIn[2]));

                    int periodoVis = Integer.parseInt(secciones[6]);

                    String enlace = secciones[7];

                    a = new ActividadesOnline(nombre, periodoIn, periodoFin, colectivos, fechaIn, periodoVis, enlace);
                    lista.afegir(a);
                }
            }

            leer.close();
            return lista;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new CarregarFitxersException();
        }
    }

}
