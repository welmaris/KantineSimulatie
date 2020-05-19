package main.java;

/**
 * klasse voor het creëeren van een artikel met een naam en een prijs.
 * voor het opgeven van een prijs, gebruik BigDecimal.valueOf(0) en geef de waarde op tussen de haakjes.
 */
public class Artikel {
    private String naam;
    private double prijs;

    public Artikel(String naam, double prijs){
        this.naam = naam;
        this.prijs = prijs;

    }

    public Artikel(){
        naam = null;
        prijs = 0;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public double getPrijs() {
        return prijs;
    }

    public void setPrijs(double prijs) {
        this.prijs = prijs;
    }

    @Override
    public String toString() {
        return naam + " €" + prijs;
    }
}
