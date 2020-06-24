package main.java;

import org.hibernate.Session;

import java.util.*;
import javax.persistence.*;

public class KantineSimulatie {

    private static final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("KantineSimulatie");
    private static final int DAGEN = 30;
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
        manager = ENTITY_MANAGER_FACTORY.createEntityManager();
        kantine = new Kantine(manager);
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

    public static void main(String[] args){
        int dagen;

            if (args.length == 0) {
            dagen = DAGEN;
        } else {
            dagen = Integer.parseInt(args[0]);
        }

        //simulate(dagen);
        KantineSimulatie simulatie = new KantineSimulatie();
            simulatie.simuleer(dagen);
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

//3
            Session session = manager.unwrap(Session.class);
            //totale omzet & toegepaste korting
//3a
            Query queryTotaleOmzet = session.createNamedQuery("SELECT SUM(totaal) FROM factuur");
            double totaleOmzet = (double) queryTotaleOmzet.getSingleResult();

            Query queryTotaleKorting = session.createNamedQuery("SELECT SUM(korting) FROM factuur");
            double totaleKorting = (double) queryTotaleKorting.getSingleResult();
//3b
            Query queryAantalFacturen = session.createNamedQuery("SELECT COUNT(*) FROM factuur");
            double aantalFacturen = (double) queryAantalFacturen.getSingleResult();

            Query queryTopDrieOmzet = session.createNamedQuery("SELECT id from factuur ORDER BY omzet");
            queryTopDrieOmzet.setMaxResults(3);
            List<Double> topDrieOmzet = queryTopDrieOmzet.getResultList();

            double gemiddeldFactuurOmzet = totaleOmzet/aantalFacturen;
            double gemiddeldFactuurKorting = totaleKorting/aantalFacturen;

            System.out.println("3a totale omzet: " + totaleOmzet);
            System.out.println("3a totale korting: " + totaleKorting);
            System.out.println("3b gemiddelde omzet: " + gemiddeldFactuurOmzet);
            System.out.println("3b gemiddelde korting: " + gemiddeldFactuurKorting);
//3c
            StringBuilder lijst = null;
            String topDrie = null;
            if(!topDrieOmzet.isEmpty()) {
                for(double omzet : topDrieOmzet) {
                    lijst.append(omzet);
                    topDrie += lijst + " - ";
                }
            }
            System.out.println("3c topOmzet: " + topDrie);

            Query queryArtikelNamen = session.createNamedQuery("SELECT artikel_naam, sum(artikel_prijs), artikel_korting");
//            List<String> artikelNamen = queryArtikelNamen.getResultList();

            Query queryTotaalPerDag = session.createNamedQuery("SELECT artikel_naam, sum(artikel.prijs), artikel_korting, GROUP BY datum");

            Query queryPopulairArtikelenTopDrie = session.createNamedQuery("SELECT artikel_naam, FROM Artikel GROUP BY artikel_naam ORDER BY artikel_naam LIMIT 3");

            Query queryOmzetArtikelenTopDrie = session.createNamedQuery("SELECT artikel_naam, FROM Artikel ORDER BY sum(artikel_prijs) DESC LIMIT 3");

        manager.close();
        ENTITY_MANAGER_FACTORY.close();

    }
}
