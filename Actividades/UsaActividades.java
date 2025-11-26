package Actividades;

//Clase que comprueba que lo que hago funciona

public class UsaActividades {
    public static void main(String[] args) {
        System.out.println("------ PRUEBAS CLASE ACTIVIDADES ------");
        System.out.println();

        System.out.println("------ Comprueba el constructor sin parámetros, el toString y los getters ------");
        System.out.println();
        Actividades a = new Actividades();
        System.out.println(a);
        System.out.println();

        System.out.println("------ Comprueba los setters ------");
        System.out.println();
        a.setNom("Conferencia");
        a.setPeriodoIn(4,10,2025);
        a.setPeriodoFin(4,11,2025);
        a.setColectivos(true,false,true);
        System.out.println(a);
        System.out.println();

        System.out.println("------ Comprueba el método copia ------");
        System.out.println();
        Actividades b = a.copia();
        System.out.println(b);
        System.out.println();

        System.out.println("------ Comprueba el comprueba el método copia y otra opción del setter colectivos ------");
        System.out.println();
        Actividades c = a.copia();
        c.setColectivos(false,false,true);
        System.out.println(c);
        System.out.println();

        System.out.println("------ Comprueba el constructor con parámetros ------");
        System.out.println();
        Actividades d = new Actividades("Curso de especialización", new int[]{14,10,2025}, new int[]{31,10,2025}, new String[]{"PTGAS"});
        System.out.println(d);
        System.out.println();

        System.out.println("------ Comprueba el método iguales ------");
        System.out.println();
        System.out.println("Son la actividad a y la b iguales? "+ a.iguals(b));
        System.out.println();

        System.out.println("Son la actividad c y la d iguales? "+ c.iguals(d));
        System.err.println();
    }
}
