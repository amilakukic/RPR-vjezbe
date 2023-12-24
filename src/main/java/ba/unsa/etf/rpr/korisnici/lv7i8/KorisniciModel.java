package ba.unsa.etf.rpr.korisnici.lv7i8;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class KorisniciModel {

    private final ObservableList<Korisnik> kolekcijaKorisnika = FXCollections.observableArrayList();
    private final SimpleObjectProperty<Korisnik> trIzabraniKorisnik = new SimpleObjectProperty<>();

    public ObservableList<Korisnik> getKorisnici() {
        return kolekcijaKorisnika;
    }

    public Korisnik getTrIzabraniKorisnik() {
        return trIzabraniKorisnik.get();
    }

    public SimpleObjectProperty<Korisnik> trIzabraniKorisnikProperty() {
        return trIzabraniKorisnik;
    }

    public void setTrIzabraniKorisnik(Korisnik trIzabraniKorisnik) {
        this.trIzabraniKorisnik.set(trIzabraniKorisnik);
    }


    public void napuni(String ime, String prezime, String email, String korisnickoIme, String lozinka) {
        Korisnik noviKorisnik = new Korisnik(ime, prezime, email, korisnickoIme, lozinka);
        kolekcijaKorisnika.add(noviKorisnik);
        trIzabraniKorisnik.set(noviKorisnik);

        System.out.println("Dodan novi korisnik: " + noviKorisnik);
    }


    public ObservableList<Korisnik> getSviKorisnici() {
        return kolekcijaKorisnika;
    }
}
