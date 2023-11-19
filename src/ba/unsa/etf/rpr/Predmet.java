package ba.unsa.etf.rpr;

import java.util.ArrayList;

public class Predmet implements Predstavlja, MozeOcijeniti{
    private String naziv;
    private String opis;
    private ArrayList<Ocjena> ocjene;

    public Predmet(String naziv, String opis){
        this.naziv=naziv;
        this.opis=opis;
        this.ocjene= new ArrayList<>();
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    @Override
    public String predstavi() {
        return "Predmet: "+ naziv + " "+opis;
    }

    @Override
    public Ocjena ocijeni(LicneInformacije osoba,int o) {
        Ocjena ocj = new Ocjena(osoba,o);
        ocjene.add(ocj);
        return ocj;
    }
}
