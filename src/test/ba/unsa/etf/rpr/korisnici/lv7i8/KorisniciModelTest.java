package ba.unsa.etf.rpr.korisnici.lv7i8;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class KorisniciModelTest {

    private KorisniciModel model;

    @BeforeEach
    public void setUp() {
        model = new KorisniciModel();
    }

    @Test
    void napuniTest() {
        model.napuni("Amila", "Kukic", "ak@mail.com", "akukic", "password");

        ObservableList<Korisnik> korisnici = model.getSviKorisnici();
        assertEquals(1, korisnici.size());

        Korisnik dodaniKorisnik = korisnici.get(0);
        assertEquals("Amila", dodaniKorisnik.getIme());
        assertEquals("Kukic", dodaniKorisnik.getPrezime());
        assertEquals("ak@mail.com", dodaniKorisnik.getEmail());
        assertEquals("akukic", dodaniKorisnik.getKorisnickoIme());
        assertEquals("password", dodaniKorisnik.getLozinka());
    }

    @Test
    public void testSetTrIzabraniKorisnik() {
        Korisnik korisnik = new Korisnik("Amila", "Kukic", "ak@mail.com", "akukic", "password");
        model.setTrIzabraniKorisnik(korisnik);

        assertEquals(korisnik, model.getTrIzabraniKorisnik());
    }

    @Test
    public void testGetSviKorisnici() {
        ObservableList<Korisnik> korisnici = model.getSviKorisnici();
        assertNotNull(korisnici);
        assertEquals(0, korisnici.size());


        model.napuni("Amila", "Kukic", "ak@mail.com", "akukic", "password");
        assertEquals(1, korisnici.size());
    }
}