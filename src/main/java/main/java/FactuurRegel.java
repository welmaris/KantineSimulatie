package main.java;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "factuurRegel")
public class FactuurRegel implements Serializable {

	@Id
	@GeneratedValue
	private long id;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "factuur_id")
	private Factuur factuur;
	@Embedded
	private Artikel artikel;

	public FactuurRegel() {}

	public FactuurRegel(Factuur factuur, Artikel artikel){
		this.factuur = factuur;
		this.artikel = artikel;

	}

	public Artikel getArtikel() {
		return artikel;
	}

	public void setArtikel(Artikel artikel) {
		this.artikel = artikel;
	}

	@Override
	public String toString() {
		String artikelRegel = artikel.getNaam() + " " + artikel.getPrijs();

		if(artikel.getKorting() > 0){
			artikelRegel += " " + artikel.getKorting() + "% korting.";
		}

		return artikelRegel;
	}
}