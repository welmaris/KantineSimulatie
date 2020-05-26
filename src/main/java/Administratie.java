package main.java;

public class Administratie {

    /**
     * Deze methode berekent van de int array aantal de gemiddelde waarde
     *
     * @param aantal
     * @return het gemiddelde
     */
    public double berekenGemiddeldAantal(int[] aantal) {

        double totaal = 0;
        double hoeveelheid = aantal.length;

        // Waarde wordt toegevoegd aan het totaalbedrag
        for(int i = 0; hoeveelheid > i; i++){
            double bedrag = aantal[i];
            totaal += bedrag;
        }

        // Gemiddelde wordt berekend
        double gemiddelde = totaal / hoeveelheid;

        return gemiddelde;
    }

    /**
     * Deze methode berekent van de double array omzet de gemiddelde waarde
     *
     * @param omzet
     * @return het gemiddelde
     */
    public double berekenGemiddeldeOmzet(double[] omzet) {

        double totaal = 0;
        double hoeveelheid = omzet.length;
        double gemiddelde = 0;

        if(hoeveelheid > 0) {
            // Waarde wordt toegevoegd aan het totaalbedrag
            for(int i = 0; hoeveelheid > i; i++) {
                double bedrag = omzet[i];
                totaal += bedrag;
            }

            // Gemiddelde wordt berekend
            gemiddelde = totaal / hoeveelheid;
        }
        return gemiddelde;
    }

    /**
     * Methode om dagomzet uit te rekenen
     *
     * @param omzet
     * @return array (7 elementen) met dagomzetten
     */

    public static double[] berekenDagOmzet(double[] omzet) {
        double[] temp = new double[7];
        for(int i = 0; i < 7; i++) {

            int j = 0;
            while ( temp.length > j ) {
                temp[i] += omzet[i + 7 * j];

                // omitted

            }
        }
        return temp;
    }
}
