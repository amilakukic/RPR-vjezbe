package ba.unsa.etf.rpr;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ImenikTest {

    private static Imenik imenik= new Imenik();

    @BeforeAll
    public static void popuniImenik(){
        imenik.dodaj("Amila",new FiksniBroj(Grad.BANOVICI,"123-456"));
        imenik.dodaj("Rijad",new FiksniBroj(Grad.BANOVICI,"123-789"));
        imenik.dodaj("Dalila",new MobilniBroj(62,"456-369"));
        imenik.dodaj("Sara",new MobilniBroj(63,"741-852"));
        imenik.dodaj("Una",new MedunarodniBroj("+387","8529-63"));
        imenik.dodaj("Amina",new MedunarodniBroj("+387","567-890"));
    }
    @Test
    void dodajIspravo() {
        TelefonskiBroj b = new MobilniBroj(65,"258-741");
        imenik.dodaj("Amela",b);

        String br = imenik.dajBroj("Amela");
        assertEquals(br,"065/258-741");
    }

    @Test
    void dajBrojIma() {
        String s = imenik.dajBroj("Amila");
        assertEquals(s,"035/123-456");
    }
    @Test
    void dajBrojNema() {
        String s = imenik.dajBroj("Amela");
        assertNull(s);
    }
}