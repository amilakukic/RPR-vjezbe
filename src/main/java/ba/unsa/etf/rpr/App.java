package ba.unsa.etf.rpr;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class App
{
    public static void main( String[] args ) throws FileNotFoundException {
        LaptopDaoSerializableFile sf = new LaptopDaoSerializableFile(new File("laptopi.txt"));
        LaptopDaoJSONFile jf = new LaptopDaoJSONFile(new File("laptopi.json"));
        LaptopDaoXMLFile xmlf = new LaptopDaoXMLFile(new File("laptopi.xml"));

        ArrayList<Laptop> laptopi = new ArrayList<>();

        laptopi.add(new Laptop("HP","model1",123,1,1,1,"procesor1","gk1",1));
        laptopi.add(new Laptop("dell","model2",456,2,2,2,"procesor2","gk2",2));
        laptopi.add(new Laptop("asus","model3",789,3,3,3,"procesor3","gk3",3));

        sf.napuniListu(laptopi);
        jf.napuniListu(laptopi);
        xmlf.napuniListu(laptopi);

        sf.dodajLaptopUFile(new Laptop("lenovo","model4",147,4,4,4,"procesor4","gk4",4));
        jf.dodajLaptopUFile(new Laptop("lenovo","model4",147,4,4,4,"procesor4","gk4",4));
        xmlf.dodajLaptopUFile(new Laptop("lenovo","model4",147,4,4,4,"procesor4","gk4",4));

        ArrayList<Laptop> n1 = sf.vratiPodatkeIzDatoteke();
        System.out.println("Ucitani laptopi:");
        for (Laptop laptop : n1) {
            System.out.println(laptop);
        }
        ArrayList<Laptop> n2 = jf.vratiPodatkeIzDatoteke();
        for (Laptop laptop : n1) {
            System.out.println(laptop);
        }
        ArrayList<Laptop> n3 = xmlf.vratiPodatkeIzDatoteke();
        for (Laptop laptop : n1) {
            System.out.println(laptop);
        }

        try{
            Laptop l1 = sf.getLaptop("procesor1");
            System.out.println("Laptop s traženim procesorom je: " + l1);
        } catch (NeodgovarajuciProcesorException e) {
            e.printStackTrace();
        }
        try{
            Laptop l1 = jf.getLaptop("procesor1");
            System.out.println("Laptop s traženim procesorom je: " + l1);
        } catch (NeodgovarajuciProcesorException e) {
            e.printStackTrace();
        }
        try{
            Laptop l1 = xmlf.getLaptop("procesor1");
            System.out.println("Laptop s traženim procesorom je: " + l1);
        } catch (NeodgovarajuciProcesorException e) {
            e.printStackTrace();
        }

    }
}
