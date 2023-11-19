package ba.unsa.etf.rpr;

public class Pobjednik {
    private String ime;
    private String prezime;
    private int brojZnakova;
    private KolekcijaImena kolekcija;

    public Pobjednik(KolekcijaImena k){
        String najduze = k.getNajduzeIme();
        String[] d = najduze.split("\\s+");
        this.ime=d[0];
        this.prezime=d[1];
        this.brojZnakova=najduze.length();
    }

    public String getIme() {
        return ime;
    }

    public int getBrojZnakova() {
        return brojZnakova;
    }

    public String getPrezime() {
        return prezime;
    }
}
