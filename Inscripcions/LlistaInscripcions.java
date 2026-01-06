package Inscripcions;

import Actividades.Actividades;
import Actividades.ActividadesDia;
import Actividades.ActividadesOnline;
import Actividades.ActividadesPeriodicas;
import Actividades.ListaActividades;
import Data.*;
import Excepcions.ActivitatNoTrobadaException;
import Excepcions.NoHayEspacioException;
import Excepcions.NomActivitatDuplicatException;
import Excepcions.UsuariJaInscritException;
import Excepcions.UsuariNoTrobatException;
import Usuari.LlistaUsuari;
import Usuari.Usuari;
import Usuari.UsuariEstudiant;
import Usuari.UsuariPDI;
import Usuari.UsuariPTGAS;

public class LlistaInscripcions {

    private static int limit;
    private int numElem;
    private Inscripcions[] llista;
    private LlistaEspera llistaEspera;

    /**
     * Constructor de la llista d'inscripcions
     *
     * @param mida tamany de la llista
     */
    public LlistaInscripcions(int mida) {
        llista = new Inscripcions[mida];
        limit = mida;
        numElem = 0;
    }

    /**
     * Métode cópia de la llista
     *
     * @return llista copiada
     */
    public LlistaInscripcions copia() {
        LlistaInscripcions novaLlista = new LlistaInscripcions(numElem);
        for (int i = 0; i < numElem; i++) {
            try {
                novaLlista.afegir(llista[i]);
            } catch (UsuariJaInscritException e) {
                System.err.println("L'usuari" + e + "ja esta inscrit");
            }
        }
        return novaLlista;
    }

    /**
     * Métode per afegir noves inscripcions a la llista
     *
     * @param ins inscripcio a afegir
     */
    public void afegir(Inscripcions ins) throws UsuariJaInscritException {
        int i = 0;
        boolean trobat = false;
        while (!trobat && i < numElem) {
            if (llista[i].getUsuari().esIgual(ins.getUsuari()) && llista[i].getActivitat().iguals(ins.getActivitat())) {
                trobat = true;
            } else {
                i++;
            }
        }

        if (!trobat && numElem < limit && ins.getActivitat() instanceof ActividadesOnline) {
            llista[numElem] = ins.copia();
            numElem++;
        } else if (!trobat && numElem < limit && (ins.getActivitat() instanceof ActividadesDia
                || ins.getActivitat() instanceof ActividadesPeriodicas)) {
            if (ins.getActivitat() instanceof ActividadesDia) {
                ActividadesDia act = (ActividadesDia) ins.getActivitat();
                if (this.contarInscritos(act.getNom()) < act.getLimite()) {
                    llista[numElem] = ins.copia();
                    numElem++;
                } else {
                    llistaEspera.afegir(ins);
                    System.out.println("S'ha afegit a la llista d'espera perque la activitat esta plena");
                }
            } else if (ins.getActivitat() instanceof ActividadesPeriodicas) {
                ActividadesPeriodicas act = (ActividadesPeriodicas) ins.getActivitat();
                if (this.contarInscritos(act.getNom()) < act.getLimite()) {
                    llista[numElem] = ins.copia();
                    numElem++;
                } else {
                    llistaEspera.afegir(ins);
                    System.out.println("S'ha afegit a la llista d'espera perque la activitat esta plena");
                }
            }
        } else if (trobat) {
            throw new UsuariJaInscritException(ins.getUsuari().getNom());
        }
    }

    // Métode toString
    @Override
    public String toString() {
        String text = "Llista Inscripcions -> Inscripcions a la llista:" + numElem + "\n";
        for (int i = 0; i < numElem; i++) {
            text = text + "\nInscripcio " + (i + 1) + ": \n" + llista[i];
        }
        return text;
    }

    /**
     * Metode que retorna un llista d'activitats d'un usuari concret (Jordi)
     *
     * @param nom
     * @return
     */
    public ListaActividades ActivitatsUsuari(Usuari nom) {
        ListaActividades aux = new ListaActividades(limit);
        try {
            for (int i = 0; i < numElem; i++) {

                if (llista[i].getUsuari().esIgual(nom)) {

                    aux.afegir(llista[i].getActivitat());
                }
            }
        } catch (NomActivitatDuplicatException e) {
            System.out.println(e + " Duplicat");
        } catch (NoHayEspacioException e) {
            System.err.println(e.getMessage());
        }
        return aux;
    }

    /**
     * Método que devuelve el número de personas inscritas Izan no te asustes lo
     * necesito para el método 4 y 6 Autor Laura Lope
     *
     * @param nomActividad
     * @return
     */
    public int contarInscritos(String nomActividad) {
        int contador = 0;

        for (int i = 0; i < numElem; i++) {
            if (llista[i].getActivitat().getNom().equals(nomActividad)) {
                contador++;
            }
        }

        return contador;
    }

    /**
     * Método que indica si una actividad tiene lista de espera o no Autor Laura
     * Lope
     *
     * @param act
     * @return
     */
    public boolean teLlistaEspera(Actividades act) {
        boolean resultado = false;

        if (act instanceof ActividadesDia) {
            ActividadesDia dia = (ActividadesDia) act;
            if (dia.getLimite() <= this.contarInscritos(dia.getNom())) {
                resultado = true;
            }
        } else if (act instanceof ActividadesPeriodicas) {
            ActividadesPeriodicas periodica = (ActividadesPeriodicas) act;
            if (periodica.getLimite() <= this.contarInscritos(periodica.getNom())) {
                resultado = true;
            }
        } else if (act instanceof ActividadesOnline) {
            ActividadesOnline online = (ActividadesOnline) act;
            if (20 <= this.contarInscritos(online.getNom())) {
                resultado = true;
            }
        }
        return resultado;
    }

    /**
     * Metode necesari per la opcio 12 (Jordi)
     *
     * @param usuari
     * @param act
     */
    public void eliminarInscripcio(Usuari usuari, Actividades act, LlistaEspera llistaEspera) {
        boolean trobat = false;
        int i = 0;
        Inscripcions insEspera = null;
        while (!trobat && i < numElem) {
            if (llista[i].getUsuari().equals(usuari) && llista[i].getActivitat().equals(act)) {
                for (int j = i; j < numElem - 1; j++) {
                    llista[j] = llista[j + 1];
                }
                numElem--;
                trobat = true;
            } else {
                i++;
            }
        }
        if (!trobat) {
            System.out.println("L'usuari no està apuntat en aquesta activitat");
        } else {

            System.out.println("L'usuari ha estat eliminat de la activitat correctament");
            insEspera = llistaEspera.buscarPrimerEspera(act);
            try {
                if (insEspera != null) {
                    this.afegir(insEspera);
                }
            } catch (UsuariJaInscritException e) {
                System.err.println(e + ": Usuari ja inscrit");
            }
        }
    }

    /**
     * Metode que compara cuantes activitats fa cada usuari d'un tipus especific
     * i retorna el mes actiu (Jordi)
     *
     * @param opcio
     * @param llistaUsuari
     * @return
     */
    public Usuari UsuariMesActiu(int opcio, LlistaUsuari llistaUsuari) {
        int numActivitats = 0;
        int max = 0;
        Usuari auxUsuari = null;
        int pos = 0;
        boolean actiu = false;
        if (opcio == 1) {
            for (int i = 0; i < llistaUsuari.getNumElems(); i++) {

                auxUsuari = llistaUsuari.buscarUsuariPerPos(i);
                if (auxUsuari instanceof UsuariEstudiant) {
                    numActivitats = this.ActivitatsUsuari(auxUsuari).getNumElems();

                    if (numActivitats > max) {

                        actiu = true;
                        pos = i;
                    }
                }
            }
            if (actiu == false) {

                System.out.println("No hi ha ningun usuari amb cap activitat");
                return null;
            } else {
                return llistaUsuari.buscarUsuariPerPos(pos);
            }
        }
        if (opcio == 2) {
            for (int i = 0; i < llistaUsuari.getNumElems(); i++) {

                auxUsuari = llistaUsuari.buscarUsuariPerPos(i);
                if (auxUsuari instanceof UsuariPDI) {
                    numActivitats = this.ActivitatsUsuari(auxUsuari).getNumElems();

                    if (numActivitats > max) {
                        actiu = true;
                        pos = i;
                    }
                }
            }
            if (actiu == false) {
                System.out.println("No hi ha ningun usuari amb cap activitat");
                return null;
            } else {
                return llistaUsuari.buscarUsuariPerPos(pos);
            }
        }
        if (opcio == 3) {
            for (int i = 0; i < llistaUsuari.getNumElems(); i++) {

                auxUsuari = llistaUsuari.buscarUsuariPerPos(i);
                if (auxUsuari instanceof UsuariPTGAS) {
                    numActivitats = this.ActivitatsUsuari(auxUsuari).getNumElems();

                    if (numActivitats > max) {
                        actiu = true;
                        pos = i;
                    }
                }
            }
            if (actiu == false) {
                System.out.println("No hi ha ningun usuari amb cap activitat");
                return null;
            } else {
                return llistaUsuari.buscarUsuariPerPos(pos);
            }
        } else {
            System.err.println("Opció seleccionada incorrecta");
            return null;
        }

    }

    /**
     * Métode que asigna una llista d'espera
     * @param llistaEspera
     */
    public void assignarLlistaEspera(LlistaEspera llistaEspera) {
        this.llistaEspera = llistaEspera;
    }

    /**
     * Métode que serveix per veure els usuaris apuntats a una activitat
     * @param act
     * @return llista d'usuaris apuntats
     */
    public LlistaUsuari usuarisApuntats(Actividades act) {
        LlistaUsuari aux = new LlistaUsuari(100);
        for (int i = 0; i < numElem; i++) {
            if (llista[i].getActivitat().iguals(act)) {
                aux.afegir(llista[i].getUsuari());
            }
        }
        return aux;
    }

    /**
     * Métode per valorar una activitat
     * @param llistaActivitat
     * @param llistaUsuaris
     * @param act
     * @param nom
     * @param avui
     * @param valoracio
     */
    public void valorarActivitat(ListaActividades llistaActivitat, LlistaUsuari llistaUsuaris, String act, String nom, Data avui, int valoracio) {
        int i = 0;
        int indexVal=0;
        boolean trobat = false;
        try {
            while (!trobat && i < numElem) {

                if (llista[i].getUsuari().esIgual(llistaUsuaris.buscarUsuari(nom))) {
                    trobat = true;
                } else {
                    i++;
                }
            }
        } catch (UsuariNoTrobatException e) {
            System.err.println("l'usuari " + e + "no s'ha trobat.");
        }
        Data fechaFin = new Data();
        if (llista[i].getActivitat() instanceof ActividadesDia) {
            ActividadesDia dia = (ActividadesDia) llista[i].getActivitat();
            fechaFin = dia.getFechaIn();
        } else if (llista[i].getActivitat() instanceof ActividadesPeriodicas) {
            ActividadesPeriodicas periodica = (ActividadesPeriodicas) llista[i].getActivitat();
            fechaFin = periodica.getFechaIn().sumarDias(periodica.getNumSemanas() * 7);
        } else if (llista[i].getActivitat() instanceof ActividadesOnline) {
            ActividadesOnline online = (ActividadesOnline) llista[i].getActivitat();
            fechaFin = online.getFechaIn().sumarDias(online.getPeriodoVis());
        }
        indexVal = posicioValoracio(llista[i], llistaActivitat, act);
        if (fechaFin.esDataInferiorOigual(avui)) {
            llista[indexVal].setValoracio(valoracio);
            System.out.println("S'ha guardat la valoració");
        } else {
            System.out.println("L'activitat encara no ha finalitzat.");
        }
    }

    /**
     * Métode que mostra les valoracions
     * @return
     */
    public String mostrarValoracions() {
        String llistaval = "Llista de Valoracions: \n";
        for (int i = 0; i < numElem; i++) {
            if (llista[i].getValoracio() != 0) {
                llistaval = llistaval + "\tNom Activitat: " + llista[i].getActivitat().getNom() + " Valoracio: " + llista[i].getValoracio() + "\n";
            }
        }
        return llistaval;
    }

    /**
     * Métode que mostra les valoracions realitzades per l'usuari
     * @param nom
     * @param llistaUsuari
     * @return
     */
    public String valoracionsUsuari(String nom, LlistaUsuari llistaUsuari) {
        int i = 0;
        boolean trobat = false;
        String llistaval = "Llista valoracions de " + nom + "\n";
        try {
            while (!trobat && i < numElem) {

                if (llista[i].getUsuari().esIgual(llistaUsuari.buscarUsuari(nom))) {
                    trobat = true;
                } else {
                    i++;
                }
            }
            if (!trobat) {
                return "L'usuari " + nom + " no té cap inscripció feta.";
            }
        } catch (UsuariNoTrobatException e) {
            System.err.println("L'usuari " + nom + " no existeix a la llista d'usuaris.");
        }
        Usuari usu = llista[i].getUsuari();

        for (int j = 0; j < numElem; j++) {
            if (llista[j].getUsuari().esIgual(usu)) {
                llistaval = llistaval + "\tNom Activitat: " + llista[j].getActivitat().getNom() + " Valoracio: " + llista[j].getValoracio() + "\n";
            }
        }
        return llistaval;
    }

    /**
     * Metode que calcula i mostra per pantalla la mitjana dels col·lectius
     * @param llistaActivitat
     */
    public void mitjanaColectius(ListaActividades llistaActivitat) {
        int[] dia = new int[numElem];
        int[] periodicas = new int[numElem];
        int[] online = new int[numElem];
        int x = 0;
        int y = 0;
        int z = 0;
        int valdies = 0;
        int valperi = 0;
        int valon = 0;
        int acumdia;
        int acumperi;
        int acumon;
        double mitjdia;
        double mitjperi;
        double mitjon;
        for (int i = 0; i < numElem; i++) {
            if (llista[i].getActivitat() instanceof ActividadesDia) {
                dia[x] = llista[i].getValoracio();
                x++;
            } else if (llista[i].getActivitat() instanceof ActividadesPeriodicas) {
                periodicas[y] = llista[i].getValoracio();
                y++;
            } else if (llista[i].getActivitat() instanceof ActividadesOnline) {
                online[z] = llista[i].getValoracio();
                z++;
            }
        }
        acumdia = x;
        acumperi = y;
        acumon = z;
        for (x = 0; x < acumdia; x++) {
            valdies = valdies + dia[x];
        }
        for (y = 0; y < acumperi; y++) {
            valperi = valperi + periodicas[y];
        }
        for (z = 0; z < acumon; z++) {
            valon = valon + online[z];
        }
        if (acumdia > 0) {
            mitjdia = (double) valdies / acumdia;
        } else {
            mitjdia = 0.0;
        }
        if (acumperi > 0) {
            mitjperi = (double) valperi / acumperi;
        } else {
            mitjperi = 0.0;
        }
        if (acumon > 0) {
            mitjon = (double) valon / acumon;
        } else {
            mitjon = 0.0;
        }
        System.out.println("Les mitjanes de valoracio dels diferents colectius son: \n \tActivitats dia: " + mitjdia + "\n \tActivitats Periodiques: " + mitjperi + "\n \tActivitats Online: " + mitjon);
    }

    /**
     * Métode que diu la posicio on s'asigna la valoració
     * @param ins
     * @param llistaActivitat
     * @param act
     * @return
     */
    public int posicioValoracio(Inscripcions ins, ListaActividades llistaActivitat, String act){
        int aux=0;
        boolean trobat = false;
        int i=0;
        while(i<numElem && !trobat){
            try {
                if(llistaActivitat.buscarActividades(act).iguals(llista[i].getActivitat())){
                    aux = i;
                    trobat = true;
                } else {
                    i++;
                }
            } catch (ActivitatNoTrobadaException e) {
                System.err.println("L'activitat "+ e + "no s'ha trobat.");
            }
        }
        return aux;
    }
}
