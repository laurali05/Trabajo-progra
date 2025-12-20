package App;

import Data.*;
import Excepcions.*;
import Fitxers.*;
import Inscripcions.*;
import Lector.*;
import Usuari.*;
import Actividades.*;
import java.util.Scanner;

public class App {

    static Scanner teclat = new Scanner(System.in, "UTF-8");// Ens permetrà llegir el número que el usuari introdueixi
                                                            // per teclat

    public static void main(String[] args) {
        LlistaUsuari llistaUsuari = new LlistaUsuari(1000);
        LlistaActividades llistaActividades = new LlistaActividades(1000);
        LlistaInscripcions llistaInscripcions = new LlistaInscripcions(1000);
        LlistaEspera llistaEspera = new LlistaEspera();
        Data avui = new Data();

        int opcio = 0;

        try {
            llistaUsuari = FitxerUsuari.carregarFitxeUsuari();
            llistaActividades = FitxerActividades.carregarFitxerActividades();
            llistaInscripcions = FitxerSerialitzat.carregarInscripcions();

        } catch (CarregarFitxersException e) {
            System.out.println(e + "Error al cargar els fitxers");

        }

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
                    opcio1(avui);
                    break;
                case 2:
                    opcio2(llistaUsuari, llistaActividades, llistaInscripcions);
                    break;
                case 3:
                    opcio3(llistaActividades, avui);
                    break;
                case 4:
                    opcio4(llistaActividades, llistaInscripcions, avui);
                    break;
                case 5:
                    opcio5(llistaActividades, avui);
                    break;
                case 6:
                    opcio6(llistaActividades, llistaInscripcions);
                    break;
                case 7:
                    opcio7(llistaActividades);
                    break;
                case 8:
                    opcio8(llistaUsuari);
                    break;
                case 9:
                    opcio9(llistaUsuari, llistaInscripcions);
                    break;
                case 10:
                    opcio10(llistaUsuari, llistaActividades, avui, llistaInscripcions);
                    break;
                case 11:
                    opcio11(llistaUsuari);
                    break;
                case 12:
                    opcio12(llistaUsuari, llistaInscripcions, llistaActividades, llistaEspera);
                    break;
                case 13:
                    opcio13(llistaActividades);
                    break;
                case 14:
                    opcio14(llistaActividades);
                    break;
                case 15:
                    opcio15(llistaActividades);
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
                    opcio20(llistaInscripcions, llistaUsuari);
                    break;
                case 21:
                    opcio21(llistaActividades, llistaInscripcions, avui);
                    break;
            }
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

        }

        System.out.println("Has sortit del programa");

        teclat.close();
    }

    /**
     * Método que elimina una actividad si no cumple con las condiciones de asistencia 
     * Autor Laura Lope
     * 
     * @param llistaActividades
     * @param llistaInscripcions
     * @param avui
     */
    private static void opcio21(LlistaActividades llistaActividades, LlistaInscripcions llistaInscripcions, Data avui) {
        for (int i = 0; i < llistaActividades.getNumElems(); i++){
            Actividades act = llistaActividades.getActividad(i);

            if(act.getTipo().equalsIgnoreCase("dia")) {
                ActividadesDia dia = (ActividadesDia) act;

                if((!avui.esDataInferiorOigual(dia.getPeriodoFin())) && (llistaInscripcions.contarInscritos(dia.getNom()) < (0.1*dia.getLimite()))){
                    llistaActividades.eliminarActividad(i);
                    System.out.println("La actividad " + dia.getNom() + " se ha eliminado correctamente");
                }
            } else if (act.getTipo().equalsIgnoreCase("periodica")){
                ActividadesPeriodicas periodica = (ActividadesPeriodicas) act;

                if((!avui.esDataInferiorOigual(periodica.getPeriodoFin())) && (llistaInscripcions.contarInscritos(periodica.getNom()) < (0.1*periodica.getLimite()))){
                    llistaActividades.eliminarActividad(i);
                    System.out.println("La actividad " + periodica.getNom() + " se ha eliminado correctamente");

                }
            } else if (act.getTipo().equalsIgnoreCase("Online")) {
                ActividadesOnline online = (ActividadesOnline) act;

                if(llistaInscripcions.contarInscritos(online.getNom()) < 20){
                    llistaActividades.eliminarActividad(i);
                    System.out.println("La actividad " + online.getNom() + " se ha eliminado correctamente");

                }
            }
        }

    }

    private static void opcio20(LlistaInscripcions llistaInscripcions, LlistaUsuari llistaUsuari) {
        int opcio = 0;
        Usuari usuMesActiu = null;
        System.err.println("Quin tipus especific d'usuari vols mostrar:");
        System.out.println("1. Mostrar l'estudiant més actiu");
        System.out.println("2. Mostrar el professor més actiu");
        System.out.println("3. Mostrar el treballador més actiu");
        try {
            opcio = Integer.parseInt(teclat.nextLine());
            usuMesActiu = llistaInscripcions.UsuariMesActiu(opcio, llistaUsuari);
        } catch (NumberFormatException e) {
            System.err.println(e + "   Es necessari introduir un numero.");
        }
        if (usuMesActiu != null) {
            System.out.println("L'usuari més actiu del tipus seleccionat és: \n" + usuMesActiu);
        }

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

    /**
     * Método que añade una actividad online
     * Autor Laura Lope
     * 
     * @param llistaActividades
     */
    private static void opcio15(LlistaActividades llistaActividades) {
        ActividadesOnline online = new ActividadesOnline();
        online.setTipo("Online");

        System.out.println("Escribe el nombre de la actividad");
        String nom = teclat.nextLine();
        online.setNom(nom);

        System.out.println("Introduce el inicio del periodo de inscripción ");
        String periodoInText = teclat.nextLine();
        String[] partesPeriodoIn = periodoInText.split("/");
        Data periodoIn = new Data(Integer.parseInt(partesPeriodoIn[0]),
                Integer.parseInt(partesPeriodoIn[1]),
                Integer.parseInt(partesPeriodoIn[2]));
        online.setPeriodoIn(periodoIn);

        System.out.println("Introduce el final del periodo de inscripción ");
        String periodoFinText = teclat.nextLine();
        String[] partesPeriodoFin = periodoFinText.split("/");
        Data periodoFin = new Data(Integer.parseInt(partesPeriodoFin[0]),
                Integer.parseInt(partesPeriodoFin[1]),
                Integer.parseInt(partesPeriodoFin[2]));
        online.setPeriodoFin(periodoFin);

        System.out.println("Introduce los colectivos que pueden asistir a esta actividad ");
        String colectivos = teclat.nextLine();
        online.setColectivos(colectivos);

        System.out.println("Introduce la fecha de inicio de la actividad ");
        String fechaInText = teclat.nextLine();
        String[] partesFecha = fechaInText.split("/");
        Data fechaIn = new Data(Integer.parseInt(partesFecha[0]),
                Integer.parseInt(partesFecha[1]),
                Integer.parseInt(partesFecha[2]));
        online.setFechaIn(fechaIn);

        System.out.println("Introduce el periodo de visualización de la actividad");
        String periodoVisText = teclat.nextLine();
        int periodoVis = Integer.parseInt(periodoVisText);
        online.setPeriodoVis(periodoVis);

        System.out.println("Introduce el enlace de la actividad");
        String enlace = teclat.nextLine();
        online.setEnlace(enlace);

        try {
            llistaActividades.afegir(online);
            System.out.println("La actividad se ha guardado correctamente");
        } catch (NomActivitatDuplicatException e) {
            System.err.println("Ya existe una actividad con el nombre: " + online.getNom());
        }

    }

    /**
     * Método que añade una nueva actividad periodíca
     * Autor Laura Lope
     * 
     * @param llistaActividades
     */
    private static void opcio14(LlistaActividades llistaActividades) {
        ActividadesPeriodicas periodica = new ActividadesPeriodicas();

        periodica.setTipo("Periodica");

        System.out.println("Escribe el nombre de la actividad");
        String nom = teclat.nextLine();
        periodica.setNom(nom);

        System.out.println("Introduce el inicio del periodo de inscripción ");
        String periodoInText = teclat.nextLine();
        String[] partesPeriodoIn = periodoInText.split("/");
        Data periodoIn = new Data(Integer.parseInt(partesPeriodoIn[0]),
                Integer.parseInt(partesPeriodoIn[1]),
                Integer.parseInt(partesPeriodoIn[2]));
        periodica.setPeriodoIn(periodoIn);

        System.out.println("Introduce el final del periodo de inscripción ");
        String periodoFinText = teclat.nextLine();
        String[] partesPeriodoFin = periodoFinText.split("/");
        Data periodoFin = new Data(Integer.parseInt(partesPeriodoFin[0]),
                Integer.parseInt(partesPeriodoFin[1]),
                Integer.parseInt(partesPeriodoFin[2]));
        periodica.setPeriodoFin(periodoFin);

        System.out.println("Introduce los colectivos que pueden asistir a esta actividad ");
        String colectivos = teclat.nextLine();
        periodica.setColectivos(colectivos);

        System.out.println("Introduce el dia de la semana en el que se realizará la actividad");
        String diaSemana = teclat.nextLine();
        periodica.setDiaSemana(diaSemana);

        System.out.println("Introduce el horario de la actividad ");
        String horarioText = teclat.nextLine();
        String[] PartesHorario = horarioText.split(":");
        int[] horario = new int[2];
        horario[0] = Integer.parseInt(PartesHorario[0]);
        horario[1] = Integer.parseInt(PartesHorario[1]);
        periodica.setHorario(horario[0], horario[1]);

        System.out.println("Introduce la fecha de inicio de la actividad ");
        String fechaInText = teclat.nextLine();
        String[] partesFecha = fechaInText.split("/");
        Data fechaIn = new Data(Integer.parseInt(partesFecha[0]),
                Integer.parseInt(partesFecha[1]),
                Integer.parseInt(partesFecha[2]));
        periodica.setFechaIn(fechaIn);

        System.out.println("Introduce el número de semanas que se repetirá la actividad");
        String numSemanasText = teclat.nextLine();
        int numSemanas = Integer.parseInt(numSemanasText);
        periodica.setNumSemanas(numSemanas);

        System.out.println("Introduce el límite de plazas que hay en la actividad");
        String límiteText = teclat.nextLine();
        int limite = Integer.parseInt(límiteText);
        periodica.setLimite(limite);

        System.out.println("Introduce el precio de la actividad ");
        String precioText = teclat.nextLine();
        double precio = Double.parseDouble(precioText);
        periodica.setPrecio(precio);

        System.out.println("Introduce el centro donde se realizará la actividad");
        String centro = teclat.nextLine();
        periodica.setCiudad(centro);

        System.out.println("Introduce la ciudad donde se realizará la actividad");
        String ciudad = teclat.nextLine();
        periodica.setCiudad(ciudad);

        try {
            llistaActividades.afegir(periodica);
            System.out.println("La actividad se ha guardado correctamente");
        } catch (NomActivitatDuplicatException e) {
            System.err.println("Ya existe una actividad con el nombre: " + periodica.getNom());
        }

    }

    /**
     * Método que añade una nueva actividad de tipo día
     * Autor Laura Lope
     * 
     * @param llistaActividades
     */
    private static void opcio13(LlistaActividades llistaActividades) {
        ActividadesDia dia = new ActividadesDia();

        dia.setTipo("Dia");

        System.out.println("Escribe el nombre de la actividad");
        String nom = teclat.nextLine();
        dia.setNom(nom);

        System.out.println("Introduce el inicio del periodo de inscripción ");
        String periodoInText = teclat.nextLine();
        String[] partesPeriodoIn = periodoInText.split("/");
        Data periodoIn = new Data(Integer.parseInt(partesPeriodoIn[0]),
                Integer.parseInt(partesPeriodoIn[1]),
                Integer.parseInt(partesPeriodoIn[2]));
        dia.setPeriodoIn(periodoIn);

        System.out.println("Introduce el final del periodo de inscripción ");
        String periodoFinText = teclat.nextLine();
        String[] partesPeriodoFin = periodoFinText.split("/");
        Data periodoFin = new Data(Integer.parseInt(partesPeriodoFin[0]),
                Integer.parseInt(partesPeriodoFin[1]),
                Integer.parseInt(partesPeriodoFin[2]));
        dia.setPeriodoFin(periodoFin);

        System.out.println("Introduce los colectivos que pueden asistir a esta actividad ");
        String colectivos = teclat.nextLine();
        dia.setColectivos(colectivos);

        System.out.println("Introduce la fecha de inicio de la actividad ");
        String fechaInText = teclat.nextLine();
        String[] partesFecha = fechaInText.split("/");
        Data fecha = new Data(Integer.parseInt(partesFecha[0]),
                Integer.parseInt(partesFecha[1]),
                Integer.parseInt(partesFecha[2]));
        dia.setFecha(fecha);

        System.out.println("Introduce el horario de la actividad ");
        String horarioText = teclat.nextLine();
        String[] PartesHorario = horarioText.split(":");
        int[] horario = new int[2];
        horario[0] = Integer.parseInt(PartesHorario[0]);
        horario[1] = Integer.parseInt(PartesHorario[1]);
        dia.setHorario(horario[0], horario[1]);

        System.out.println("Introduce el límite de plazas que hay en la actividad");
        String límiteText = teclat.nextLine();
        int limite = Integer.parseInt(límiteText);
        dia.setLimite(limite);

        System.out.println("Introduce el precio de la actividad ");
        String precioText = teclat.nextLine();
        double precio = Double.parseDouble(precioText);
        dia.setPrecio(precio);

        System.out.println("Introduce la ciudad donde se realizará la actividad");
        String ciudad = teclat.nextLine();
        dia.setCiudad(ciudad);

        try {
            llistaActividades.afegir(dia);
            System.out.println("La actividad se ha guardado correctamente");
        } catch (NomActivitatDuplicatException e) {
            System.err.println("Ya existe una actividad con el nombre: " + dia.getNom());
        }

    }

    /**
     * Autor Jordi
     * 
     * @param llistaUsuari
     * @param llistaInscripcions
     * @param llistaActivitat
     */
    private static void opcio12(LlistaUsuari llistaUsuari, LlistaInscripcions llistaInscripcions,
            LlistaActividades llistaActivitat, LlistaEspera llistaEspera) {

        System.out.println("Introdueix nom de l'Usuari");
        String nom = teclat.nextLine();
        System.out.println("Introdueix l'activitat que vol eliminar-se:");
        String activitat = teclat.nextLine();

        Usuari usuari = null;
        Actividades act = null;

        try {
            usuari = llistaUsuari.buscarUsuari(nom);
        } catch (UsuariNoTrobatException e) {
            System.err.println("No s'ha trobat l'usuari" + e);
        }

        try {
            act = llistaActivitat.buscarActividades(activitat);
        } catch (ActivitatNoTrobadaException e) {
            System.err.println("No s'ha trobat l'activitat" + e);

        }

        llistaInscripcions.eliminarInscripcio(usuari, act, llistaEspera);
    }

    private static void opcio11(LlistaUsuari llistaUsuari) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'opcio11'");
    }

    /**
     * Autor Izan
     * 
     * @param llistaUsuari
     * @param llistaActivitat
     * @param avui
     * @param llistaInscripcions
     */
    private static void opcio10(LlistaUsuari llistaUsuari, LlistaActividades llistaActivitat, Data avui,
            LlistaInscripcions llistaInscripcions) {
        Inscripcions ins = new Inscripcions();
        System.out.println("Introdueix nom de l'Usuari");
        String nom = teclat.nextLine();
        System.out.println("Introdueix l'activitat que vol fer:");
        String activitat = teclat.nextLine();

        Usuari nouUsuari = null;
        Actividades novaActividades = null;

        try {
            nouUsuari = llistaUsuari.buscarUsuari(nom);
        } catch (UsuariNoTrobatException e) {
            System.err.println("No s'ha trobat l'usuari" + e);
        }

        try {
            novaActividades = llistaActivitat.buscarActividades(activitat);
        } catch (ActivitatNoTrobadaException e) {
            System.err.println("No s'ha trobat l'activitat" + e);

        }

        ins.setUsuari(nouUsuari);
        ins.setActivitat(novaActividades);

        try {
            llistaInscripcions.afegir(ins);
            try {
                FitxerSerialitzat.guardarInscripcions(llistaInscripcions);
            } catch (GuardarFitxersException e) {
                System.err.println("Ja esta guardat");
            }
        } catch (UsuariJaInscritException e) {
            System.err.println("L'usuari " + e + " ja esta inscrit.");
        }

    }

    /**
     * Autor Jordi
     * 
     * @param llistaUsuari
     * @param llistaInscripcions
     */
    private static void opcio9(LlistaUsuari llistaUsuari, LlistaInscripcions llistaInscripcions) {
        System.out.println("Introduiu el nom del usuari:");
        String nom = teclat.nextLine();
        Usuari usu = null;
        try {
            usu = llistaUsuari.buscarUsuari(nom);
        } catch (UsuariNoTrobatException e) {
            System.out.println("No s'ha trobat el nom del usuari " + e);
        }
        LlistaActividades llistaActitatsUsu = llistaInscripcions.ActivitatsUsuari(usu);
        if (llistaActitatsUsu.getNumElems() == 0) {
            System.out.println("L'usuari no està apuntat a cap activitat");
        } else {
            System.out.println(llistaActitatsUsu);
        }

    }

    /**
     * Autor Jordi
     * 
     * @param llistaUsuari
     */
    private static void opcio8(LlistaUsuari llistaUsuari) {
        System.out.println("introdueix el nom de l'usuari");
        String nom = teclat.nextLine();
        Usuari usu = null;
        try {
            usu = llistaUsuari.buscarUsuari(nom);
        } catch (UsuariNoTrobatException e) {
            System.out.println("No s'ha trobat el nom del usuari " + e);
        }
        System.out.println(usu);

    }

    /**
     * Método que devuelve la información de una actividad en concreto
     * Autor Laura Lope
     * 
     * @param llistaActividades
     * @param Actividades
     */
    private static void opcio7(LlistaActividades llistaActividades) {
        System.out.println("Introduce el nombre de la actividad");
        String actividad = teclat.nextLine();

        for (int i = 0; i < llistaActividades.getNumElems(); i++) {
            Actividades a = llistaActividades.getActividad(i);
            if (a.getNom().equalsIgnoreCase(actividad)) {
                System.out.println("\n" + a.toString());
            }
        }
    }

    /**
     * Método que indica las actividades con plazas disponibles
     * Autor Laura Lope
     * 
     * @param llistaActividades
     * @param llistaInscripcions
     */
    private static void opcio6(LlistaActividades llistaActividades, LlistaInscripcions llistaInscripcions) {

        for (int i = 0; i < llistaActividades.getNumElems(); i++) {
            Actividades a = llistaActividades.getActividad(i);

            if (a.getTipo().equalsIgnoreCase("Dia")) {
                ActividadesDia dia = (ActividadesDia) a;

                int numInscritos = llistaInscripcions.contarInscritos(dia.getNom());

                if (numInscritos <= dia.getLimite()) {
                    System.out.println("En la actividad '" + dia.getNom() + "'' hay plazas disponibles");
                }
            } else if (a.getTipo().equalsIgnoreCase("Periodica")) {
                ActividadesPeriodicas periodica = (ActividadesPeriodicas) a;

                int numInscritos = llistaInscripcions.contarInscritos(periodica.getNom());

                if (numInscritos <= periodica.getLimite()) {
                    System.out.println("En la actividad '" + periodica.getNom() + "'' hay plazas diposnibles");
                }
            } else if (a.getTipo().equalsIgnoreCase("Online")) {
                ActividadesOnline online = (ActividadesOnline) a;
                System.out.println("En la actividad '" + online.getNom() + "'' hay plazas disponibles");
            }
        }
    }

    /**
     * Método que te devuelve el nombre de las actividades que están activas
     * Autor Laura Lope
     * 
     * @param llistaActividades
     */
    private static void opcio5(LlistaActividades llistaActividades, Data avui) {
        boolean act = false;
        int contador = 0;

        for (int i = 0; i < llistaActividades.getNumElems(); i++) {
            Actividades a = llistaActividades.getActividad(i);

            if (a.tipo.equalsIgnoreCase("Dia")) {
                ActividadesDia dia = (ActividadesDia) a;
                if (avui.esIgual(dia.getFechaIn())) {
                    contador++;
                    System.out.println(contador + ". " + dia.getNom());
                    act = true;
                }

            } else if (a.tipo.equalsIgnoreCase("Periodica")) {
                ActividadesPeriodicas periodica = (ActividadesPeriodicas) a;

                Data fechaFin = periodica.getFechaIn().sumarDias(periodica.getNumSemanas() * 7);

                if (periodica.getFechaIn().esDataInferiorOigual(avui) && (avui.esDataInferiorOigual(fechaFin))) {
                    contador++;
                    System.out.println(contador + ". " + periodica.getNom());
                    act = true;
                }
            } else if (a.tipo.equalsIgnoreCase("Online")) {
                ActividadesOnline online = (ActividadesOnline) a;

                Data fechaFin = online.getFechaIn().sumarDias(online.getPeriodoVis());

                if (online.getFechaIn().esDataInferiorOigual(avui) && (avui.esDataInferiorOigual(fechaFin))) {
                    contador++;
                    System.out.println(contador + ". " + online.getNom());
                    act = true;
                }
            }
        }

        if (!act) {
            System.out.println("No hay actividades activas");
        }
    }

    /**
     * Método que comprueba que actividades tiene clase hoy y si hay plazas y lista
     * de espera
     * Autor Laura Lope
     * 
     * @param llistaActividades
     * @param llistaInscripcions
     */
    private static void opcio4(LlistaActividades llistaActividades, LlistaInscripcions llistaInscripcions, Data avui) {
        boolean act = false;
        int contador = 0;

        for (int i = 0; i < llistaActividades.getNumElems(); i++) {
            Actividades b = llistaActividades.getActividad(i);

            if (b.tipo.equalsIgnoreCase("Dia")) {
                ActividadesDia dia = (ActividadesDia) b;
                if (avui.esIgual(dia.getFechaIn())) {
                    contador++;

                    System.out.println("\nActividad " + contador + ": \n\n" + dia.toString());

                    int numInscritos = llistaInscripcions.contarInscritos(dia.getNom());

                    int aforoMaximo = dia.getLimite();

                    if (numInscritos >= aforoMaximo) {
                        System.out.println("Plazas llenas: SÍ");
                    } else {
                        System.out.println("Plazas llenas: NO");
                    }

                    if (numInscritos > aforoMaximo) {
                        System.out.println("Lista de espera: SÍ");
                    } else {
                        System.out.println("Lista de espera: NO");
                    }

                    act = true;

                }

            } else if (b.tipo.equalsIgnoreCase("Periodica")) {
                ActividadesPeriodicas periodica = (ActividadesPeriodicas) b;

                Data fechaFin = periodica.getFechaIn().sumarDias(periodica.getNumSemanas() * 7);

                if ((periodica.getFechaIn().esDataInferiorOigual(avui)) && ((avui.esDataInferiorOigual(fechaFin)))) {

                    int diasDesdeInicio = periodica.getFechaIn().numDiesAData(avui);

                    if (diasDesdeInicio % 7 == 0) {
                        contador++;
                        System.out.println("\nActividad " + contador + ": \n\n" + periodica.toString());

                        int numInscritos = llistaInscripcions.contarInscritos(periodica.getNom());

                        int aforoMaximo = periodica.getLimite();

                        if (numInscritos >= aforoMaximo) {
                            System.out.println("Plazas llenas: SÍ");
                        } else {
                            System.out.println("Plazas llenas: NO");
                        }

                        if (numInscritos > aforoMaximo) {
                            System.out.println("Lista de espera: SÍ");
                        } else {
                            System.out.println("Lista de espera: NO");
                        }
                        act = true;
                    }
                }

            } else if (b.tipo.equalsIgnoreCase("Online")) {
                ActividadesOnline online = (ActividadesOnline) b;

                Data fechaFin = online.getFechaIn().sumarDias(online.getPeriodoVis());

                if ((online.getFechaIn().esDataInferiorOigual(avui)) && (avui.esDataInferiorOigual(fechaFin))) {
                    contador++;

                    System.out.println("\nActividad " + contador + ": \n\n" + online.toString());
                    System.out.println("Plazas llenas: NO");
                    System.out.println("Lista de espera: NO");
                    act = true;
                }
            }
        }

        if (!act) {
            System.out.println("Hoy no hay actividades");
        }
    }

    private static void opcio3(LlistaActividades llistaActividades, Data avui) {
        Data hoy = avui;
        boolean actividades = false;
        int contador = 0;

        for (int i = 0; i < llistaActividades.getNumElems(); i++) {
            Actividades a = llistaActividades.getActividad(i);

            if ((a.getPeriodoIn().esDataInferiorOigual(hoy)) && (hoy.esDataInferiorOigual(a.getPeriodoFin()))) {
                contador++;
                System.out.println("\nActividad " + contador + ":\n\n" + a.toString());
                actividades = true;
            }
        }

        if (!actividades) {
            System.out.println("No hay actividades en periodo de inscripción");
        }
    }

    /**
     * Autor Jordi
     * 
     * @param llistaUsuari
     * @param llistaActividades
     * @param llistaInscripcions
     */
    private static void opcio2(LlistaUsuari llistaUsuari, LlistaActividades llistaActividades,
            LlistaInscripcions llistaInscripcions) {
        int opcio = 0;
        System.out.println("Quina llista vols mostrar:");
        System.out.println("1. Llista d'usuaris");
        System.out.println("2. Llista d'activitats");
        System.out.println("3. Llista d'incripcions");
        try {
            opcio = Integer.parseInt(teclat.nextLine());

            switch (opcio) {
                case 1:
                    System.out.println("\nEscull una opció:");
                    System.out.println("1. Mostrar llista sencera");
                    System.out.println("2. Mostrar llista de tipus especific");
                    opcio = Integer.parseInt(teclat.nextLine());
                    switch (opcio) {
                        case 1:
                            System.out.println(llistaUsuari.toString());
                            break;
                        case 2:

                            System.err.println("Quin tipus especific de la llista d'usuaris vols mostrar:");
                            System.out.println("1. Mostrar llista d'estudiants");
                            System.out.println("2. Mostrar llista de professors");
                            System.out.println("3. Mostrar llista de treballadors");
                            opcio = Integer.parseInt(teclat.nextLine());
                            LlistaUsuari llistaEsp = llistaUsuari.llistaTipus(opcio);
                            System.out.println(llistaEsp.toString());
                    }
                    break;

                case 2:
                    System.out.println("\nEscull una opció:");
                    System.out.println("1. Mostrar llista sencera");
                    System.out.println("2. Mostrar llista de tipus especific");
                    opcio = Integer.parseInt(teclat.nextLine());
                    switch (opcio) {
                        case 1:
                            System.out.println(llistaActividades.toString());
                            break;
                        case 2:

                            System.err.println("Quin tipus especific de la llista d'usuaris vols mostrar:");
                            System.out.println("1. Mostrar llista de activitats de un dia específic");
                            System.out.println("2. Mostrar llista de activitats online");
                            System.out.println("3. Mostrar llista de activitats periodiques");
                            opcio = Integer.parseInt(teclat.nextLine());
                            LlistaActividades llistaEsp = llistaActividades.llistaTipus(opcio);
                            System.out.println(llistaEsp.toString());
                    }
                    break;

                case 3:
                    if (llistaInscripcions == null) {
                        System.out.println("Llista Buida");
                    } else {
                        System.out.println(llistaInscripcions.toString());
                        break;
                    }
            }
        } catch (NumberFormatException e) {
            System.err.println(e + "   Es necessari introduir un numero.");
        }
    }

    /**
     * Autor Izan
     * 
     * @param avui
     */
    private static void opcio1(Data avui) {
        System.out.println("La data d'avui es " + avui);
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
        System.out.println("  11. Consultar usuaris apuntats i en llista d'espera a una activitat en qüestió");
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
