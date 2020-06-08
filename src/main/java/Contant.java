package main.java;

public class Contant extends Betaalwijze {

    /**
     * Methode om betaling af te handelen
     */
    public void betaal(double tebetalen) {
//        return (saldo > tebetalen);
        if(saldo < tebetalen){
            throw new TeWeinigGeldException("onvoldoende saldo");
        }
    }
}
