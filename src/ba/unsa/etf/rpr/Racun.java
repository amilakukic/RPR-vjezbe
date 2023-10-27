package ba.unsa.etf.rpr;

public class Racun {
    private Long brojRacuna;
    private Double prekoracenje;
    private Osoba korisnikRacuna;
    private boolean odobrenjePrekoracenja;
    private double stanjeRacuna;
    public Racun(Long brojRacuna,Osoba korisnikRacuna){
        this.brojRacuna=brojRacuna;
        this.korisnikRacuna=korisnikRacuna;
    }
    private boolean provjeriOdobrenjePrekoracenja(Double iznos){
        if(iznos>this.prekoracenje)return false;
        return true;
    }
    public boolean izvrsiUplatu(Double uplata){
        this.stanjeRacuna+=uplata;
        return true;
    }
    public boolean izvrsiIsplatu(Double isplata){
        if(this.stanjeRacuna + this.prekoracenje>=isplata) {
            this.stanjeRacuna -= isplata;
            return true;
        }
        return false;
    }
    public void odobriPrekoracenje(Double prekoracenje){
       this.prekoracenje=prekoracenje;
    }

    public Long getBrojRacuna() {
        return brojRacuna;
    }

    public void setBrojRacuna(Long brojRacuna) {
        this.brojRacuna = brojRacuna;
    }

    public Osoba getKorisnikRacuna() {
        return korisnikRacuna;
    }

    public void setKorisnikRacuna(Osoba korisnikRacuna) {
        this.korisnikRacuna = korisnikRacuna;
    }

    public double getStanjeRacuna() {
        return stanjeRacuna;
    }

    public void setStanjeRacuna(double stanjeRacuna) {
        this.stanjeRacuna = stanjeRacuna;
    }

    public Double getPrekoracenje() {
        return prekoracenje;
    }

    public void setPrekoracenje(Double prekoracenje) {
        this.prekoracenje = prekoracenje;
    }
}
