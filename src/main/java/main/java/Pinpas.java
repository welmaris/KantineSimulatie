package main.java;

public class Pinpas extends Betaalwijze {

    private double kredietlimiet;

    /**
     * Methode om kredietlimiet te zetten
     *
     * @param kredietlimiet
     */
    public void setKredietLimiet(double kredietlimiet) {
        this.kredietlimiet = kredietlimiet;
    }

    /**
     * Methode om betaling af te handelen
     */
    public void betaal(double tebetalen) {
//        return (saldo+kredietlimiet > tebetalen);
        if (saldo+kredietlimiet < tebetalen){
            throw new TeWeinigGeldException("onvoldoende Saldo");
        }
    }
}
