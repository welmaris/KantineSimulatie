package main.java;

public class Datum {

	private final int dag;
	private final int maand;
	private final int jaar;

	private static final int MAAND_JAN = 1;
	private static final int MAAND_FEB = 2;
	private static final int MAAND_MRT = 3;
	private static final int MAAND_APR = 4;
	private static final int MAAND_MEI = 5;
	private static final int MAAND_JUN = 6;
	private static final int MAAND_JUL = 7;
	private static final int MAAND_AUG = 8;
	private static final int MAAND_SEP = 9;
	private static final int MAAND_OKT = 10;
	private static final int MAAND_NOV = 11;
	private static final int MAAND_DEC = 12;

	/**
	 * Constructor voor datum met 3 int parameters
	 * @param dag
	 * @param maand
	 * @param jaar
	 *
	 */
	public Datum(int dag, int maand, int jaar){
		if(bestaatDatum(dag, maand, jaar)) {
			this.dag = dag;
			this.maand = maand;
			this.jaar = jaar;
		} else {this.dag = 0;
			this.maand = 0;
			this.jaar = 0;}
	}

	/**
	 * Constructor zonder parameter
	 */
	public Datum() {
		dag = 0;
		maand = 0;
		jaar = 0;

	}

	/**
	 * Methode die checkt of de datum werkelijk bestaat.
	 * Controleert ook op schikkeljaren en uitzonderingen daarop
	 * @param dag
	 * @param maand
	 * @param jaar
	 * @return
	 */
	public boolean bestaatDatum(int dag, int maand, int jaar) {

		// Hier word gecontroleerd of de maand tussen de 1 en de 12 is --> hoeft niet persé, wordt later ook gedaan
		// Hier word gecontroleerd of de dag tussen de 1 en de 31 ligt --> hoeft niet persé, wordt later ook gedaan
		// Hier word gecontroleerd of het jaar tussen de  1900 en de 2100 ligt
		if ( jaar < 1900 || jaar > 2100) {
			return false;
		}

		// Hier word gecontroleerd of de maand februari is
		if (maand == MAAND_FEB ){

			// Hier word gecontroleerd of het geen uitzonderingsjaar is op het schikkeljaar
			if(jaar % 4 == 0 & jaar % 100 != 0){
				if(dag <= 29){ return true; }

			// Check of jaar een uitzondering op de uitzondering is voor schikkeljaren
			} else if(jaar % 400 == 0){
				if(dag <= 29){ return true; }

			// Als het geen schikkeljaar is
			} else {
				if(dag <=28){ return true; }
			}
		}

		// Check voor de maanden die 30 dagen hebben
		if ( maand == MAAND_APR|| maand == MAAND_JUN|| maand == MAAND_SEP || maand == MAAND_NOV){
			if (dag <= 30){return true;}
		}

		// Check voor de maandenmaanden die 31 dagen hebben
		if (maand == MAAND_JAN || maand == MAAND_MRT || maand == MAAND_MEI|| maand == MAAND_JUL|| maand == MAAND_AUG|| maand== MAAND_OKT|| maand == MAAND_DEC){
			return dag <= 31;
		}

		// Als geen van de opties klopt, dan bestaat de maand niet
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

	/**
	 * Getter voor de dag als int
	 * @return dag
	 */
	public int getDag() {
		return dag;
	}

	/**
	 * Getter voor de maand als int
	 * @return maand
	 */
	public int getMaand() {
		return maand;
	}

	/**
	 * getter voor het jaar als int
	 * @return jaar
	 */
	public int getJaar() {
		return jaar;
	}
}
