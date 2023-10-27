package ba.unsa.etf.rpr;
import java.util.ArrayList;
import java.util.List;

public class Banka {
    private long brojRacuna;
    private List<Korisnik> korisnici;
    private List<Uposlenik> uposlenici;

    public Banka() {
        this.uposlenici = new ArrayList<Uposlenik>();
        this.korisnici = new ArrayList<Korisnik>();
    }


    public Korisnik kreirajNovogKorisnika(String ime,String prezime){
        Korisnik k = new Korisnik(ime,prezime);
        this.korisnici.add(k);
        return k;

    }
    public Uposlenik kreirajNovogUposlenika(String ime,String prezime){
        Uposlenik  u = new Uposlenik(ime,prezime);
        this.uposlenici.add(u);
        return u;

    }
    public Racun KreirajRacunZaKorisnika(Korisnik korisnik){
        Racun novi = null;
        //dovrsiii!!!!!!!!!!!!!
        return novi;
    }
}
