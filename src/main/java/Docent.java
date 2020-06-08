package main.java;

public class Docent extends Persoon implements KortingskaartHouder{
    private String afkorting;
    private String afdeling;

    public Docent(String BSN, String voornaam, String achternaam, Datum geboortedatum, char geslacht, String afkorting, String afdeling) {
        super(BSN, voornaam, achternaam, geboortedatum, geslacht);
        this.afkorting = afkorting;
        this.afdeling = afdeling;
    }

    public Docent() {
        this.afkorting = null;
        this.afdeling = null;
    }

    public String getAfkorting() {
        return afkorting;
    }

    public void setAfkorting(String afkorting) {
        this.afkorting = afkorting;
    }

    public String getAfdeling() {
        return afdeling;
    }

    public void setAfdeling(String afdeling) {
        this.afdeling = afdeling;
    }

    @Override
    public double geefKortingsPercentage() {
        return 25;
    }

    @Override
    public boolean heeftMaximum() {
        return true;
    }

    @Override
    public double geefMaximum() {
        return 1.00;
    }
}
