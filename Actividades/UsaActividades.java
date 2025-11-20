package Actividades;

//Clase que comprueba que lo que hago funciona

public class UsaActividades {
    public static void main(String[] args) {
        Actividades a = new Actividades();
        String b = a.getNom();
        String c = a.getPeriodoIn();
        String d = a.getPeriodoFin();
        String e = a.getColectivos();
        System.out.println("Las incripciones de la actividad " + b + " empiezan el día " + c + " y terminan el día " + d
                + " los colectivos que pueden asistir son " + e + ".");
        System.out.println();
        
        a.setNom("'Conferencia'");
        String f = a.getNom();
        a.setPeriodoIn(4,10,2025);
        String g = a.getPeriodoIn();
        a.setPeriodoFin(4,11,2025);
        String h = a.getPeriodoFin();
        a.setColectivos(new String[]{"PDI", "Estudiants"});
        String i = a.getColectivos();
        System.out.println("Las incripciones de la actividad " + f + " empiezan el día " + g + " y terminan el día " + h
                + " los colectivos que pueden asistir son " + i + ".");
        System.out.println();
        
        System.out.println(a);
    }
}
