package Actividades;

import Data.Data;

/**
 * Clase: Actividades periódicas
 * Autor: Laura Lope
 */
public class ActividadesPeriodicas extends Actividades {
    public String diaSemana;
    public int[] horario = new int[2];
    public Data fechaIn;
    public int numSemanas;
    public int limite;
    public double precio;
    public String centro;
    public String ciudad;

    public ActividadesPeriodicas(String nom, Data periodoIn, Data periodoFin, String[] colectivos, String diaSemana,
            int[] horario, Data fechaIn, int numSemanas, int limite, double precio, String centro, String ciudad) {
        super(nom, periodoIn, periodoFin, colectivos);

        if (diaSemana.equalsIgnoreCase("Lunes")
                || diaSemana.equalsIgnoreCase("Martes") | diaSemana.equalsIgnoreCase("Miércoles")
                        | diaSemana.equalsIgnoreCase("Jueves") | diaSemana.equalsIgnoreCase("Viernes")
                        | diaSemana.equalsIgnoreCase("Sábado") | diaSemana.equalsIgnoreCase("Domingo")) {
            this.diaSemana = diaSemana;
        } else {
            this.diaSemana = "-----";
        }

        if (horario[0] <= 23 && horario[0] >= 0 && horario[1] <= 59 && horario[1] >= 0) {
            this.horario = horario;
        } else {
            this.horario = new int[] { 00, 00 };
        }

        if (fechaIn.getMes() >= 9 && fechaIn.getMes() <= 12) {
            this.fechaIn = fechaIn;
        } else {
            this.fechaIn = new Data(); // Si no cumple la condición devuelve la fecha por defecto
        }

        this.numSemanas = numSemanas;
        this.limite = limite;
        this.precio = precio;
        this.centro = centro;
        this.ciudad = ciudad;
    }

    /**
     * Setter: asigna el día de la semana que será la actividad
     * 
     * @param dia
     */
    public void setDiaSemana(String dia) {
        if (dia.equalsIgnoreCase("Lunes") || dia.equalsIgnoreCase("Martes") | dia.equalsIgnoreCase("Miércoles")
                | dia.equalsIgnoreCase("Jueves") | dia.equalsIgnoreCase("Viernes") | dia.equalsIgnoreCase("Sábado")
                | dia.equalsIgnoreCase("Domingo")) {
            this.diaSemana = dia;
        } else {
            this.diaSemana = "------";
        }
    }

    /**
     * Setter: fija cuando será la actividad
     * 
     * @param hora
     * @param min
     */
    public void setHorario(int hora, int min) {
        if (hora <= 23 && hora >= 0 && min <= 59 && min >= 0) {
            horario[0] = hora;
            horario[1] = min;
        } else {
            horario[0] = 0;
            horario[1] = 0;
        }
    }

    /**
     * Setter: determina la fecha de inicio de la actividad
     * 
     * @param fechaIn
     */
    public void setFechaIn(Data fechaIn) {
        if (fechaIn.getMes() >= 9 && fechaIn.getMes() <= 12) {
            this.fechaIn = fechaIn;
        } else {
            this.fechaIn = new Data(); // Si no cumple la condición devuelve la fecha por defecto
        }
    }

    /**
     * Setter: determina el número de semanas que se repetirá la actividad
     * 
     * @param num
     */
    public void setNumSemanas(int num) {
        this.numSemanas = num;
    }

    /**
     * Setter: fija el número máximo de personas que pueden acceder a esa actividad
     * 
     * @param limite
     */
    public void setLimite(int limite) {
        this.limite = limite;
    }

    /**
     * Setter: fija el precio de la actividad
     * 
     * @param precio
     */
    public void setPrecio(double precio) {
        this.precio = precio;
    }

    /**
     * Setter: fija el centro de la actividad
     * 
     * @param centro
     */
    public void setCentro(String centro) {
        this.centro = centro;
    }

    /**
     * Setter: fija la ciudad de la actividad
     * 
     * @param ciudad
     */
    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    /**
     * Getter: devuelve el dia de la semana que empieza la actividad
     * 
     * @return
     */
    public String getDiaSemana() {
        return this.diaSemana;
    }

    /**
     * Getter: devuelve en que horario es la actividad
     * 
     * @return
     */
    public String getHorario() {
        String resultado = "";
        for (int i = 0; i < horario.length; i++) {
            if (horario[i] < 10) {
                resultado += "0" + horario[i];
            } else {
                resultado += horario[i];
            }
            if (i < horario.length - 1) {
                resultado += ":";
            }
        }
        return resultado;
    }

    /**
     * Getter: devuelve la fecha de inicio de la actividad
     * 
     * @return
     */
    public Data getFechaIn() {
        return fechaIn;
    }

    /**
     * Getter: devuelve el número de semanas durante las que se repita la actividad
     * 
     * @return
     */
    public int getNumSemanas() {
        return this.numSemanas;
    }

    /**
     * Getter: devuelve el límite de personas que pueden ir a la actividad
     * 
     * @return
     */
    public int getLimite() {
        return this.limite;
    }

    /**
     * Getter: devuelve el precio de la actividad
     * 
     * @return
     */
    public double getPrecio() {
        return this.precio;
    }

    /**
     * Getter: devuelve el centro donde tendrá lugar la actividad
     * 
     * @return
     */
    public String getCentro() {
        return this.centro;
    }

    /**
     * Getter: devuelve la ciudad donde tendrá lugar la actividad
     * 
     * @return
     */
    public String getCiudad() {
        return this.ciudad;
    }

    @Override
    public String toString() {
        return super.toString() + "\nDia semana: " + diaSemana +
                "\nHorario: " + getHorario() +
                "\nFecha de inicio: " + fechaIn +
                "\nNúmero de semanas: " + numSemanas +
                "\nLímite de asistentes: " + limite +
                "\nPrecio de la actividad: " + precio +
                "\nCentro: " + centro +
                "\nCiudad: " + ciudad;
    }

    /**
     * Método que hace una copia
     * 
     * @return
     */
    public ActividadesPeriodicas copia() {
        ActividadesPeriodicas a = new ActividadesPeriodicas(this.nom, this.periodoIn, this.periodoFin, this.colectivos,
                this.diaSemana,
                this.horario, this.fechaIn, this.numSemanas, this.limite, this.precio, this.centro, this.ciudad);
        return a;
    }

    /**
     * Método que comprueba si dos actividades son iguales
     * 
     * @param a
     * @return
     */
    public boolean iguals(ActividadesPeriodicas a) {
        super.iguals(a);

        if (!a.diaSemana.equalsIgnoreCase(this.diaSemana)) {
            return false;
        }

        if (this.horario.length != a.horario.length) {
            return false;
        } else {
            for (int i = 0; i < this.horario.length; i++) {
                if (this.horario[i] != this.horario[i]) {
                    return false;
                }
            }
        }

        if (!a.fechaIn.esIgual(this.fechaIn)) {
            return false;
        }

        if (this.numSemanas != a.numSemanas) {
            return false;
        }

        if (this.limite != a.limite) {
            return false;
        }

        if (this.precio != a.precio) {
            return false;
        }

        if (!this.centro.equalsIgnoreCase(a.centro)) {
            return false;
        }

        if (!this.ciudad.equalsIgnoreCase(a.ciudad)) {
            return false;
        }

        return true;
    }
}
