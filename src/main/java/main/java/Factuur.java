package main.java;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;

@Entity
@Table (name = "factuur")
public class Factuur implements Serializable{

    @Id
    @GeneratedValue
    private Long id;

    @Column (name = "datum", nullable = false)
    private LocalDate datum;

    @Column (name = "korting")
    private double korting;

    @Column (name = "totaal", nullable = false)
    private double totaal;

    @OneToMany(targetEntity = FactuurRegel.class, mappedBy = "factuur")
    private ArrayList<FactuurRegel> regels =  new ArrayList<>();

    public Factuur() {
        totaal = 0;
        korting = 0;
    }

    public Factuur(Dienblad klant, LocalDate datum) {
        this();
        this.datum = datum;

        verwerkBestelling(klant);
    }

    /**
     * Verwerk artikelen en pas korting toe.
     *
     * Zet het totaal te betalen bedrag en het
     * totaal aan ontvangen kortingen.
     *
     * @param klant
     */
    private void verwerkBestelling(Dienblad klant) {
        // method body omitted
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
        totaal = standaardProductenPrijs + kortingsProductenPrijs;
    }

    /**
    * @return het totaalbedrag
    */
    public double getTotaal() {
        return totaal;
    }

    /**
     * @return de toegepaste korting
     */
     public double getKorting() {
        return korting;
     }

    /**
     * @return een printbaar bonnetje
     */
    @Override
    public String toString() {
        String bon = "Factuur: ";
        for(int i = 0; regels.size() > i; i++){

            FactuurRegel regel = regels.get(i);
            bon +=  regel.toString() + "\n";
        }

        bon += "\n" + "totaal = " + getTotaal();

        return bon;
    }


}


