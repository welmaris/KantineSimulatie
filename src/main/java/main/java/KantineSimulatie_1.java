package main.java;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.EntityManager;

public class KantineSimulatie {

    private static final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("KantineSimulatie");
    private EntityManager manager;

    private final Kantine kantine;

    public static final int DAGEN = 7;

    /**
     * Constructor
     */
    public KantineSimulatie() {
        manager = ENTITY_MANAGER_FACTORY.createEntityManager();
        kantine = new Kantine(manager);
    }

    /**
     * Deze methode simuleert een aantal dagen in het
     * verloop van de kantine
     *
     * @param dagen
     */
    public void simuleer(int dagen) {

        manager = ENTITY_MANAGER_FACTORY.createEntityManager();

        // herhaal voor elke dag
        for (int i = 0; i < dagen; i++) {

            // per dag nu even vast 10 + i personen naar binnen
            // laten gaan, wordt volgende week veranderd...

            // for lus voor personen
            for (int j = 0; j < 10 + i; j++) {
                // kantine.(...);
                kantine.loopPakSluitAan();
            }

            // verwerk rij voor de kassa
            kantine.verwerkRijVoorKassa();

            kantine.getKassa();
            // toon dagtotalen (artikelen en geld in kassa)
            kantine.getKassa().hoeveelheidGeldInKassa();

            // reset de kassa voor de volgende dag
            kantine.getKassa().resetKassa();
        }

        manager.close();
        ENTITY_MANAGER_FACTORY.close();
    }

    /**
     * Start een simulatie
     */
    public static void main(String[] args) {
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
}
