package ba.unsa.etf.rpr;
import java.io.*;
import java.util.ArrayList;

public class LaptopDaoSerializableFile implements LaptopDao {
    File file;
    ArrayList<Laptop> laptopi;

    public LaptopDaoSerializableFile(File file) {
        this.file = file;
        this.laptopi = new ArrayList<>();
    }

    @Override
    public void dodajLaptopUListu(Laptop laptop) {
        laptopi.add(laptop);
    }

    @Override
    public void dodajLaptopUFile(Laptop laptop) {
        try (ObjectOutputStream a = new ObjectOutputStream(new FileOutputStream(file))) {
            laptopi.add(laptop);
            a.writeObject(laptopi);
        } catch (IOException e) {
            System.out.println("Greska!");
            e.printStackTrace();
        }
    }

    @Override
    public Laptop getLaptop(String procesor) throws NeodgovarajuciProcesorException {
        for(Laptop l : laptopi){
            if(l.getProcesor().equals(procesor))return l;
        }
        throw new NeodgovarajuciProcesorException("Nema laptopa sa traženim procesorom: " + procesor);

    }

    @Override
    public void napuniListu(ArrayList<Laptop> laptopi) {
        this.laptopi=laptopi;
    }

    @Override
    public ArrayList<Laptop> vratiPodatkeIzDatoteke() {
        try (ObjectInputStream a = new ObjectInputStream(new FileInputStream(file))) {
            laptopi = (ArrayList<Laptop>) a.readObject();
            return laptopi;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
