public class Datum {

	private int dag;
	private int maand;
	private int jaar;

	/**
	 * Constructor
	 */
	public Datum(int dag, int maand, int jaar){
		this.dag = dag;
		this.maand = maand;
		this.jaar = jaar;
	}
	// TODO

	public boolean bestaatDatum(int dag, int maand, int jaar) {
		// TODO

		//als de datum nul is geeft hij false
		//als de maand niet tussen de 1 en de 12 is
		//als het jaar niet tussen de 1900 en 2100 ligt
		//de dag maand combinatie moet bestaan
		//schrikkeljaar als deelbaar door 4, maar niet 100 -->

		//hier moet 29 & 30 februari nog in worden verwerjt
		if (jaar % 4 = 0){
			return false;
		}

		if(maand = 0){

			return false;
		}


	}



	/**
	 * Getter voor Sting weergave van datum
	 *
	 * @return Geboortedatum
	 */
	public String getDatumAsString() {
		//het aanmaken van een string variabele
		public String datum = dag + "/" + maand + "/" + jaar;


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
