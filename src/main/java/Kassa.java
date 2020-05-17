package main.java;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;

public class Kassa {
    private BigDecimal geldInKassa;
    private Dienblad dienblad;
    private KassaRij kassaRij;
    private BigDecimal totaalPrijs;
    private int gepasseederArtikelen;

    /**
     * Constructor
     */
    public Kassa(KassaRij kassarij) {
        geldInKassa = BigDecimal.valueOf(0);
        dienblad = kassarij.eerstePersoonInRij();
        totaalPrijs = BigDecimal.valueOf(0);
        gepasseederArtikelen = 0;
        this.kassaRij = kassarij;

    }

    /**
     * Vraag het aantal artikelen en de totaalprijs op. Tel deze gegevens op bij de controletotalen
     * die voor de kassa worden bijgehouden. De implementatie wordt later vervangen door een echte
     * betaling door de persoon.
     *
     * @param klant die moet afrekenen
     */
    public void rekenAf(Dienblad klant) {
        gepasseederArtikelen += klant.getAantalArtikelen();
        totaalPrijs = BigDecimal.valueOf(klant.getTotaalPrijs());
        geldInKassa.add(totaalPrijs);

    }

    /**
     * Geeft het aantal artikelen dat de kassa heeft gepasseerd, vanaf het moment dat de methode
     * resetWaarden is aangeroepen.
     *
     * @return aantal artikelen
     */
    public int aantalArtikelen() {
        return gepasseederArtikelen;
    }

    /**
     * Geeft het totaalbedrag van alle artikelen die de kass zijn gepasseerd, vanaf het moment dat
     * de methode resetKassa is aangeroepen.
     *
     * @return hoeveelheid geld in de kassa
     */
    public BigDecimal hoeveelheidGeldInKassa() {
        return geldInKassa;
    }

    /**
     * reset de waarden van het aantal gepasseerde artikelen en de totale hoeveelheid geld in de
     * kassa.
     */
    public void resetKassa() {
        geldInKassa = BigDecimal.valueOf(0);
        gepasseederArtikelen = 0;

    }
}
