package main.java;

public class Persoon {

    private String BSN;
    private String voornaam;
    private String achternaam;
    private Datum geboortedatum;
    private char geslacht;

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
    }

    /**
     * constructor zonder parameters
     */
    public Persoon() {
        this.BSN = null;
        this.voornaam = null;
        this.achternaam = null;
        this.geboortedatum = null;
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
        /*if (geslacht != 'v' && geslacht != 'm') {
            System.out.println("Onbekend");
        } else if (geslacht == 'v') {
            System.out.println("Vrouw");
        } else {
            System.out.println("Man");
        }*/
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
