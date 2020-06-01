package main.java;

public class Student extends Persoon {
    private int studentnummer;
    private String studierichting;

    public Student(String BSN, String voornaam, String achternaam, Datum geboortedatum, char geslacht, int studentnummer, String studierichting) {
        super(BSN, voornaam, achternaam, geboortedatum, geslacht);
        this.studentnummer = studentnummer;
        this.studierichting = studierichting;
    }

    /*public Student() {
        this.studentnummer = 0;
        this.studierichting = null;
    }*/

    public int getStudentnummer() {
        return studentnummer;
    }

    public void setStudentnummer(int studentnummer) {
        this.studentnummer = studentnummer;
    }

    public String getStudierichting() {
        return studierichting;
    }

    public void setStudierichting(String studierichting) {
        this.studierichting = studierichting;
    }
}
