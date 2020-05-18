package main.java;

import java.util.ArrayList;
import java.util.LinkedList;

public class KassaRij {

    private ArrayList<Dienblad> kassarij;

    /**
     * Constructor
     */
    public KassaRij() {
        // method body omitted
    kassarij.clear();
    }

    /**
     * Persoon sluit achter in de rij aan
     *
     * @param klant
     */
    public void sluitAchteraan(Dienblad klant) {
        // hier word de destbetreffende persoon achteraan in de arraylist gezet

            kassarij.add(klant);
    }

    /**
     * Indien er een rij bestaat, de eerste klant uit de rij verwijderen en retourneren. Als er
     * niemand in de rij staat geeft deze null terug.
     *
     * @return Eerste klant in de rij of null
     */
    public Dienblad eerstePersoonInRij() {
        // hier word de index 0 van de arraylist opgevraagd
      if(!kassarij.isEmpty()) {


          Dienblad eersteKlant = kassarij.get(0);
          kassarij.remove(0);
          return eersteKlant;
      }
      return null;
    }

    /**
     * Methode kijkt of er personen in de rij staan.
     *
     * @return Of er wel of geen rij bestaat
     */
    public boolean erIsEenRij() {
        // checken of arraylist null is en dan teruggeven
return !kassarij.isEmpty();
    }
}
