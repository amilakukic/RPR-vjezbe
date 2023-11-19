package ba.unsa.etf.rpr;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<String> l = new ArrayList<>();
        l.add("Amila Kukic");
        l.add("abcd efghijklmno");

        KolekcijaImena k = new KolekcijaImena(l);
        System.out.println("Najduže ime: " + k.getNajduzeIme());

        List<String> i = new ArrayList<>();
        List<String> p = new ArrayList<>();
        i.add("Amila");
        p.add("Kukic");
        i.add("abcd");
        p.add("efg");

        KolekcijaImenaIPrezimena k2 = new KolekcijaImenaIPrezimena(i, p);
        int ix = k2.getIndexNajduzegPara();
        System.out.println("Najduži par: " + k2.getImeiPrezime(ix));

        Pobjednik pobjednik = new Pobjednik(k);
        System.out.println("Pobjednik: " + pobjednik.getIme() + " " + pobjednik.getPrezime());


    }
}
