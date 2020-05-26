package main.java;

import java.util.*;

public class Dienblad {
    // dit moet later een Queue<Persoon>, maar het is een interface, hier leren we later meer over
    private Stack<Artikel> artikelen;
    private Iterator<Artikel> artikelIterator;
    private Persoon klant;

    /**
     * Constructor voor dienblad met artikelen
     */
    public Dienblad(Stack<Artikel> artikelen) {

        this.artikelen = artikelen;
    }

    /**
     * Constructor voor klant
     */
    public Dienblad(Persoon klant) {

        this.klant = klant;
    }

    /**
     * Methode om artikel aan dienblad toe te voegen
     *
     * @param artikel
     */
    public void voegToe(Artikel artikel) {
        // method body omitted
        artikelen.add(artikel);
    }

    /**
     * Getter voor ArtikelIterator als Iterator met type Artikel
     * @return artikelIterator
     */
    public Iterator<Artikel> getArtikelIterator() {
        return artikelIterator;
    }

    /**
     * Getter voor de klant als type Persoon
     * @return klant
     */
    public Persoon getKlant() {
        return klant;
    }

    /**
     * setter voor de klant als type Persoon
     * @param klant
     */
    public void setKlant(Persoon klant) {
        this.klant = klant;
    }
}

