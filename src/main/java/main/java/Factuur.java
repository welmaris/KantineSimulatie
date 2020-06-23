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
        Factuur factuur;
        getTotaal();
        getKorting();
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


