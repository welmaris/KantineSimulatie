package main.java;

public class Administratie {

    static final int  DAYS_IN_WEEK = 7;

    private Administratie(){

    }

    /**
     * Deze methode berekent van de int array aantal de gemiddelde waarde
     *
     * @param aantal
     * @return het gemiddelde
     */
    public static double berekenGemiddeldAantal(int[] aantal) {

        double totaal = 0;
        double hoeveelheid = aantal.length;

        // voor elke dag wordt het aantal artikelen toegevoegd aan het totaal
        if(aantal.length > 0) {
            for(int i = 0; hoeveelheid > i; i++) {
                totaal += aantal[i];
            }
        }

        return (totaal/aantal.length);
    }

    /**
     * Deze methode berekent van de double array omzet de gemiddelde waarde
     *
     * @param omzet
     * @return het gemiddelde
     */
    public static double berekenGemiddeldeOmzet(double[] omzet) {
        double totaal = 0;

        if(omzet.length > 0) {
            // Waarde wordt toegevoegd aan het totaalbedrag
            for(int i = 0; omzet.length > i; i++) {
                totaal += omzet[i];
            }
        }
        return (totaal / omzet.length);
    }

    /**
     * Methode om dagomzet uit te rekenen
     *
     * @param omzet
     * @return array (7 elementen) met dagomzetten
     */

    public static double[] berekenDagOmzet(double[] omzet) {

        // Opslag voor de omzetten

        double[] temp = new double[DAYS_IN_WEEK];

        // Ga door de omzet lijst heen voor een week (7 dagen)
        for(int i = 0; i < temp.length; i++) {

            // j terugzetten op de eerste week
            int j = 0;

            // Kijken voor dag i in week j
            while( omzet.length >i + j*DAYS_IN_WEEK) {

                // Omzet toevoegen aan de dag van de week.
                temp[i] += omzet[i + DAYS_IN_WEEK * j];

                // Verder gaan naar de volgende week
                j++;
            }
        }
        return temp;
    }
}
