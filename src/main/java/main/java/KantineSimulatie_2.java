package main.java;

import java.util.*;
import javax.persistence.Persistence;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class KantineSimulatie {

    private static final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("KantineSimulatie");
    private EntityManager manager;

    // voor administratie
    private int[] aantal;
    private double[] omzet;

    // kantine
    private final Kantine kantine;

    // kantineaanbod
    private final KantineAanbod kantineaanbod;

    // random generator
    private final Random random;

    // aantal artikelen
    private static final int AANTAL_ARTIKELEN = 4;

    // artikelen
    private static final String[] artikelnamen =
            new String[] {"Koffie", "Broodje pindakaas", "Broodje kaas", "Appelsap"};

    // prijzen
    private static final double[] artikelprijzen = new double[] {1.50, 2.10, 1.65, 1.65};

    // minimum en maximum aantal artikelen per soort (verander naar 10 en 20)
    private static final int MIN_ARTIKELEN_PER_SOORT = 10;
    private static final int MAX_ARTIKELEN_PER_SOORT = 20;

    // minimum en maximum aantal personen per dag
    private static final int MIN_PERSONEN_PER_DAG = 50;
    private static final int MAX_PERSONEN_PER_DAG = 100;

    // minimum en maximum artikelen per persoon
    private static final int MIN_ARTIKELEN_PER_PERSOON = 1;
    private static final int MAX_ARTIKELEN_PER_PERSOON = 4;

    /**
     * Constructor
     *
     */
    public KantineSimulatie() {
        kantine = new Kantine();
        random = new Random();
        int[] hoeveelheden =
                getRandomArray(AANTAL_ARTIKELEN, MIN_ARTIKELEN_PER_SOORT, MAX_ARTIKELEN_PER_SOORT);
        kantineaanbod = new KantineAanbod(artikelnamen, artikelprijzen, hoeveelheden);

        kantine.setKantineAanbod(kantineaanbod);
    }

    /**
     * Methode om een array van random getallen liggend tussen min en max van de gegeven lengte te
     * genereren
     *
     * @param lengte
     * @param min
     * @param max
     * @return De array met random getallen
     */
    private int[] getRandomArray(int lengte, int min, int max) {
        int[] temp = new int[lengte];
        for (int i = 0; i < lengte; i++) {
            temp[i] = getRandomValue(min, max);
        }

        return temp;
    }

    /**
     * Methode om een random getal tussen min(incl) en max(incl) te genereren.
     *
     * @param min
     * @param max
     * @return Een random getal
     */
    private int getRandomValue(int min, int max) {
        return random.nextInt(max - min + 1) + min;
    }

    /**
     * Methode om op basis van een array van indexen voor de array artikelnamen de bijhorende array
     * van artikelnamen te maken
     *
     * @param indexen
     * @return De array met artikelnamen
     */
    private String[] geefArtikelNamen(int[] indexen) {
        String[] artikelen = new String[indexen.length];

        for (int i = 0; i < indexen.length; i++) {
            artikelen[i] = artikelnamen[indexen[i]];

        }
        return artikelen;
    }


    /**
     * klant word hier gemaakt. bepaald welk type persoon het wordt.
     * @return klant
     */
    public Persoon klantAanmaken() {
        // maak persoon en dienblad aan, koppel ze
        int g = random.nextInt(100);
        Persoon klant;

        if (g < 89) {
            klant  = new Student();
        } else if (g < 99) {
            klant  = new Docent();
        } else if (g < 100){
            klant  = new KantineMedewerker();
        } else {
            klant  = new Persoon();
        }

        return klant;
    }

    /**
     * Deze methode simuleert een aantal dagen
     * in het verloop van de kantine
     *
     * @param dagen
     */
    public void simuleer(int dagen) {

        manager = ENTITY_MANAGER_FACTORY.createEntityManager();

        aantal = new int[dagen];
        omzet = new double[dagen];

        // for lus voor dagen
        for(int i = 0; i < dagen; i++) {

            // bepaal de kortingsproucten voor de dag
            int productenMetKorting = getRandomValue(1, artikelnamen.length);

            //lijst met producten die nog geen korting hebben
            String[] artikelLijst = artikelnamen;

            for(int g = 0; g < productenMetKorting; g++){
                int nr = getRandomValue(0, artikelnamen.length-1);

                // als artikel al korting heeft, een ander getal gebruiken
                while(artikelLijst[nr] == null){
                    nr = getRandomValue(0, artikelnamen.length - 1);
                }
                // selecteer het product en verwijder uit lijst
                String product = artikelLijst[nr];
                Artikel kortingsProduct = kantineaanbod.getArtikel(product);

                // Korting wordt verwijdert uit lijst
                artikelLijst[nr] = null;

                // korting wordt toegevoegd
                kortingsProduct.setKorting(20);

            }

            // bedenk hoeveel personen vandaag binnen lopen
            int aantalpersonen = getRandomValue(MIN_PERSONEN_PER_DAG, MAX_PERSONEN_PER_DAG);

            // laat de personen maar komen...
            for (int j = 0; j < aantalpersonen; j++) {

                // maak persoon en dienblad aan, koppel ze
                Dienblad dienblad = new Dienblad(klantAanmaken());

                // en bedenk hoeveel artikelen worden gepakt
                int aantalArtikelen = getRandomValue(MIN_ARTIKELEN_PER_PERSOON, MAX_ARTIKELEN_PER_PERSOON);

                // genereer de "artikelnummers", dit zijn indexen van de artikelnamen
                int[] tepakken = getRandomArray(
                    aantalArtikelen, 0, AANTAL_ARTIKELEN-1);

                // vind de artikelnamen op basis van de indexen hierboven
                String[] artikelen = geefArtikelNamen(tepakken);

                // loop de kantine binnen, pak de gewenste artikelen, sluit aan
                kantine.loopPakSluitAan(dienblad, artikelen);
            }


            // verwerk rij voor de kassa
            try {
                kantine.verwerkRijVoorKassa();
            } catch(TeWeinigGeldException e){
                System.out.println(e.getMessage());
            }

            // einde van de dag worden alle kortingen weer verwijdert
            for(int g = 0; g < artikelnamen.length; g++){
                kantineaanbod.getArtikel(artikelnamen[g]).setKorting(0);
            }

//            // druk de dagtotalen af en hoeveel personen binnen zijn gekomen
//            System.out.println("€" + kantine.getKassa().hoeveelheidGeldInKassa() + " omzet.");
//            System.out.println(aantalpersonen + " bezoekers.");
//            System.out.println(kantine.getKassa().aantalArtikelen() + " artikelen verkocht");
//
//            // reset de kassa voor de volgende dag
//            omzet[i] = kantine.getKassa().hoeveelheidGeldInKassa();
//            aantal[i] = kantine.getKassa().aantalArtikelen();
//            kantine.getKassa().resetKassa();

        }
//        // Gemiddelde aantal gepasseerde artikelen per dag
//        double gemiddeldeAantal = Administratie.berekenGemiddeldAantal(aantal);
//        // Gemiddelde dagomzet
//        double gemiddeldeOmzet = Administratie.berekenGemiddeldeOmzet(omzet);
//        // Dagomzet per weekdag.
//        double[] totaleDagomzet = Administratie.berekenDagOmzet(omzet);

        manager.close();
        ENTITY_MANAGER_FACTORY.close();

//        System.out.println("Simulatie Afgelopen");
//        System.out.println("Totale dagomzet maandagen: " + totaleDagomzet[0]);
//        System.out.println("Totale dagomzet Dinsdagen: " + totaleDagomzet[1]);
//        System.out.println("Totale dagomzet Woensdagen: " + totaleDagomzet[2]);
//        System.out.println("Totale dagomzet Donderdagen: " + totaleDagomzet[3]);
//        System.out.println("Totale dagomzet Vrijdagen: " + totaleDagomzet[4]);
//        System.out.println("Totale dagomzet Zaterdagen: " + totaleDagomzet[5]);
//        System.out.println("Totale dagomzet Zondagen: " + totaleDagomzet[6]);
//        System.out.println("--------------------------------------------------------------");
//        System.out.println("gemiddeld aantal verkochte artikelen per dag: " + gemiddeldeAantal);
//        System.out.println("gemiddelde omzet per dag: " + gemiddeldeOmzet);

    }
}