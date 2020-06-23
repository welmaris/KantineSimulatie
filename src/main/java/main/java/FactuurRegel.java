package main.java;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "factuurregel")
public class FactuurRegel implements Serializable {

	private EntityManager manager;

	@Id
	@GeneratedValue
	private long id;

	@Column(name = "factuur")
	private Factuur factuur;

	@ManyToOne(targetEntity = Artikel.class)
	private Artikel artikel;


	public FactuurRegel() {}

	public FactuurRegel(Factuur factuur, Artikel artikel){
		this.factuur = factuur;
		this.artikel = artikel;
	}

	/**
	 *
	 */
	@Override
	public String toString() {

		String artikelRegel = artikel.getNaam() + " " + artikel.getPrijs();

		if(artikel.getKorting() > 0){
			artikelRegel += " " + artikel.getKorting() + "% korting.";
		}

		return artikelRegel;
	}
}
