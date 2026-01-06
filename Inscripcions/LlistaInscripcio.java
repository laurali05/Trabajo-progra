package Inscripcions;

import Actividades.Actividad;
import Actividades.ActividadDia;
import Actividades.ActividadOnline;
import Actividades.ActividadPeriodicas;
import Actividades.ListaActividad;
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

/**
 * Clase: LlistaInscripcio Autor: Izan Flores
 */
public class LlistaInscripcio {

    private int limit;
    private int numElem;
    private Inscripcio[] llista;
    private LlistaEspera llistaEspera;

    /**
     * Constructor de la llista d'inscripcions
     *
     * @param mida tamany de la llista
     */
    public LlistaInscripcio(int mida) {
        llista = new Inscripcio[mida];
        limit = mida;
        numElem = 0;
        llistaEspera = new LlistaEspera();
    }

    /**
     * Métode cópia de la llista
     *
     * @return llista copiada
     */
    public LlistaInscripcio copia() {
        LlistaInscripcio novaLlista = new LlistaInscripcio(numElem);
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
    public void afegir(Inscripcio ins) throws UsuariJaInscritException {
        int i = 0;
        boolean trobat = false;
        while (!trobat && i < numElem) {
            if (llista[i].getUsuari().esIgual(ins.getUsuari()) && llista[i].getActivitat().iguals(ins.getActivitat())) {
                trobat = true;
            } else {
                i++;
            }
        }

        if (!trobat && numElem < limit && ins.getActivitat() instanceof ActividadOnline) {
            llista[numElem] = ins.copia();
            numElem++;
        } else if (!trobat && numElem < limit && (ins.getActivitat() instanceof ActividadDia
                || ins.getActivitat() instanceof ActividadPeriodicas)) {
            if (ins.getActivitat() instanceof ActividadDia) {
                ActividadDia act = (ActividadDia) ins.getActivitat();
                if (this.contarInscritos(act.getNom()) < act.getLimite()) {
                    llista[numElem] = ins.copia();
                    numElem++;
                } else {
                    llistaEspera.afegir(ins);
                    System.out.println("S'ha afegit a la llista d'espera perque la activitat esta plena");
                }
            } else if (ins.getActivitat() instanceof ActividadPeriodicas) {
                ActividadPeriodicas act = (ActividadPeriodicas) ins.getActivitat();
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

    /**
     * Métode toString
     */
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
    public ListaActividad ActivitatsUsuari(Usuari nom) {
        ListaActividad aux = new ListaActividad(limit);
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
     * Método que devuelve el número de personas inscritas Autor Laura Lope
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
    public boolean teLlistaEspera(Actividad act) {
        boolean resultado = false;

        if (act instanceof ActividadDia) {
            ActividadDia dia = (ActividadDia) act;
            if (dia.getLimite() <= this.contarInscritos(dia.getNom())) {
                resultado = true;
            }
        } else if (act instanceof ActividadPeriodicas) {
            ActividadPeriodicas periodica = (ActividadPeriodicas) act;
            if (periodica.getLimite() <= this.contarInscritos(periodica.getNom())) {
                resultado = true;
            }
        } else if (act instanceof ActividadOnline) {
            ActividadOnline online = (ActividadOnline) act;
            if (20 <= this.contarInscritos(online.getNom())) {
                resultado = true;
            }
        }
        return resultado;
    }

    /**
     * Metode que elimina la inscripcio d'un usuari d'una activitat previament
     * inscrita Metode necesari per la opcio 12 (Jordi)
     *
     * @param usuari
     * @param act
     */
    public void eliminarInscripcio(Usuari usuari, Actividad act, LlistaEspera llistaEspera) {
        boolean trobat = false;
        int i = 0;
        Inscripcio insEspera = null;
        while (!trobat && i < numElem) {
            if (llista[i].getUsuari().equals(usuari) && llista[i].getActivitat().equals(act)) {
                for (int j = i; j < numElem - 1; j++) {
                    llista[j] = llista[j + 1];
                }
                numElem--;
                llista[numElem] = null;
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
     * Metode que compara quantes activitats fa cada usuari d'un tipus especific
     * i retorna el mes actiu (Jordi)
     *
     * @param opcio
     * @param llistaUsuari
     * @return Usuari més actiu del col·lectiu en concret
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
     *
     * @param llistaEspera
     */
    public void assignarLlistaEspera(LlistaEspera llistaEspera) {
        this.llistaEspera = llistaEspera;
    }

    /**
     * Métode que serveix per veure els usuaris apuntats a una activitat
     *
     * @param act
     * @return llista d'usuaris apuntats
     */
    public LlistaUsuari usuarisApuntats(Actividad act) {
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
     *
     * @param llistaActivitat
     * @param llistaUsuaris
     * @param act
     * @param nom
     * @param avui
     * @param valoracio
     */
    public void valorarActivitat(ListaActividad llistaActivitat, LlistaUsuari llistaUsuari, String act, String nom, Data avui, int valoracio) {
        int i = 0;
        int indexVal = 0;
        boolean trobat = false;
        try {
            while (!trobat && i < numElem) {

                if (llista[i].getUsuari().esIgual(llistaUsuari.buscarUsuari(nom))) {
                    trobat = true;
                } else {
                    i++;
                }
            }
        } catch (UsuariNoTrobatException e) {
            System.err.println("l'usuari " + e + "no s'ha trobat.");
        }
        Data fechaFin = new Data();
        if (llista[i].getActivitat() instanceof ActividadDia) {
            ActividadDia dia = (ActividadDia) llista[i].getActivitat();
            fechaFin = dia.getFechaIn();
        } else if (llista[i].getActivitat() instanceof ActividadPeriodicas) {
            ActividadPeriodicas periodica = (ActividadPeriodicas) llista[i].getActivitat();
            fechaFin = periodica.getFechaIn().sumarDias(periodica.getNumSemanas() * 7);
        } else if (llista[i].getActivitat() instanceof ActividadOnline) {
            ActividadOnline online = (ActividadOnline) llista[i].getActivitat();
            fechaFin = online.getFechaIn().sumarDias(online.getPeriodoVis());
        }
        indexVal = posicioValoracio(llista[i], llistaUsuari, llistaActivitat, act, nom);
        if (fechaFin.esDataInferiorOigual(avui)) {
            llista[indexVal].setValoracio(valoracio);
            System.out.println("S'ha guardat la valoració");
        } else {
            System.out.println("L'activitat encara no ha finalitzat.");
        }
    }

    /**
     * Métode que mostra les valoracions
     *
     * @return totes les valoracions efectuades
     */
    public String mostrarValoracions() {
        String llistaval = "Llista de Valoracions: \n";
        for (int i = 0; i < numElem; i++) {
            if (llista[i].getValoracio() != -1) {
                llistaval = llistaval + "\tNom Activitat: " + llista[i].getActivitat().getNom() + " Valoracio: " + llista[i].getValoracio() + "\n";
            } else {
                llistaval = llistaval + "\tNom Activitat: " + llista[i].getActivitat().getNom() + " Valoracio: Pendent\n";
            }
        }
        return llistaval;
    }

    /**
     * Métode que mostra les valoracions realitzades per l'usuari
     *
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
                if (llista[j].getValoracio() != -1) {
                    llistaval = llistaval + "\tNom Activitat: " + llista[j].getActivitat().getNom() + " Valoracio: " + llista[j].getValoracio() + "\n";
                } else {
                    llistaval = llistaval + "\tL'usuari esta inscrit a l'activitat "+llista[j].getActivitat().getNom()+", pero encara no l'ha valorat.\n";
                }
            }
        }
        return llistaval;
    }

    /**
     * Metode que calcula i mostra per pantalla la mitjana dels col·lectius
     *
     * @param llistaActivitat
     */
    public void mitjanaColectius(LlistaUsuari llistaUsuari) {
        int[] pdi = new int[numElem];
        int[] ptgas = new int[numElem];
        int[] estudiants = new int[numElem];
        int x = 0, y = 0, z = 0, valpd = 0, valpt = 0, vales = 0;
        int acumpd, acumpt, acumes;
        double mitjpd, mitjpt, mitjes;
        for (int i = 0; i < numElem; i++) {
            if (llista[i].getValoracio() != -1) {
                if (llista[i].getUsuari() instanceof UsuariPDI) {
                    pdi[x] = llista[i].getValoracio();
                    x++;
                } else if (llista[i].getUsuari() instanceof UsuariPTGAS) {
                    ptgas[y] = llista[i].getValoracio();
                    y++;
                } else if (llista[i].getUsuari() instanceof UsuariEstudiant) {
                    estudiants[z] = llista[i].getValoracio();
                    z++;
                }
            }
        }

        acumpd = x; acumpt = y; acumes = z;

        for (x = 0; x < acumpd; x++) {
            valpd = valpd + pdi[x];
        }
        for (y = 0; y < acumpt; y++) {
            valpt = valpt + ptgas[y];
        }
        for (z = 0; z < acumes; z++) {
            vales = vales + estudiants[z];
        }
        if (acumpd > 0) {
            mitjpd = (double) valpd / acumpd;
        } else {
            mitjpd = 0.0;
        }
        if (acumpt > 0) {
            mitjpt = (double) valpt / acumpt;
        } else {
            mitjpt = 0.0;
        }
        if (acumes > 0) {
            mitjes = (double) vales / acumes;
        } else {
            mitjes = 0.0;
        }
        System.out.println("Les mitjanes de valoracio dels diferents colectius son: \n \tPDI: " + mitjpd + "\n \tPTGAS: " + mitjpt + "\n \tEstudiants: " + mitjes);
    }

    /**
     * Métode que diu la posicio on s'asigna la valoració
     *
     * @param ins
     * @param llistaActivitat
     * @param act
     * @return poscicio on s'asigna la valoració
     */
    public int posicioValoracio(Inscripcio ins, LlistaUsuari llistaUsuari, ListaActividad llistaActivitat, String act, String usu) {
        int aux = 0;
        boolean trobat = false;
        int i = 0;
        while (i < numElem && !trobat) {
            try {
                if (llistaActivitat.buscarActividades(act).iguals(llista[i].getActivitat()) && llistaUsuari.buscarUsuari(usu).esIgual(llista[i].getUsuari())) {
                    aux = i;
                    trobat = true;
                } else {
                    i++;
                }
            } catch (ActivitatNoTrobadaException e) {
                System.err.println("L'activitat " + e + "no s'ha trobat.");
            } catch (UsuariNoTrobatException e) {
                System.err.println("L'usuari " + e + "no s'ha trobat.");
            }
        }
        return aux;
    }
}
