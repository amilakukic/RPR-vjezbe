package ba.unsa.etf.rpr;

import java.util.ArrayList;

public class InformacijeONastavniku extends LicneInformacije implements Predstavlja,MozeOcijeniti{
    String titula;
    ArrayList<Ocjena> ocjene;

    public InformacijeONastavniku(String ime, String prezime, String titula){
        super(ime,prezime);
        this.titula=titula;
        this.ocjene=new ArrayList<>();
    }
    public String getTitula() {
        return titula;
    }

    public void setTitula(String titula) {
        this.titula = titula;
    }

    @Override
    public String predstavi() {
        return super.predstavi()+ " "+ titula;
    }

    public Ocjena ocijeni(LicneInformacije osoba, int o) {
        if (!(osoba instanceof InformacijeOStudentu)){
            System.out.println("Samo student može ocijeniti nastavnika.");
            return null;
        }
        Ocjena ocj = new Ocjena(osoba,o);
        ocjene.add(ocj);
        return ocj;
    }
}
