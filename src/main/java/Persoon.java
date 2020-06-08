package main.java;

public class Persoon {

    private String BSN;
    private String voornaam;
    private String achternaam;
    private Datum geboortedatum;
    private char geslacht;
    private Betaalwijze betaalwijze;

    /**
     * constructor met parameters en geslacht controle
     *
     * @param BSN
     * @param voornaam
     * @param achternaam
     * @param geboortedatum
     * @param geslacht
     */
    public Persoon(String BSN, String voornaam, String achternaam, Datum geboortedatum, char geslacht) {
        this.BSN = BSN;
        this.voornaam = voornaam;
        this.achternaam = achternaam;
        this.geboortedatum = geboortedatum;
        if (geslacht == 'm' || geslacht == 'v') {
            this.geslacht = geslacht;
        } else {
            this.geslacht = 'o';
        }
        betaalwijze
    }

    /**
     * constructor zonder parameters
     */
    public Persoon() {
        this.BSN = null;
        this.voornaam = null;
        this.achternaam = null;
        this.geboortedatum = null; //new geboortedatum;
        this.geslacht = 'o';
    }

    public void setBSN(String BSN) {
        this.BSN = BSN;
    }

    public void setVoornaam(String voornaam) {
        this.voornaam = voornaam;
    }

    public void setAchternaam(String achternaam) {
        this.achternaam = achternaam;
    }

    public void setGeboortedatum(Datum geboortedatum) {
        this.geboortedatum = geboortedatum;
    }

    /**
     * set geslacht met controle of geslacht m of v is
     *
     * @param geslacht
     */
    public void setGeslacht(char geslacht) {
        this.geslacht = geslacht;
        switch (geslacht) {
            case 'v':
                System.out.println("Vrouw");
            break;
            case 'm':
                System.out.println("Man");
            break;
            default:
                System.out.println("Onbekend");
        }
    }

    public String getBSN() {
        return BSN;
    }

    public String getVoornaam() {
        return voornaam;
    }

    public String getAchternaam() {
        return achternaam;
    }

    public String getGeboortedatum() {
        return geboortedatum.getDatumAsString();
    }

    public char getGeslacht() {
        return geslacht;
    }

    public Betaalwijze getBetaalwijze() {
        return betaalwijze;
    }

    public void setBetaalwijze(Betaalwijze betaalwijze) {
        this.betaalwijze = betaalwijze;
    }

    @Override
    public String toString() {
        return "Persoon{" +
                "BSN='" + BSN + '\'' +
                ", voornaam='" + voornaam + '\'' +
                ", achternaam='" + achternaam + '\'' +
                ", geboortedatum='" + geboortedatum + '\'' +
                ", geslacht=" + geslacht +
                '}';
    }
}
