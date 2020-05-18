package main.java;

public class Datum {

	private int dag;
	private int maand;
	private int jaar;

	private static int MAAND_JAN = 1;
	private static int MAAND_FEB = 2;
	private static int MAAND_MRT = 3;
	private static int MAAND_APR = 4;
	private static int MAAND_MEI = 5;
	private static int MAAND_JUN = 6;
	private static int MAAND_JUL = 7;
	private static int MAAND_AUG = 8;
	private static int MAAND_SEP = 9;
	private static int MAAND_OKT = 10;
	private static int MAAND_NOV = 11;
	private static int MAAND_DEC = 12;

	/**
	 * Constructor
	 * @param dag
	 * @param maand
	 * @param jaar
	 *
	 */
	public Datum(int dag, int maand, int jaar){
		if(bestaatDatum(dag, maand, jaar)) {
			;
			this.dag = dag;
			this.maand = maand;
			this.jaar = jaar;
		} else {this.dag = 0;
			this.maand = 0;
			this.jaar = 0;};
	}

	/**
	 * Constructor zonder parameter
	 */
	public Datum() {
		dag = 0;
		maand = 0;
		jaar = 0;

	}

	public boolean bestaatDatum(int dag, int maand, int jaar) {
		// TODO



		//hier word gecontroleerd of de maand tussen de 1 en de 12 is
		//hier word gecontroleerd of de dag tussen de 1 en de 31 ligt
		//hier word gecontroleerd of het jaar tussen de  1900 en de 2100 ligt
		if ( maand < 1  ||maand > 12 || dag < 1 || dag > 31 || jaar < 1900 || jaar > 2100) {
			return false;
		}
		//hier word gecontroleerd of de maand februari is
		if (maand == MAAND_FEB ){
			//hier word gecontroleerd of het een schrikkeljaar is
			if (jaar % 4 == 0) {
				//hier word gecontroleerd of het een uitzondering is op het schrikkeljaar
				if (jaar % 100 == 0) {
					//hier word gecontroleerd of het geen uitzondering op de uitzondering is en dus WEL een schrikkeljaar is
					if (jaar % 400 == 0 && dag < 30) {
						return true;
					} else if (dag < 29) {
						return true;
					}
				} else if (dag < 30) {
					return true;
				}
			} else if (dag < 29){return true;}
		}
		if (maand == MAAND_JAN || maand == MAAND_MRT || maand == MAAND_MEI|| maand == MAAND_JUL|| maand == MAAND_AUG|| maand== MAAND_OKT|| maand == MAAND_DEC){
			if (dag < 32){return true;}
			else return false;
		}
		if ( maand == MAAND_APR|| maand == MAAND_JUN|| maand == MAAND_SEP || maand == MAAND_NOV){
			if (dag<31){return true;}
			else return false;
		}
		return false;
	}




	/**
	 * Getter voor Sting weergave van datum
	 *
	 * @return Geboortedatum
	 */
	public String getDatumAsString() {
		//het aanmaken van een string variabele
		 String datum = dag + "/" + maand + "/" + jaar;


		return datum;
	}
	public int getDag() {
		return dag;
	}

	public int getMaand() {
		return maand;
	}

	public int getJaar() {
		return jaar;
	}
}
