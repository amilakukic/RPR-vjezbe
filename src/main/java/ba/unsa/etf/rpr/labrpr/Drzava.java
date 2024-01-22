package ba.unsa.etf.rpr.labrpr;

import javafx.beans.property.*;

public class Drzava {
    private final IntegerProperty id;
    private final StringProperty naziv;
    private final ObjectProperty<Grad> glavni_grad;

    public Drzava(int id, String naziv, Grad glavniGrad) {
        this.id = new SimpleIntegerProperty(id);
        this.naziv = new SimpleStringProperty(naziv);
        this.glavni_grad = new SimpleObjectProperty<Grad>(glavniGrad);
    }

    public Drzava(String naziv, Grad glavniGrad) {
        this.id = new SimpleIntegerProperty();
        this.naziv = new SimpleStringProperty(naziv);
        this.glavni_grad = new SimpleObjectProperty<>(glavniGrad);
    }

    public Drzava() {
        this.id = new SimpleIntegerProperty();
        this.naziv = new SimpleStringProperty();
        this.glavni_grad= new SimpleObjectProperty<>();
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

    public Grad getGlavniGrad() {
        return glavni_grad.get();
    }

    public void setGlavniGrad(Grad glavniGrad) {
        this.glavni_grad.set(glavniGrad);
    }

    @Override
    public String toString() {
        return "Drzava{id=" + id + ", naziv='" + naziv + "', glavniGradId=" + glavni_grad + '}';
    }

}
