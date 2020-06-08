package main.java;

public class KantineMedewerker extends Persoon implements KortingskaartHouder{
    private int medewerkersnummer;
    private boolean magAchterKassa;
    //private Artikel artikel;

    public KantineMedewerker(String BSN, String voornaam, String achternaam, Datum geboortedatum, char geslacht, int medewerkersnummer, boolean magAchterKassa) {
        super(BSN, voornaam, achternaam, geboortedatum, geslacht);
        this.medewerkersnummer = medewerkersnummer;
        this.magAchterKassa = magAchterKassa;
    }

    public KantineMedewerker() {
        this.medewerkersnummer = 0;
        this.magAchterKassa = false;
    }

    public int getMedewerkersnummer() {
        return medewerkersnummer;
    }

    public void setMedewerkersnummer(int medewerkersnummer) {
        this.medewerkersnummer = medewerkersnummer;
    }

    public boolean isMagAchterKassa() {
        return magAchterKassa;
    }

    public void setMagAchterKassa(boolean magAchterKassa) {
        this.magAchterKassa = magAchterKassa;
    }

    @Override
    public double geefKortingsPercentage() {
        return 35;
    }

    @Override
    public boolean heeftMaximum() {
        return false;
    }

    @Override
    public double geefMaximum() {
        return 0;
    }
}
