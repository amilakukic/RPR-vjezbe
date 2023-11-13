package ba.unsa.etf.rpr;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class LaptopDaoJSONFile implements LaptopDao, Serializable {
    File file;
    ArrayList<Laptop> laptopi;

    public LaptopDaoJSONFile(File file) {
        this.file = file;
        this.laptopi = new ArrayList<>();
    }

    public LaptopDaoJSONFile() {
    }

    @Override
    public void dodajLaptopUListu(Laptop laptop) {
        laptopi.add(laptop);
    }

    @Override
    public void dodajLaptopUFile(Laptop laptop) {
        try{
            ObjectMapper a = new ObjectMapper();
            a.writeValue(new File("laptopi.json"),laptopi);
        }catch(IOException e){
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
        this.laptopi.addAll(laptopi);
    }

    @Override
    public ArrayList<Laptop> vratiPodatkeIzDatoteke() {
        ArrayList<Laptop> nl = new ArrayList<>();
        try{
            ObjectMapper a = new ObjectMapper();
            nl = a.readValue(file, new TypeReference<ArrayList<Laptop>>() {
                @Override
                public Type getType() {
                    return super.getType();
                }
            });
    }catch (IOException e){
            e.printStackTrace();
        }
        return nl;
    }
}

