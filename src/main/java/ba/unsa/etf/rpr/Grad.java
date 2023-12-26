package ba.unsa.etf.rpr;

public class Grad {
    private int id;
    private String naziv;
    private int broj_stanovnika;
    private Drzava drzava;

    public Grad(int id, String naziv, int brojStanovnika, Drzava idDrzava) {
        this.id = id;
        this.naziv = naziv;
        this.broj_stanovnika = brojStanovnika;
        this.drzava = idDrzava;
    }

    public Grad() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public int getBrojStanovnika() {
        return broj_stanovnika;
    }

    public void setBrojStanovnika(int brojStanovnika) {
        this.broj_stanovnika = brojStanovnika;
    }

    public Drzava getIdDrzava() {
        return drzava;
    }

    public void setIdDrzava(Drzava idDrzava) {
        this.drzava = idDrzava;
    }

    @Override
    public String toString() {
        return "Grad{id=" + id + ", naziv='" + naziv + "', brojStanovnika=" + broj_stanovnika + ", drzavaId=" + drzava + '}';
    }
}
