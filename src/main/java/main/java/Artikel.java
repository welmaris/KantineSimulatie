package main.java;

/**
 * klasse voor het creëeren van een artikel met een naam en een prijs.
 * voor het opgeven van een prijs, gebruik BigDecimal.valueOf(0) en geef de waarde op tussen de haakjes.
 */
public class Artikel {
    private String naam;
    private double prijs;
    private int korting = 0;

    /**
     * Constructor voor de Klasse Artikel met een naam en prijs
     * @param naam
     * @param prijs
     */
    public Artikel(String naam, double prijs){
        this.naam = naam;
        this.prijs = prijs;
        korting = 0;
    }

    public Artikel(String naam, double prijs, int korting){
        this.naam = naam;
        this.prijs = prijs;
        this.korting = korting;
    }

    /**
     * lege constructor voor klasse Artikel
     */
    public Artikel(){
        naam = null;
        prijs = 0;
    }

    /**
     * Getter voor de String naam
     * @return naam
     */
    public String getNaam() {
        return naam;
    }

    /**
     * Setter voor String naam
     * @param naam
     */
    public void setNaam(String naam) {
        this.naam = naam;
    }

    /**
     * Getter voor double prijs
     * @return prijs
     */
    public double getPrijs() {
        return prijs;
    }

    /**
     * Setter voor double prijs
     * @param prijs
     */
    public void setPrijs(double prijs) {
        this.prijs = prijs;
    }


    /**
     * zet Artikel tot String. Geeft de naam en de prijs
     * @Override
     */
    public String toString() {
        return naam + " €" + prijs;
    }

    public int getKorting(){ return korting; }
    public void setKorting(int korting){this.korting = korting;}
}
