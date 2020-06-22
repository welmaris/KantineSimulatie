package main.java;

import java.util.Iterator;

public class Kassa {
    private double geldInKassa;
    private final Dienblad dienblad;
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

    }

    /**
     * Vraag het aantal artikelen en de totaalprijs op. Tel deze gegevens op bij de controletotalen
     * die voor de kassa worden bijgehouden. De implementatie wordt later vervangen door een echte
     * betaling door de persoon.
     *
     * @param klant die moet afrekenen
     */
    public void rekenAf(Dienblad klant) {
        Persoon persoon = klant.getKlant();

//        totaalPrijs = getTotaalPrijs(klant);

        double kortingsProductenPrijs = 0;
        double standaardProductenPrijs = 0;

        Iterator<Artikel> artikelIterator = klant.getArtikelIterator();

        while (artikelIterator.hasNext()) {
            Artikel artikel = artikelIterator.next();
            if(artikel.getKorting() > 0) {
                kortingsProductenPrijs += artikel.getPrijs() * (artikel.getKorting() / 100.00);
            } else {
                standaardProductenPrijs += artikel.getPrijs();
            }
        }

        if(persoon instanceof KortingskaartHouder && standaardProductenPrijs > 0){

            double KortingskaartKorting = (standaardProductenPrijs * ((KortingskaartHouder) klant).geefKortingsPercentage());
            // als er een max is aan de korting, de max wordt bereikt
            if (((KortingskaartHouder) klant).heeftMaximum() && KortingskaartKorting > ((KortingskaartHouder) klant).geefMaximum()){
                standaardProductenPrijs -= ((KortingskaartHouder) klant).geefMaximum();
            } else {

                standaardProductenPrijs -= KortingskaartKorting;
            }
        }

        totaalPrijs = standaardProductenPrijs + kortingsProductenPrijs;

        // Exception check voor TeWeinigGeldException
        try {
            klant.getKlant().getBetaalwijze().betaal(totaalPrijs);

            gepasseederArtikelen += getAantalArtikelen(klant);
            geldInKassa += totaalPrijs;

        } catch(TeWeinigGeldException e) {
            String naam;
            // als de achternaam niet volledig is ingevuld
            if(klant.getKlant().getAchternaam() == null){
                naam = "<onbekend>";
            } else {
                naam = klant.getKlant().getAchternaam();
            }
            String message = naam + " heeft " +e.getMessage();
            throw new TeWeinigGeldException(message);
        }

        // Exception check voor TeWeinigGeldException
        try {
            klant.getKlant().getBetaalwijze().betaal(totaalPrijs);
            gepasseederArtikelen += getAantalArtikelen(klant);
            geldInKassa += totaalPrijs;
        } catch(TeWeinigGeldException e) {
            String naam;
            // als de achternaam niet volledig is ingevuld
            if(klant.getKlant().getAchternaam() == null){
                naam = "<onbekend>";
            } else {
                naam = klant.getKlant().getAchternaam();
            }
            String message = naam + " heeft " +e.getMessage();
            throw new TeWeinigGeldException(message);
        }
    }

    /**
     * Methode om aantal artikelen op dienblad te tellen
     *
     * @return Het aantal artikelen
     */
    public int getAantalArtikelen(Dienblad klant) {
        int artikelenAantal = 0;
        Iterator<Artikel> artikelIterator = klant.getArtikelIterator();

        while(artikelIterator.hasNext()) {
            artikelenAantal++;
            artikelIterator.next();
        }
        return artikelenAantal;
    }

    /**
     * Methode om de totaalprijs van de artikelen op dienblad uit te rekenen
     *
     * @return De totaalprijs
     */
    public double getTotaalPrijs(Dienblad klant){
            double totaal = 0;
            //KortingskaartHouder k;
            Iterator<Artikel> artikelIterator = klant.getArtikelIterator();

            while (artikelIterator.hasNext()) {
                Artikel artikel = artikelIterator.next();
                totaal += artikel.getPrijs();
                //totaal = totaal + artikel.getPrijs() * kortingskaartHouder.geefKortingsPercentage();
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
