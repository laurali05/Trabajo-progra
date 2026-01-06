package Pruebas;

import Actividades.*;
import Data.Data;
import Excepcions.FechaIncorrectaExcepcion;

/**
 * Clase: UsaActividades
 * Autor: Laura Lope
 */
public class UsaActividades {
        public static void main(String[] args) throws FechaIncorrectaExcepcion {
                System.out.println("------ PRUEBAS CLASE ACTIVIDADES ------");
                System.out.println();

                comprobarConstructorToStringActividades();
                compruebaSettersGettersActividades();
                compruebaCopiaEIgualsActividades();

                System.out.println("------ PRUEBAS CLASE ACTIVIDADES DIA ------");
                System.out.println();

                comprobarConstructorToStringActividadesDia();
                compruebaSettersGettersActividadesDia();
                compruebaCopiaEIgualsActividadesDia();

                System.out.println("------ PRUEBAS CLASE ACTIVIDADES ONLINE ------");
                System.out.println();

                comprobarConstructorToStringActividadesOnline();
                compruebaSettersGettersActividadesOnline();
                compruebaCopiaEIgualsActividadesOnline();

                System.out.println("------ PRUEBAS CLASE ACTIVIDADES PERIÓDICAS ------");
                System.out.println();

                comprobarConstructorToStringActividadesPeriodicas();
                compruebaSettersGettersActividadesPeriodicas();
                compruebaCopiaEIgualsActividadesPeriodicas();
        }

        private static void comprobarConstructorToStringActividades() throws FechaIncorrectaExcepcion {
                System.out.println("------ COMPRUEBA EL CONSTRUCTOR Y EL TO STRING ------");
                System.out.println("------ Comprueba el constructor sin parámetros y el toString ------");
                System.out.println();

                Actividades a = new Actividades();
                System.out.println(a);
                System.out.println();

                System.out.println("------ Comprueba el constructor con parámetros con fechas correctas ------");
                System.out.println();

                Actividades b = new Actividades("Dia", "Curso de especialización", new Data(4, 10, 2025),
                                new Data(31, 10, 2025),
                                new String("PDI, Estudiants"));
                System.out.println(b);
                System.out.println();

                System.out.println("------ Comprueba el constructor con parámetros con fecha incorrecta ------");
                System.out.println();

                Actividades c = new Actividades("Dia","Curso de especialización", new Data(1, 6, 2025),
                                new Data(31, 10, 2025),
                                new String("PTGAS"));
                System.out.println(c);
                System.out.println();
        }

        private static void compruebaSettersGettersActividades() throws FechaIncorrectaExcepcion {
                System.out.println("------ COMPRUEBA LOS SETTERS Y LOS GETTERS ------");
                System.out.println();

                Actividades a = new Actividades();
                System.out.println("------ COMPRUEBA EL NOMBRE ------");
                a.setNom("Conferencia");
                System.out.println("El nombre de la actividad es " + a.getNom());
                System.out.println();

                System.out.println("------ COMPRUEBA EL INICIO DE LAS INCRIPCIONES ------");
                System.out.println("------ Fecha correcta en el límite por abajo  ------");
                a.setPeriodoIn(new Data(4, 9, 2025));
                System.out.println(
                                "La fecha en la que se inicia el periodo de inscripción de la actividad es: "
                                                + a.getPeriodoIn());
                System.out.println();

                System.out.println("------ Fecha incorrecta ------");
                a.setPeriodoIn(new Data(4, 4, 2025));
                System.out.println(
                                "La fecha en la que se inicia el periodo de inscripción de la actividad es: "
                                                + a.getPeriodoIn());
                System.out.println();

                System.out.println("------ COMPRUEBA EL FINAL DE LAS INSCRIPCIONES ------");
                System.out.println("------ Fecha correcta en el límite por arriba ------");
                a.setPeriodoFin(new Data(4, 12, 2025));
                System.out.println(
                                "La fecha en la que finaliza el periodo de inscripción de la actividad es: "
                                                + a.getPeriodoFin());
                System.out.println();

                System.out.println("------ Fecha incorrecta ------");
                a.setPeriodoFin(new Data(4, 2, 2025));
                System.out.println(
                                "La fecha en la que finaliza el periodo de inscripción de la actividad es: "
                                                + a.getPeriodoFin());
                System.out.println();

                System.out.println("------ COMPRUEBA LOS COLECTIVOS ------");
                System.out.println("------ Todos los colectivos ------");
                a.setColectivos("PDI, PTGA,Estudiants");
                System.out.println("Los colectivos que pueden participar en la actividad son: " + a.getColectivos());
                System.out.println();

                System.out.println("------ Solo PDI ------");
                a.setColectivos("PDI");
                System.out.println("Los colectivos que pueden participar en la actividad son: " + a.getColectivos());
                System.out.println();

                System.out.println("------ PDI y PTGAS ------");
                a.setColectivos("PDI, PTGAS");
                System.out.println("Los colectivos que pueden participar en la actividad son: " + a.getColectivos());
                System.out.println();

                System.out.println("------ PDI y Estudiants ------");
                a.setColectivos("PDI, Estudiants");
                System.out.println("Los colectivos que pueden participar en la actividad son: " + a.getColectivos());
                System.out.println();

                System.out.println("------ Solo PTGAS ------");
                a.setColectivos("PTGAS");
                System.out.println("Los colectivos que pueden participar en la actividad son: " + a.getColectivos());
                System.out.println();

                System.out.println("------ PTGAS y Estudiants ------");
                a.setColectivos("PTGAS, Estudiants");
                System.out.println("Los colectivos que pueden participar en la actividad son: " + a.getColectivos());
                System.out.println();

                System.out.println("------ Solo Estudiants ------");
                a.setColectivos("Estudiants");
                System.out.println("Los colectivos que pueden participar en la actividad son: " + a.getColectivos());
                System.out.println();

                System.out.println("------ Ninguno de los colectivos ------");
                a.setColectivos(" ");
                System.out.println("Los colectivos que pueden participar en la actividad son: " + a.getColectivos());
                System.out.println();

        }

        private static void compruebaCopiaEIgualsActividades() throws FechaIncorrectaExcepcion {
                System.out.println("------ COMPRUEBA COPIA ------");
                Actividades a = new Actividades();
                Actividades b = a.copia();
                System.out.println(b);
                System.out.println();

                System.out.println("------ COMPRUEBA IGUALES ------");
                System.out.println("------ Comprueba cuando las dos actividades son iguales ------");
                System.out.println("Son la actividad a y la b iguales? " + a.iguals(b));
                System.out.println();

                System.out.println("------ Comprueba si son iguales después de cambiar el nombre ------");
                b.setNom("Pataquers");
                System.out.println("Son la actividad a y la b iguales? " + a.iguals(b));
                System.out.println();

                System.out.println(
                                "------ Comprueba si son iguales después de cambiar la fecha de inicio de inscripciones ------");
                b.setPeriodoIn(new Data(12, 9, 2025));
                System.out.println("Son la actividad a y la b iguales? " + a.iguals(b));
                System.out.println();

                System.out.println(
                                "------ Comprueba si son iguales después de cambiar la fecha de finalización de inscripciones ------");
                b.setPeriodoFin(new Data(12, 11, 2025));
                System.out.println("Son la actividad a y la b iguales? " + a.iguals(b));
                System.out.println();

                System.out.println(
                                "------ Comprueba si son iguales después de cambiar los colectivos que pueden asistir ------");
                b.setColectivos("PDI");
                System.out.println("Son la actividad a y la b iguales? " + a.iguals(b));
                System.out.println();
        }

        private static void comprobarConstructorToStringActividadesDia() throws FechaIncorrectaExcepcion {
                System.out.println("------ COMPRUEBA EL CONSTRUCTOR Y EL TO STRING ------");
                System.out.println("------ Comprueba el constructor y el toString (todo correcto) ------");
                System.out.println();

                ActividadesDia a = new ActividadesDia("Dia","Premios", new Data(29, 9, 2925), new Data(5, 10, 2025),
                                "Estudiants" , new Data(6, 10, 2025), new int[] { 18, 00 }, 20, 0,
                                "Tarragona");
                System.out.println(a);
                System.out.println();

                System.out.println(
                                "------ Comprueba el constructor con parámetros con fecha y horario incorrecto ------");
                System.out.println();
                ActividadesDia b = new ActividadesDia("Dia","Premios", new Data(29, 9, 2925), new Data(5, 10, 2025),
                                "Estudiants" , new Data(6, 3, 2025), new int[] { 24, 60 }, 20, 0,
                                "Tarragona");
                System.out.println(b);
                System.out.println();

        }

        private static void compruebaSettersGettersActividadesDia() throws FechaIncorrectaExcepcion {
                System.out.println("------ COMPRUEBA LOS SETTERS Y LOS GETTERS ------");
                System.out.println();

                ActividadesDia a = new ActividadesDia("Dia", "Premios", new Data(29, 9, 2925), new Data(5, 10, 2025),
                                "Estudiants" , new Data(6, 10, 2025), new int[] { 18, 00 }, 20, 0,
                                "Tarragona");
                System.out.println("------ COMPRUEBA LA FECHA ------");
                System.out.println("------ Fecha correcta ------");
                a.setFecha(new Data(4, 11, 2025));
                System.out.println("La fecha de la actividad es " + a.getFechaIn());
                System.out.println();

                System.out.println("------ Fecha incorrecta ------");
                a.setFecha(new Data(4, 3, 2025));
                System.out.println("La fecha de la actividad es " + a.getFechaIn());
                System.out.println();

                System.out.println("------ COMPRUEBA EL HORARIO DE LA ACTIVIDAD ------");
                System.out.println("------ Horario correcto ------");
                a.setHorario(19, 30);
                System.out.println("El horario de la actividad es: " + a.getHorario());
                System.out.println();

                System.out.println("------ Horario incorrecto horas ------");
                a.setHorario(24, 30);
                System.out.println("El horario de la actividad es: " + a.getHorario());
                System.out.println();

                System.out.println("------ Horario incorrecto minutos ------");
                a.setHorario(15, 60);
                System.out.println("El horario de la actividad es: " + a.getHorario());
                System.out.println();

                System.out.println("------ COMPRUEBA LÍMITE DE INSCRIPCIONES ------");
                System.out.println("------ Límite de inscripciones ------");
                a.setLimite(50);
                System.out.println("El límite de inscripciones de la actividad es: " + a.getLimite());
                System.out.println();

                System.out.println("------ COMPRUEBA EL PRECIO ------");
                System.out.println("------ Precio de la actividad ------");
                a.setPrecio(40.0);
                System.out.println(
                                "El precio de la actividad es: " + a.getPrecio());
                System.out.println();

                System.out.println("------ COMPRUEBA LA CIUDAD ------");
                System.out.println("------ Ciudad de la actividad ------");
                a.setCiudad("Reus");
                System.out.println(
                                "La ciudad donde se realiza la actividad es: " + a.getCiudad());
                System.out.println();
        }

        private static void compruebaCopiaEIgualsActividadesDia() throws FechaIncorrectaExcepcion {
                System.out.println("------ COMPRUEBA COPIA ------");
                ActividadesDia a = new ActividadesDia("Dia", "Premios", new Data(29, 9, 2925), new Data(5, 10, 2025),
                                "Estudiants" , new Data(6, 10, 2025), new int[] { 18, 00 }, 20, 0,
                                "Tarragona");
                ActividadesDia b = a.copia();
                System.out.println(b);
                System.out.println();

                System.out.println("------ COMPRUEBA IGUALES ------");

                System.out.println("------ Comprueba cuando las dos actividades son iguales ------");
                System.out.println("Son la actividad a y la b iguales? " + a.iguals(b));
                System.out.println();
                System.out.println("------ Comprueba si son iguales después de cambiar la fecha ------");
                b.setFecha(new Data(15, 11, 2025));
                System.out.println("Son la actividad a y la b iguales? " + a.iguals(b));
                System.out.println();

                System.out.println("------ Comprueba si son iguales después de cambiar el horario ------");
                b.setHorario(17, 45);
                System.out.println("Son la actividad a y la b iguales? " + a.iguals(b));
                System.out.println();

                System.out.println("------ Comprueba si son iguales después de cambiar el límte ------");
                b.setLimite(40);
                System.out.println("Son la actividad a y la b iguales? " + a.iguals(b));
                System.out.println();

                System.out.println("------ Comprueba si son iguales después de cambiar el precio ------");
                b.setPrecio(5.5);
                System.out.println("Son la actividad a y la b iguales? " + a.iguals(b));
                System.out.println();

                System.out.println("------ Comprueba si son iguales después de cambiar la ciudad ------");
                b.setCiudad("Torredembarra");
                System.out.println("Son la actividad a y la b iguales? " + a.iguals(b));
                System.out.println();
        }

        private static void comprobarConstructorToStringActividadesOnline() throws FechaIncorrectaExcepcion {
                System.out.println("------ COMPRUEBA EL CONSTRUCTOR Y EL TO STRING ------");
                System.out.println("------ Comprueba el constructor y el toString (todo correcto) ------");
                System.out.println();

                ActividadesOnline a = new ActividadesOnline("Online", "Premios", new Data(29, 9, 2925), new Data(5, 10, 2025),
                                "Estudiants" , new Data(6, 10, 2025), 7,
                                "https://www.universitat.cat/actividades/java2025");
                System.out.println(a);
                System.out.println();

                System.out.println("------ Comprueba el constructor con parámetros con fecha incorrecta ------");
                System.out.println();
                ActividadesOnline b = new ActividadesOnline("Online", "Premios", new Data(29, 9, 2925), new Data(5, 10, 2025),
                                "Estudiants" , new Data(6, 3, 2025), 7,
                                "https://www.universitat.cat/actividades/java2025");
                System.out.println(b);
                System.out.println();

        }

        private static void compruebaSettersGettersActividadesOnline() throws FechaIncorrectaExcepcion {
                System.out.println("------ COMPRUEBA LOS SETTERS Y LOS GETTERS ------");
                System.out.println();

                ActividadesOnline a = new ActividadesOnline("Online","Premios", new Data(29, 9, 2925), new Data(5, 10, 2025),
                                 "Estudiants" , new Data(6, 10, 2025), 7,
                                "https://www.universitat.cat/actividades/java2025");
                System.out.println("------ COMPRUEBA LA FECHA ------");
                System.out.println("------ Fecha de inicio correcta ------");
                a.setFechaIn(new Data(3, 12, 2025));
                System.out.println("La fecha de inicio de la actividad es " + a.getFechaIn());
                System.out.println();

                System.out.println("------ Fecha de inicio incorrecta ------");
                a.setFechaIn(new Data(4, 1, 2025));
                System.out.println("La fecha de inicio de la actividad es " + a.getFechaIn());
                System.out.println();

                System.out.println("------ COMPRUEBA EL PERIODO DE VISUALIZACIÓN DE LA ACTIVIDAD ------");
                a.setPeriodoVis(15);
                System.out.println("El periodo de visualización de la actividad es: " + a.getPeriodoVis());
                System.out.println();

                System.out.println("------ COMPRUEBA EL ENLACE DE LA ACTIVIDAD ------");
                a.setEnlace("https://www.universitat.cat/actividades/java2024");
                System.out.println("El límite de inscripciones de la actividad es: " + a.getEnlace());
                System.out.println();
        }

        private static void compruebaCopiaEIgualsActividadesOnline() throws FechaIncorrectaExcepcion {
                System.out.println("------ COMPRUEBA COPIA ------");
                ActividadesOnline a = new ActividadesOnline("Online", "Premios", new Data(29, 9, 2925), new Data(5, 10, 2025),
                                 "Estudiants" , new Data(6, 10, 2025), 7,
                                "https://www.universitat.cat/actividades/java2025");
                ActividadesOnline b = a.copia();
                System.out.println(b);
                System.out.println();

                System.out.println("------ COMPRUEBA IGUALES ------");

                System.out.println("------ Comprueba cuando las dos actividades son iguales ------");
                System.out.println("Son la actividad a y la b iguales? " + a.iguals(b));
                System.out.println();
                System.out.println("------ Comprueba si son iguales después de cambiar la fecha de inicio ------");
                b.setFechaIn(new Data(18, 12, 2025));
                System.out.println("Son la actividad a y la b iguales? " + a.iguals(b));
                System.out.println();

                System.out.println(
                                "------ Comprueba si son iguales después de cambiar el periodo de visualización ------");
                b.setPeriodoVis(30);
                System.out.println("Son la actividad a y la b iguales? " + a.iguals(b));
                System.out.println();

                System.out.println(
                                "------ Comprueba si son iguales después de cambiar el enlace de la actividad ------");
                b.setEnlace("https://www.universitat.cat/actividadesOnline/java2024");
                System.out.println("Son la actividad a y la b iguales? " + a.iguals(b));
                System.out.println();
        }

        private static void comprobarConstructorToStringActividadesPeriodicas() throws FechaIncorrectaExcepcion {
                System.out.println("------ COMPRUEBA EL CONSTRUCTOR Y EL TO STRING ------");
                System.out.println("------ Comprueba el constructor y el toString (todo correcto) ------");
                System.out.println();

                ActividadesPeriodicas a = new ActividadesPeriodicas("Periodica","Premios", new Data(29, 9, 2925),
                                new Data(5, 10, 2025),
                                 "Estudiants" , "Lunes", new int[] { 18, 00 }, new Data(6, 10, 2025), 4,
                                20, 0, "Sescelades", "Tarragona");
                System.out.println(a);
                System.out.println();

                System.out.println(
                                "------ Comprueba el constructor con parámetros con fecha y horario incorrecto ------");
                System.out.println();
                ActividadesPeriodicas b = new ActividadesPeriodicas("Periodica", "Premios", new Data(29, 9, 2925),
                                new Data(5, 10, 2025),
                                 "Estudiants" , "Lunes", new int[] { 40, 00 }, new Data(6, 5, 2025), 4,
                                20, 0, "Sescelades", "Tarragona");
                System.out.println(b);
                System.out.println();

        }

        private static void compruebaSettersGettersActividadesPeriodicas() throws FechaIncorrectaExcepcion {
                System.out.println("------ COMPRUEBA LOS SETTERS Y LOS GETTERS ------");
                System.out.println();

                ActividadesPeriodicas a = new ActividadesPeriodicas("Periodica", "Premios", new Data(29, 9, 2925),
                                new Data(5, 10, 2025),
                                 "Estudiants" , "Lunes", new int[] { 18, 00 }, new Data(6, 10, 2025), 4,
                                20, 0, "Sescelades", "Tarragona");

                System.out.println("------ COMPRUEBA DIA SEMANA ------");
                System.out.println("------ Día de la semana correcto ------");
                a.setDiaSemana("Lunes");
                System.out.println("El día de la semana que tiene lugar la actividad es: " + a.getDiaSemana());
                System.out.println();

                System.out.println("------ Día de la semana incorrecto ------");
                a.setDiaSemana("Sopa");
                System.out.println("El día de la semana que tiene lugar la actividad es: " + a.getDiaSemana());
                System.out.println();

                System.out.println("------ COMPRUEBA EL HORARIO DE LA ACTIVIDAD ------");
                System.out.println("------ Horario correcto ------");
                a.setHorario(19, 30);
                System.out.println("El horario de la actividad es: " + a.getHorario());
                System.out.println();

                System.out.println("------ Horario incorrecto horas ------");
                a.setHorario(24, 30);
                System.out.println("El horario de la actividad es: " + a.getHorario());
                System.out.println();

                System.out.println("------ Horario incorrecto minutos ------");
                a.setHorario(15, 60);
                System.out.println("El horario de la actividad es: " + a.getHorario());
                System.out.println();

                System.out.println("------ COMPRUEBA LA FECHA ------");
                System.out.println("------ Fecha correcta ------");
                a.setFechaIn(new Data(4, 11, 2025));
                System.out.println("La fecha de la actividad es " + a.getFechaIn());
                System.out.println();

                System.out.println("------ Fecha incorrecta ------");
                a.setFechaIn(new Data(4, 3, 2025));
                System.out.println("La fecha de la actividad es " + a.getFechaIn());
                System.out.println();

                System.out.println("------ COMPRUEBA NÚMERO DE SEMANAS DE DURACIÓN ------");
                a.setNumSemanas(6);
                System.out.println("El límite de inscripciones de la actividad es: " + a.getNumSemanas());
                System.out.println();

                System.out.println("------ COMPRUEBA LÍMITE DE INSCRIPCIONES ------");
                System.out.println("------ Límite de inscripciones ------");
                a.setLimite(50);
                System.out.println("El límite de inscripciones de la actividad es: " + a.getLimite());
                System.out.println();

                System.out.println("------ COMPRUEBA EL PRECIO ------");
                System.out.println("------ Precio de la actividad ------");
                a.setPrecio(40.0);
                System.out.println(
                                "El precio de la actividad es: " + a.getPrecio());
                System.out.println();

                System.out.println("------ COMPRUEBA EL CENTRO ------");
                a.setCentro("Catalunya");
                System.out.println(
                                "El centro donde se realiza la actividad es: " + a.getCentro());
                System.out.println();

                System.out.println("------ COMPRUEBA LA CIUDAD ------");
                a.setCiudad("Reus");
                System.out.println(
                                "La ciudad donde se realiza la actividad es: " + a.getCiudad());
                System.out.println();
        }

        private static void compruebaCopiaEIgualsActividadesPeriodicas() throws FechaIncorrectaExcepcion {
                System.out.println("------ COMPRUEBA COPIA ------");
                ActividadesPeriodicas a = new ActividadesPeriodicas("Periodica", "Premios", new Data(29, 9, 2925),
                                new Data(5, 10, 2025), "Estudiants", "Lunes", new int[] { 18, 00 },
                                new Data(6, 10, 2025), 4,
                                20, 0, "Sescelades", "Tarragona");
                ActividadesPeriodicas b = a.copia();
                System.out.println(b);
                System.out.println();

                System.out.println("------ COMPRUEBA IGUALES ------");

                System.out.println("------ Comprueba cuando las dos actividades son iguales ------");
                System.out.println("Son la actividad a y la b iguales? " + a.iguals(b));
                System.out.println();

                System.out.println("------ Comprueba si son iguales después de cambiar el día de la semana ------");
                b.setDiaSemana("Miércoles");
                System.out.println("Son la actividad a y la b iguales? " + a.iguals(b));
                System.out.println();

                System.out.println("------ Comprueba si son iguales después de cambiar el horario ------");
                b.setHorario(17, 45);
                System.out.println("Son la actividad a y la b iguales? " + a.iguals(b));
                System.out.println();

                System.out.println("------ Comprueba si son iguales después de cambiar la fecha de inicio ------");
                b.setFechaIn(new Data(15, 11, 2025));
                System.out.println("Son la actividad a y la b iguales? " + a.iguals(b));
                System.out.println();

                System.out.println(
                                "------ Comprueba si son iguales después de cambiar el número de semanas de duración ------");
                b.setNumSemanas(8);
                System.out.println("Son la actividad a y la b iguales? " + a.iguals(b));
                System.out.println();

                System.out.println("------ Comprueba si son iguales después de cambiar el límte ------");
                b.setLimite(40);
                System.out.println("Son la actividad a y la b iguales? " + a.iguals(b));
                System.out.println();

                System.out.println("------ Comprueba si son iguales después de cambiar el precio ------");
                b.setPrecio(5.5);
                System.out.println("Son la actividad a y la b iguales? " + a.iguals(b));
                System.out.println();

                System.out.println("------ Comprueba si son iguales después de cambiar el centro ------");
                b.setCentro("Catalunya");
                System.out.println("Son la actividad a y la b iguales? " + a.iguals(b));
                System.out.println();

                System.out.println("------ Comprueba si son iguales después de cambiar la ciudad ------");
                b.setCiudad("Torredembarra");
                System.out.println("Son la actividad a y la b iguales? " + a.iguals(b));
                System.out.println();
        }
}
