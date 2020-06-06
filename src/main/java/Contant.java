package main.java;

public class Contant extends Betaalwijze {
    /**
     * Methode om betaling af te handelen
     */
    public boolean betaal(double tebetalen) {
        // method body omitted
       this.saldo =  Betaalwijze.saldo - double tebetalen;

        return saldo;
    }
}
