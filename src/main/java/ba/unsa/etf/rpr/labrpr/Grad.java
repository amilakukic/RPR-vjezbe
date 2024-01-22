package ba.unsa.etf.rpr.labrpr;
import javafx.beans.property.*;

public class Grad {
    private final IntegerProperty id;
    private final StringProperty naziv;
    private final IntegerProperty broj_stanovnika;
    private final ObjectProperty<Drzava> drzava;

    public Grad(int id, String naziv, int brojStanovnika, Drzava idDrzava) {
        this.id = new SimpleIntegerProperty(id);
        this.naziv = new SimpleStringProperty(naziv);
        this.broj_stanovnika= new SimpleIntegerProperty(brojStanovnika);
        this.drzava = new SimpleObjectProperty<Drzava>(idDrzava);
    }

    public Grad() {
        this.id = new SimpleIntegerProperty();
        this.naziv = new SimpleStringProperty();
        this.broj_stanovnika= new SimpleIntegerProperty();
        this.drzava = new SimpleObjectProperty<>();
    }

    public Grad(String nazivValue, int brStValue, Drzava drzavaValue) {
        this.id = new SimpleIntegerProperty();
        this.naziv = new SimpleStringProperty();
        this.broj_stanovnika= new SimpleIntegerProperty();
        this.drzava = new SimpleObjectProperty<>();
    }

    public int getId() {
        return id.get();
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getNaziv() {
        return naziv.get();
    }

    public void setNaziv(String naziv) {
        this.naziv.set(naziv);
    }

    public int getBrojStanovnika() {
        return broj_stanovnika.get();
    }

    public void setBrojStanovnika(int brojStanovnika) {

        this.broj_stanovnika.set(brojStanovnika);
    }

    public Drzava getIdDrzava() {
        return drzava.get();
    }

    public void setIdDrzava(Drzava idDrzava) {
        this.drzava.set(idDrzava);
    }

    @Override
    public String toString() {
        return "Grad{id=" + id + ", naziv='" + naziv + "', brojStanovnika=" + broj_stanovnika + ", drzavaId=" + drzava + '}';
    }
}
