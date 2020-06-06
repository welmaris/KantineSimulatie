package main.java;

import java.util.*;

public class Dienblad implements Iterator {
    // dit moet later een Queue<Persoon>, maar het is een interface, hier leren we later meer over
    private Stack<Artikel> artikelen;
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
        artikelen.add(artikel);
    }

    /**
     * Getter voor ArtikelIterator als Iterator met type Artikel
     * @return artikelIterator
     */
    public Iterator<Artikel> getArtikelIterator() {
        return artikelen.iterator();
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

    /**
     * Returns {@code true} if the iteration has more elements.
     * (In other words, returns {@code true} if {@link #next} would
     * return an element rather than throwing an exception.)
     *
     * @return {@code true} if the iteration has more elements
     */
    @Override
    public boolean hasNext() {
        return artikelen.iterator().hasNext();
    }

    /**
     * Returns the next element in the iteration.
     *
     * @return the next element in the iteration
     * @throws NoSuchElementException if the iteration has no more elements
     */
    @Override
    public Artikel next() {
        return artikelen.iterator().next();
    }
}

