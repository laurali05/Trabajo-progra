package Data;

public class Data {
	private int dia;
	private int mes;
	private int any;

	/**
	 * Constructor sin parámetros
	 */
	public Data() {
		this.dia = 17;
		this.mes = 12;
		this.any = 2025;
	}

	/**
	 * Constructor con parámetros
	 * 
	 * @param dia
	 * @param mes
	 * @param any
	 */
	public Data(int dia, int mes, int any) {
		if (esDataCorrecta(dia, mes, any)) {
			this.dia = dia;
			this.mes = mes;
			this.any = any;
		} else {
			this.dia = 1;
			this.mes = 1;
			this.any = 2000;
		}
	}

	/**
	 * Getter
	 * 
	 * @return dia de la data
	 */
	public int getDia() {
		return dia;
	}

	/**
	 * Getter
	 * 
	 * @return mes de la data
	 */
	public int getMes() {
		return mes;
	}

	/**
	 * Getter
	 * 
	 * @return any de la data
	 */
	public int getAny() {
		return any;
	}

	/**
	 * Setter
	 * 
	 * @param dia
	 * @param mes
	 * @param any
	 */
	public void setData(int dia, int mes, int any) {
		if (esDataCorrecta(dia, mes, any)) { // ens asegurem que hi ha una data vàlida
			this.dia = dia;
			this.mes = mes;
			this.any = any;
		}
	}

	/**
	 * Método que comprueba si dos fechas son iguales
	 * 
	 * @param data - fecha a comparar
	 * @return si son iguales
	 */
	public boolean esIgual(Data data) {
		if (this.dia == data.getDia() && this.mes == data.getMes() && this.any == data.getAny()) {
			return true;
		}
		return false;
	}

	/**
	 * Mètode que calcula el dia siguiente a la fecha actual
	 * * @return
	 */
	public Data diaSeguent() {
		Data novaData = new Data(dia, mes, any);

		novaData.dia++;
		if (novaData.dia > diesMes(novaData.mes, novaData.any)) {
			novaData.dia = 1;
			novaData.mes++;
			if (novaData.mes > 12) {
				novaData.mes = 1;
				novaData.any++;
			}
		}
		return novaData;
	}

	/**
	 * 
	 * Método que calcula el día anterior a la fecha actual
	 */
	public void diaAnterior() {
		this.dia--;
		if (this.dia < 1) {
			this.mes--;
			if (this.mes < 1) {
				this.mes = 12;
				this.any--;
			}
			this.dia = diesMes(this.mes, this.any);
		}
	}

	/**
	 * Método que calcula cuanto falta para una fecha introducida por parámetro
	 * 
	 * @param data
	 * @return
	 */
	public int numDiesAData(Data data) {
		Data dataTemp;
		int contador;
		dataTemp = new Data(dia, mes, any);
		if (dataTemp.esDataInferiorOigual(data)) {
			contador = 0;
			// la data rebuda és major que l'actual
			while (!dataTemp.esIgual(data)) {
				dataTemp = dataTemp.diaSeguent();
				contador++;
			}
		} else
			contador = -1;
		return contador;
	}

	/**
	 * Método que comprueba si una fecha es inferior a otra
	 * 
	 * @param nova
	 * @return
	 */
	public boolean esDataInferiorOigual(Data nova) {
		boolean esInferior;
		if (any < nova.any)
			esInferior = true;
		else if (any > nova.any)
			esInferior = false;
		else {
			// cas anys iguals
			if (mes < nova.mes)
				esInferior = true;
			else if (mes > nova.mes)
				esInferior = false;
			else {
				// anys i mes iguals
				if (dia <= nova.dia)
					esInferior = true;
				else
					esInferior = false;
			}
		}
		return esInferior;

	}

	/**
	 * Método que comprueba si la fecha actual pertenece a un año bisiesto
	 * 
	 * @return
	 */
	public boolean esDataAnyTraspas() {
		if (esAnyTraspas(this.any)) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public String toString() {
		return ("[" + dia + ", " + mes + ", " + any + "]");
	}

	/**
	 * Método que crea un duplicado de la fecha actual
	 * 
	 * @return
	 */
	public Data copia() {
		Data aux = new Data(dia, mes, any);
		return aux;
	}

	private static boolean esDataCorrecta(int dia, int mes, int any) {
		boolean hoEs = true;
		if (dia < 1 || dia > 31) {
			hoEs = false;
		}
		if (mes < 1 || mes > 12) {
			hoEs = false;
		}
		if (dia > diesMes(mes, any)) {
			hoEs = false;
		}
		return hoEs;
	}

	private static boolean esAnyTraspas(int any) {
		if ((any % 4 == 0) && ((any % 100 != 0) || (any % 400 == 0))) {
			return true;
		} else {
			return false;
		}
	}

	private static int diesMes(int mes, int any) {
		int diesMes;
		if (mes == 2) {
			if (esAnyTraspas(any)) {
				diesMes = 29;
			} else {
				diesMes = 28;
			}
		} else {
			if (mes == 4 || mes == 6 || mes == 9 || mes == 11) {
				diesMes = 30;
			} else {
				diesMes = 31;
			}
		}
		return diesMes;
	}

}