package ba.unsa.etf.rpr;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
	    InformacijeOStudentu student = new InformacijeOStudentu("Amila","Kukić","2023","19065");
        InformacijeONastavniku nastavnik = new InformacijeONastavniku("a","k","profesor");

        System.out.println("Student: "+ student.getIme() + " " + student.getPrezime() + " " + student.getGodinaStudija() + " "
        + student.getBrojIndexa());
        System.out.println("Nastavnik: " + nastavnik.getIme() + " " + nastavnik.getPrezime() + " " + nastavnik.getTitula());
        ArrayList<Predstavlja> poruke = new ArrayList<>();
        poruke.add(new InformacijeOStudentu("Amila","Kukić","2023","19065"));
        poruke.add(new InformacijeONastavniku("a","k","profesor"));
        poruke.add(new Predmet("rpr","razvoj programskih rjesenja"));


        KolekcijaPoruka k = new KolekcijaPoruka(poruke);

        ArrayList<String> p = k.getPoruka();
        for(String s : p)System.out.println(s);

        Predmet rpr = new Predmet("rpr","razvoj programskih rjesenja");
         Ocjena Ocjrpr= rpr.ocijeni(student,10);
        System.out.println("Ocjena za rpr: " + Ocjrpr.getOcjena());

        nastavnik.ocijeni(student,10);

    }
}
