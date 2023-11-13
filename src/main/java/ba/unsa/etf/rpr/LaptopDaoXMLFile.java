package ba.unsa.etf.rpr;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.*;
import java.util.ArrayList;

public class LaptopDaoXMLFile implements LaptopDao, Serializable {
    File file;
    ArrayList<Laptop> laptopi;

    public LaptopDaoXMLFile(File file) {
        this.file = file;
        this.laptopi = new ArrayList<>();
    }

    public LaptopDaoXMLFile() {
    }


    @Override
    public void dodajLaptopUListu(Laptop laptop) {
        laptopi.add(laptop);
    }

    @Override
    public void dodajLaptopUFile(Laptop laptop) {
        try{
            XmlMapper a = new XmlMapper();
            a.writeValue(new File("laptopi.xml"),laptopi);
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (IOException e) {
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
    public ArrayList<Laptop>  vratiPodatkeIzDatoteke() {
        ArrayList<Laptop> nl = new ArrayList<>();
        try{
            File f = new File("laptopi.xml");
            if(f.exists()) {
                XmlMapper a = new XmlMapper();
                nl = a.readValue(f, ArrayList.class);
            }else System.out.println("File ne postoji!");

        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return nl;
    }
}
