package ba.unsa.etf.rpr;

import java.util.List;

public class KolekcijaImena {
        private List<String> imeIPrezime;

    public KolekcijaImena(List<String> imeIPrezime) {
        this.imeIPrezime = imeIPrezime;
    }

    public String getNajduzeIme(){
        String najduze ="";
           for(String s : imeIPrezime){
               if(s.length()>najduze.length())najduze=s;
           }
           return najduze;
    }
}
