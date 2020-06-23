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
    private long id;

    @Column (name = "datum", nullable = false)
    private LocalDate datum;

    @Column (name = "korting")
    private double korting;

    @Column (name = "totaal", nullable = false)
    private double totaal;

    @OneToMany(targetEntity = FactuurRegel.class, mappedBy = "factuur", cascade = CascadeType.ALL)
    private ArrayList<FactuurRegel> regels =  new ArrayList<>();

    public Factuur(){
        totaal = 0;
        korting = 0;
    }

    public Factuur(Dienblad klant, LocalDate datum){
        this();
        this.datum = datum;

        verwerkBestelling(klant);
    }

    /**
     * Verwerk artikelen en pas kortingen toe.
     *
     * Zet het totaal te betalen bedrag en
     * het totaal aan ontvangen kortingen
     *
     * @param klant
     */
    private void verwerkBestelling(Dienblad klant){
        Persoon persoon = klant.getKlant();

        double kortingsProductenPrijs = 0;
        double productenKorting = 0;
        double standaardProductenPrijs = 0;
        double kaartKorting = 0;

        Iterator<Artikel> artikelIterator = klant.getArtikelIterator();

        while (artikelIterator.hasNext()) {
            Artikel artikel = artikelIterator.next();

            // Moet nog geregeld worden met product en factuur
            regels.add(new FactuurRegel());

            if(artikel.getKorting() > 0) {
                productenKorting = artikel.getPrijs() * (artikel.getKorting() / 100.00);
                kortingsProductenPrijs += artikel.getPrijs() - productenKorting;
                korting += productenKorting;
            } else {
                standaardProductenPrijs += artikel.getPrijs();
            }
        }

        if(persoon instanceof KortingskaartHouder && standaardProductenPrijs > 0){

            kaartKorting = (standaardProductenPrijs * ((KortingskaartHouder) klant).geefKortingsPercentage());
            // als er een max is aan de korting, de max wordt bereikt
            if (((KortingskaartHouder) klant).heeftMaximum() && kaartKorting > ((KortingskaartHouder) klant).geefMaximum()){
                kaartKorting = ((KortingskaartHouder) klant).geefMaximum();
                standaardProductenPrijs -= ((KortingskaartHouder) klant).geefMaximum();
            } else {
                standaardProductenPrijs -= kaartKorting;
            }
            korting += kaartKorting;
        }

        totaal =  standaardProductenPrijs + kortingsProductenPrijs;

    }

    /**
     * @return de Korting
     */
    public double getKorting() {
        return korting;
    }

    /**
     * @return het totaalbedrag
     */
    public double getTotaal() {
        return totaal;
    }

    @Override
    public String toString() {
        String bon = "Factuur: ";
        for(int i = 0; regels.size() > i; i++){
            bon +=  regels.get(i).toString() + "\n";
        }

        bon += "\n" + "totaal = " + getTotaal() + "\n" + getKorting();

        return bon;
    }
}


