package main.java;

import java.util.Random;

public class Kantine {

    private final Kassa kassa;
    private final KassaRij kassarij;
    private Dienblad dienblad;
    private Random random;
    private KantineAanbod kantineAanbod;


    /**
     * Constructor voor de Kantine. Heeft geen parameters nodig.
     * Maakt een kassa met een kassarij.
     */
    public Kantine() {
        kassarij = new KassaRij();
        kassa = new Kassa(kassarij);
        random = new Random();
    }

    //het kiezen van een random nunmmer die we hierna aan een type klant koppelen
    public Persoon klantAanmaken() {
       int i = random.nextInt(100);

        if (i==99) {
            return new KantineMedewerker();
        }

        else if (i<10) {
            return new Docent();
        }

        else{
            return new Student();
        }

    }
    /**
     * In deze methode wordt een Persoon en Dienblad gemaakt en aan elkaar gekoppeld. Maak twee
     * Artikelen aan en plaats deze op het dienblad. Tenslotte sluit de Persoon zich aan bij de rij
     * voor de kassa.
     */
    public void loopPakSluitAan() {
        dienblad.setKlant(new Persoon());
        dienblad.voegToe(new Artikel());
        dienblad.voegToe(new Artikel());
        kassarij.sluitAchteraan(dienblad);
    }


    /**
     * In deze methode wordt een dienblad met artikelen
     * in de kassarij geplaatst
     * @param dienblad
     * @param artikelnamen
     */
    public void loopPakSluitAan(Dienblad dienblad, String[] artikelnamen){
        // zet dienblad
        this.dienblad = dienblad;

        // voeg artikelen toe aan dienblad
        for(int i = 0; i < artikelnamen.length; i++){
            
            dienblad.voegToe(kantineAanbod.getArtikel(artikelnamen[i]));
        }

        // sluit achteraan in de rij
        kassarij.sluitAchteraan(dienblad);
    }

    /**
     * Deze methode handelt de rij voor de kassa af.
     */
    public void verwerkRijVoorKassa() {
        while (kassarij.erIsEenRij()) {
            kassarij.eerstePersoonInRij();
        }
    }

    /**
     * Getter voor de kassa van de Klasse Kassa
     * @return kassa
     */
    public Kassa getKassa() {
        return kassa;
    }

    public void setKantineAanbod(KantineAanbod kantineAanbod) {
        this.kantineAanbod = kantineAanbod;
    }
}
