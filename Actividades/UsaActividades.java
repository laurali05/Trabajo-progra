package Actividades;

//Clase que comprueba que lo que hago funciona

public class UsaActividades {
    public static void main(String[] args) {
        Actividades a = new Actividades();
        System.out.println(a);
        System.out.println();

        a.setNom("'Conferencia'");
        a.setPeriodoIn(4,10,2025);
        a.setPeriodoFin(4,11,2025);
        a.setColectivos(new String[]{"PDI", "Estudiants"});
        System.out.println(a);
    }
}
