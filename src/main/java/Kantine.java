package main.java;

import java.util.ArrayList;

public class Kantine {

    private Kassa kassa;
    private KassaRij kassarij;
    private Dienblad dienblad;

    /**
     * Constructor
     */
    public Kantine() {
        kassarij = new KassaRij();
        kassa = new Kassa(kassarij);
        //dienblad = new dienblad();
    }

    /**
     * In deze methode wordt een Persoon en Dienblad gemaakt en aan elkaar gekoppeld. Maak twee
     * Artikelen aan en plaats deze op het dienblad. Tenslotte sluit de Persoon zich aan bij de rij
     * voor de kassa.
     */
    public void loopPakSluitAan() {
        // method body omitted
        dienblad.setKlant(new Persoon());
        dienblad.voegToe(new Artikel());
        dienblad.voegToe(new Artikel());
        kassarij.sluitAchteraan(dienblad);
    }

    /**
     * Deze methode handelt de rij voor de kassa af.
     */
    public void verwerkRijVoorKassa() {
        while (kassarij.erIsEenRij()) {
            // omitted
            kassarij.eerstePersoonInRij();
        }
    }

    public Kassa getKassa() {
        return kassa;
    }

    /**
     * Deze methode telt het geld uit de kassa
     *
     * @return hoeveelheid geld in kassa
     */
    /*public double hoeveelheidGeldInKassa() {
        // method body omitted
        return kassa.hoeveelheidGeldInKassa();
    }

    /**
     * Deze methode geeft het aantal gepasseerde artikelen.
     *
     * @return het aantal gepasseerde artikelen
     */
    /*public int aantalArtikelen() {
        // method body omitted
        return kassa.aantalArtikelen();
    }

    /**
     * Deze methode reset de bijgehouden telling van het aantal artikelen en "leegt" de inhoud van
     * de kassa.
     */
    /*public void resetKassa() {
        // method body omitted
        kassa.resetKassa();
    }*/
}
