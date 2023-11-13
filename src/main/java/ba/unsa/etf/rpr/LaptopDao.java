package ba.unsa.etf.rpr;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public interface LaptopDao {

    void dodajLaptopUListu(Laptop laptop);
    void dodajLaptopUFile(Laptop laptop);
    Laptop getLaptop(String procesor) throws NeodgovarajuciProcesorException;
    void napuniListu(ArrayList<Laptop> laptopi);
    ArrayList<Laptop>   vratiPodatkeIzDatoteke() throws FileNotFoundException;
}
