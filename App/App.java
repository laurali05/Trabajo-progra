package App;

import Data.*;
import Excepcions.*;
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
        ListaActividad llistaActividades = new ListaActividad(1000);
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
            llistaInscripcions.assignarLlistaEspera(llistaEspera);
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
                    opcio10(llistaUsuari, llistaActividades, avui, llistaInscripcions, llistaEspera);
                    break;
                case 11:
                    opcio11(llistaInscripcions, llistaEspera, llistaActividades);
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
                    opcio16(llistaUsuari, llistaActividades, llistaInscripcions, avui);
                    break;
                case 17:
                    opcio17(llistaInscripcions);
                    break;
                case 18:
                    opcio18(llistaUsuari, llistaInscripcions);
                    break;
                case 19:
                    opcio19(llistaInscripcions, llistaActividades);
                    break;
                case 20:
                    opcio20(llistaInscripcions, llistaUsuari);
                    break;
                case 21:
                    opcio21(llistaActividades, llistaInscripcions, avui);
                    break;
            }
            System.out.println("\nPresiona ENTER para continuar");
            teclat.nextLine();

            try {
                llistaInscripcions.assignarLlistaEspera(llistaEspera);
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
     * Método que elimina una actividad si no cumple con las condiciones de
     * asistencia Autor Laura Lope
     *
     * @param llistaActividades
     * @param llistaInscripcions
     * @param avui
     */
    private static void opcio21(ListaActividad llistaActividades, LlistaInscripcions llistaInscripcions, Data avui) {
        llistaActividades.elActNoCondiciones(llistaInscripcions, avui);
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

    /**
     * Autor Izan
     * 
     * @param llistaInscripcions
     * @param llistaActividades
     */
    private static void opcio19(LlistaInscripcions llistaInscripcions,ListaActividad llistaActividades) {
        llistaInscripcions.mitjanaColectius(llistaActividades);
    }

    /**
     * Autor Izan
     * 
     * @param llistaUsuari
     * @param llistaInscripcions
     */
    private static void opcio18(LlistaUsuari llistaUsuari,LlistaInscripcions llistaInscripcions) {
        System.out.println("Introdueix el nom de l'usuari que vols veure les valoracions: "+llistaUsuari.nomUsuarios());
        String nom = teclat.nextLine();
        System.out.println(llistaInscripcions.valoracionsUsuari(nom, llistaUsuari));
    }

    /**
     * Autor Izan
     * 
     * @param llistaInscripcions
     */
    private static void opcio17(LlistaInscripcions llistaInscripcions) {
        System.out.println("Es mostraran les valoracions de les activitats acabades:\n");
        System.out.println(llistaInscripcions.mostrarValoracions());
    }

    /**
     * Autor Izan
     * 
     * @param llistaUsuari
     * @param llistaActividades
     * @param llistaInscripcions
     * @param avui
     */
    private static void opcio16(LlistaUsuari llistaUsuari, ListaActividad llistaActividades, LlistaInscripcions llistaInscripcions, Data avui) {
        System.out.println("Introdueix el nom de l'usuari:" + llistaUsuari.nomUsuarios());
        String nom = teclat.nextLine();
        System.out.println("Introdueix el nom de l'usuari:" + llistaActividades.nomActividades());
        String act = teclat.nextLine();
        System.out.println("Introdueix la valoració que vols donar si es posible (1-10):");
        String valoraciotext = teclat.nextLine();
        int valoracio = Integer.parseInt(valoraciotext);
        llistaInscripcions.valorarActivitat(llistaActividades, llistaUsuari, act, nom, avui, valoracio);
    }

    /**
     * Método que añade una actividad online Autor Laura Lope
     *
     * @param llistaActividades
     */
    private static void opcio15(ListaActividad llistaActividades) {
        ActividadOnline online = new ActividadOnline();
        online.setTipo("Online");

        System.out.println("Escribe el nombre de la actividad");
        String nom = teclat.nextLine();
        online.setNom(nom);

        System.out.println("Introduce el inicio del periodo de inscripción (dd/09-12/aaaa)");
        String periodoInText = teclat.nextLine();
        String[] partesPeriodoIn = periodoInText.split("/");
        Data periodoIn = new Data(Integer.parseInt(partesPeriodoIn[0]),
                Integer.parseInt(partesPeriodoIn[1]),
                Integer.parseInt(partesPeriodoIn[2]));
        try {
            online.setPeriodoIn(periodoIn);
        } catch (FechaIncorrectaExcepcion e) {
            System.err.println(e.getMessage());
        }

        System.out.println("Introduce el final del periodo de inscripción (dd/09-12/aaaa)");
        String periodoFinText = teclat.nextLine();
        String[] partesPeriodoFin = periodoFinText.split("/");
        Data periodoFin = new Data(Integer.parseInt(partesPeriodoFin[0]),
                Integer.parseInt(partesPeriodoFin[1]),
                Integer.parseInt(partesPeriodoFin[2]));
        try {
            online.setPeriodoFin(periodoFin);
        } catch (FechaIncorrectaExcepcion e) {
            System.err.println(e.getMessage());
        }

        System.out.println("Introduce los colectivos que pueden asistir a esta actividad (Estudiants, PGI o PTGAS)");
        String colectivos = teclat.nextLine();
        online.setColectivos(colectivos);

        System.out.println("Introduce la fecha de inicio de la actividad (dd/09-12/aaaa)");
        String fechaInText = teclat.nextLine();
        String[] partesFecha = fechaInText.split("/");
        Data fechaIn = new Data(Integer.parseInt(partesFecha[0]),
                Integer.parseInt(partesFecha[1]),
                Integer.parseInt(partesFecha[2]));
        online.setFechaIn(fechaIn);

        System.out.println("Introduce el periodo de visualización de la actividad (días)");
        String periodoVisText = teclat.nextLine();
        int periodoVis = Integer.parseInt(periodoVisText);
        online.setPeriodoVis(periodoVis);

        System.out.println("Introduce el enlace de la actividad");
        String enlace = teclat.nextLine();
        online.setEnlace(enlace);

        try {
            llistaActividades.afegir(online);
            System.out.println("La actividad se ha guardado correctamente");
            System.out.println(online.toString());
        } catch (NomActivitatDuplicatException e) {
            System.err.println("Ya existe una actividad con el nombre: " + online.getNom());
        } catch (NoHayEspacioException e){
            System.err.println(e.getMessage());
        }

    }

    /**
     * Método que añade una nueva actividad periodíca Autor Laura Lope
     *
     * @param llistaActividades
     */
    private static void opcio14(ListaActividad llistaActividades) {
        ActividadPeriodicas periodica = new ActividadPeriodicas();

        periodica.setTipo("Periodica");

        System.out.println("Escribe el nombre de la actividad");
        String nom = teclat.nextLine();
        periodica.setNom(nom);

        System.out.println("Introduce el inicio del periodo de inscripción (dd/09-12/aaaa)");
        String periodoInText = teclat.nextLine();
        String[] partesPeriodoIn = periodoInText.split("/");
        Data periodoIn = new Data(Integer.parseInt(partesPeriodoIn[0]),
                Integer.parseInt(partesPeriodoIn[1]),
                Integer.parseInt(partesPeriodoIn[2]));
        try {
            periodica.setPeriodoIn(periodoIn);
        } catch (FechaIncorrectaExcepcion e) {
            System.err.println(e.getMessage());
        }

        System.out.println("Introduce el final del periodo de inscripción (dd/09-12/aaaa)");
        String periodoFinText = teclat.nextLine();
        String[] partesPeriodoFin = periodoFinText.split("/");
        Data periodoFin = new Data(Integer.parseInt(partesPeriodoFin[0]),
                Integer.parseInt(partesPeriodoFin[1]),
                Integer.parseInt(partesPeriodoFin[2]));
        try {
            periodica.setPeriodoFin(periodoFin);
        } catch (FechaIncorrectaExcepcion e) {
            System.err.println(e.getMessage());
        }

        System.out.println("Introduce los colectivos que pueden asistir a esta actividad (Estudiants, PGI o PTGAS)");
        String colectivos = teclat.nextLine();
        periodica.setColectivos(colectivos);

        System.out.println("Introduce el dia de la semana en el que se realizará la actividad");
        String diaSemana = teclat.nextLine();
        periodica.setDiaSemana(diaSemana);

        System.out.println("Introduce el horario de la actividad (hh:mm)");
        String horarioText = teclat.nextLine();
        String[] PartesHorario = horarioText.split(":");
        int[] horario = new int[2];
        horario[0] = Integer.parseInt(PartesHorario[0]);
        horario[1] = Integer.parseInt(PartesHorario[1]);
        periodica.setHorario(horario[0], horario[1]);

        System.out.println("Introduce la fecha de inicio de la actividad (dd/09-12/aaaa)");
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

        System.out.println("Introduce el precio de la actividad (EUR.cent)");
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
            System.out.println(periodica.toString());
        } catch (NomActivitatDuplicatException e) {
            System.err.println("Ya existe una actividad con el nombre: " + periodica.getNom());
        } catch (NoHayEspacioException e){
            System.err.println(e.getMessage());
        }

    }

    /**
     * Método que añade una nueva actividad de tipo día Autor Laura Lope
     *
     * @param llistaActividades
     */
    private static void opcio13(ListaActividad llistaActividades) {
        ActividadDia dia = new ActividadDia();

        dia.setTipo("Dia");

        System.out.println("Escribe el nombre de la actividad");
        String nom = teclat.nextLine();
        dia.setNom(nom);

        System.out.println("Introduce el inicio del periodo de inscripción (dd/09-12/aaaa)");
        String periodoInText = teclat.nextLine();
        String[] partesPeriodoIn = periodoInText.split("/");
        Data periodoIn = new Data(Integer.parseInt(partesPeriodoIn[0]),
                Integer.parseInt(partesPeriodoIn[1]),
                Integer.parseInt(partesPeriodoIn[2]));
        try {
            dia.setPeriodoIn(periodoIn);
        } catch (FechaIncorrectaExcepcion e) {
            System.err.println(e.getMessage());
        }

        System.out.println("Introduce el final del periodo de inscripción (dd/09-12/aaaa)");
        String periodoFinText = teclat.nextLine();
        String[] partesPeriodoFin = periodoFinText.split("/");
        Data periodoFin = new Data(Integer.parseInt(partesPeriodoFin[0]),
                Integer.parseInt(partesPeriodoFin[1]),
                Integer.parseInt(partesPeriodoFin[2]));
        try {
            dia.setPeriodoFin(periodoFin);
        } catch (FechaIncorrectaExcepcion e) {
            System.err.println(e.getMessage());
        }

        System.out.println("Introduce los colectivos que pueden asistir a esta actividad (Estudiants, PGI o PTGAS)");
        String colectivos = teclat.nextLine();
        dia.setColectivos(colectivos);

        System.out.println("Introduce la fecha de inicio de la actividad (dd/09-12/aaaa)");
        String fechaInText = teclat.nextLine();
        String[] partesFecha = fechaInText.split("/");
        Data fecha = new Data(Integer.parseInt(partesFecha[0]),
                Integer.parseInt(partesFecha[1]),
                Integer.parseInt(partesFecha[2]));
        dia.setFecha(fecha);

        System.out.println("Introduce el horario de la actividad (hh:mm)");
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

        System.out.println("Introduce el precio de la actividad (EUR.cent)");
        String precioText = teclat.nextLine();
        double precio = Double.parseDouble(precioText);
        dia.setPrecio(precio);

        System.out.println("Introduce la ciudad donde se realizará la actividad");
        String ciudad = teclat.nextLine();
        dia.setCiudad(ciudad);

        try {
            llistaActividades.afegir(dia);
            System.out.println("La actividad se ha añadido correctamente");
            System.out.println(dia.toString());
        } catch (NomActivitatDuplicatException e) {
            System.err.println("Ya existe una actividad con el nombre: " + dia.getNom());
        } catch (NoHayEspacioException e){
            
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
            ListaActividad llistaActivitat, LlistaEspera llistaEspera) {

        System.out.println("Introdueix nom de l'Usuari: " + llistaUsuari.nomUsuarios());
        String nom = teclat.nextLine();

        System.out.println("Introdueix l'activitat que vol eliminar-se:" + llistaActivitat.nomActividades());
        String activitat = teclat.nextLine();

        Usuari usuari = null;
        Actividad act = null;

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

    /**
     * Autor Izan
     *
     * @param llistaInscripcions
     * @param llistaEspera
     * @param llistaActividades
     */
    private static void opcio11(LlistaInscripcions llistaInscripcions, LlistaEspera llistaEspera, ListaActividad llistaActividades) {
        System.out.println("Introdueix l'activitat per la qual vols veure els usuaris:");
        String act = teclat.nextLine();
        Actividad novaAct = null;
        try {
            novaAct = llistaActividades.buscarActividades(act);
        } catch (ActivitatNoTrobadaException e) {
            System.err.println("No s'ha trobat l'activitat" + e);

        }
        System.out.println("La quantitat d'usuaris a l'activitat es de " + llistaInscripcions.contarInscritos(act) + "\n Els usuaris son: " + llistaInscripcions.usuarisApuntats(novaAct) + "\n Els següents es troben en llista d'espera: " + llistaEspera.usuarisApuntats(novaAct));
    }

    /**
     * Autor Izan
     *
     * @param llistaUsuari
     * @param llistaActivitat
     * @param avui
     * @param llistaInscripcions
     */
    private static void opcio10(LlistaUsuari llistaUsuari, ListaActividad llistaActivitat, Data avui,
            LlistaInscripcions llistaInscripcions, LlistaEspera llistaEspera) {
        Inscripcions ins = new Inscripcions();
        System.out.println("Introdueix nom de l'Usuari: " + llistaUsuari.nomUsuarios());
        String nom = teclat.nextLine();

        System.out.println("Introdueix l'activitat que vol fer: " + llistaActivitat.nomActividades());
        String activitat = teclat.nextLine();

        Usuari nouUsuari = null;
        Actividad novaActividades = null;

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
        System.out.println("Introduiu el nom del usuari: " + llistaUsuari.nomUsuarios());
        String nom = teclat.nextLine();
        Usuari usu = null;
        try {
            usu = llistaUsuari.buscarUsuari(nom);
        } catch (UsuariNoTrobatException e) {
            System.out.println("No s'ha trobat el nom del usuari " + e);
        }
        ListaActividad llistaActitatsUsu = llistaInscripcions.ActivitatsUsuari(usu);
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
        System.out.println("Introdueix el nom de l'usuari: " + llistaUsuari.nomUsuarios());
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
     * Método que devuelve la información de una actividad en concreto Autor
     * Laura Lope
     *
     * @param llistaActividades
     * @param Actividad
     */
    private static void opcio7(ListaActividad llistaActividades) {
        System.out.println("Introduce el nombre de la actividad : " + llistaActividades.nomActividades());
        String actividad = teclat.nextLine();

        try {
            System.out.println(llistaActividades.buscarActividades(actividad));
        } catch (ActivitatNoTrobadaException e) {
            System.err.println("No se ha encontrado una actividad con ese nombre");
        }
    }

    /**
     * Método que indica las actividades con plazas disponibles Autor Laura Lope
     *
     * @param llistaActividades
     * @param llistaInscripcions
     */
    private static void opcio6(ListaActividad llistaActividades, LlistaInscripcions llistaInscripcions) {
        try {
            ListaActividad actConPlazas = llistaActividades.actPlazasDisponibles(llistaInscripcions);
            if (actConPlazas.getNumElems() == 0) {
                System.out.println("No hay actividades con plazas disponnibles");
            } else {
                System.out.println(actConPlazas.toString());
            }
        } catch (NomActivitatDuplicatException e) {
            System.err.println("Está actividad ya está añadida");
        } catch (ActivitatNoTrobadaException e) {
            System.err.println("No se ha encontrado una actividad con ese nombre");
        } catch(NoHayEspacioException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Autor Laura Lope
     *
     * @param llistaActividades
     */
    private static void opcio5(ListaActividad llistaActividades, Data avui) {
        System.out.println(llistaActividades.actActivas(avui));
    }

    /**
     * Método que comprueba que actividades tiene clase hoy y si hay plazas y
     * lista de espera Autor Laura Lope
     *
     * @param llistaActividades
     * @param llistaInscripcions
     */
    private static void opcio4(ListaActividad llistaActividades, LlistaInscripcions llistaInscripcions, Data avui) {

        try {
            llistaActividades.llistaClasseAvui(avui, llistaInscripcions);
        } catch (NomActivitatDuplicatException e) {
            System.err.println("Está actividad ya está añadida");
        } catch(NoHayEspacioException e){
            System.err.println(e.getMessage());
        }
    }

    /**
     * Autor Laura Lope
     *
     * @param llistaActividades
     * @param avui
     */
    private static void opcio3(ListaActividad llistaActividades, Data avui) {
        try {
            System.out.println(llistaActividades.llistaPeriodo(avui).toString());
        } catch (NomActivitatDuplicatException e) {
            System.err.println("Está actividad ya está añadida");
        } catch (NoHayEspacioException e){
            System.err.println(e.getMessage());
        }
    }

    /**
     * Autor Jordi
     *
     * @param llistaUsuari
     * @param llistaActividades
     * @param llistaInscripcions
     */
    private static void opcio2(LlistaUsuari llistaUsuari, ListaActividad llistaActividades,
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

                            System.err.println("Quin tipus especific de la llista d'activitats vols mostrar:");
                            System.out.println("1. Mostrar llista de activitats de un dia específic");
                            System.out.println("2. Mostrar llista de activitats online");
                            System.out.println("3. Mostrar llista de activitats periodiques");
                            opcio = Integer.parseInt(teclat.nextLine());
                            ListaActividad llistaEsp = llistaActividades.llistaTipus(opcio);
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
            System.err.println(e + " Es necessari introduir un numero.");
        } catch (NoHayEspacioException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Autor Izan
     *
     * @param avui
     */
    private static void opcio1(Data avui) {
        System.out.println("La data d'avui es" + avui);
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
