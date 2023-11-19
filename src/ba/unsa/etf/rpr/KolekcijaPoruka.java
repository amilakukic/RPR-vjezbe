package ba.unsa.etf.rpr;

import java.util.ArrayList;
import java.util.List;

public class KolekcijaPoruka {

    ArrayList<String> poruke;

    public ArrayList<String> getPoruka() {
        return poruke;
    }
    public KolekcijaPoruka(ArrayList<Predstavlja> l){ //konstruktor koji prima listu klasa koje imaju metodu predstavi()
        this.poruke = new ArrayList<>();
        for(Predstavlja pom : l){
            poruke.add(pom.predstavi());
        }

    }
}
