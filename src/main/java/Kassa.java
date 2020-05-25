package main.java;

import java.util.ArrayList;
import java.util.Iterator;

public class Kassa {
    private double geldInKassa;
    private Dienblad dienblad;
    private KassaRij kassaRij;
    private double totaalPrijs;
    private int gepasseederArtikelen;

    /**
     * Constructor
     */
    public Kassa(KassaRij kassarij) {
        geldInKassa = 0;
        dienblad = kassarij.eerstePersoonInRij();
        totaalPrijs = 0;
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
        gepasseederArtikelen += getAantalArtikelen();
        totaalPrijs = getTotaalPrijs();
        geldInKassa += totaalPrijs;

    }

    /**
     * Methode om aantal artikelen op dienblad te tellen
     *
     * @return Het aantal artikelen
     */
    public int getAantalArtikelen() {
        // method body omitted
        int artikelenAantal = 0;
        while(dienblad.getArtikelIterator().hasNext()) {
            artikelenAantal++;
            dienblad.getArtikelIterator().next();
        }
        return artikelenAantal;
    }

    /**
     * Methode om de totaalprijs van de artikelen op dienblad uit te rekenen
     *
     * @return De totaalprijs
     */
    public double getTotaalPrijs() {
        // method body omitted
        double totaal = 0;
        while(dienblad.getArtikelIterator().hasNext()) {
            Artikel artikel = dienblad.getArtikelIterator().next();
            totaal += artikel.getPrijs();
        }
        return totaal;
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
    public double hoeveelheidGeldInKassa() {
        return geldInKassa;
    }

    /**
     * reset de waarden van het aantal gepasseerde artikelen en de totale hoeveelheid geld in de
     * kassa.
     */
    public void resetKassa() {
        geldInKassa = 0;
        gepasseederArtikelen = 0;

    }
}
