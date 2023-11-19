package ba.unsa.etf.rpr;

public class Ocjena {
    LicneInformacije osoba;
    int ocjena;

    public int getOcjena() {
        return ocjena;
    }

    public void setOcjena(int ocjena) {//seter za ocjenu koji dozvoljava postavljanje ocjene ako je veća od 0 i manja od 10
        if(ocjena> 0 && ocjena <=10)
        this.ocjena = ocjena;
        else System.out.println("Ocjena nije ispravna");
    }
    public Ocjena(LicneInformacije osoba, int ocjena) {//konstruktor sa parametrima osoba i ocjena
        this.osoba=osoba;
        setOcjena(ocjena);
    }

    public LicneInformacije getOsoba() {
        return osoba;
    }
}
