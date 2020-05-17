package main.java;

import java.math.BigDecimal;

/**
 * klasse voor het creëeren van een artikel met een naam en een prijs.
 * voor het opgeven van een prijs, gebruik BigDecimal.valueOf(0) en geef de waarde op tussen de haakjes.
 */
public class Artikel {
    private String naam;
    private BigDecimal prijs;

    public Artikel(String naam, BigDecimal prijs){
        this.naam = naam;
        this.prijs = prijs;

    }

    public Artikel(){
        naam = null;
        prijs = BigDecimal.valueOf(0);
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public BigDecimal getPrijs() {
        return prijs;
    }

    public void setPrijs(BigDecimal prijs) {
        this.prijs = prijs;
    }

    @Override
    public String toString() {
        return "Artikel{" +
                "naam='" + naam + '\'' +
                ", prijs=" + prijs +
                '}';
    }
}
