package ba.unsa.etf.rpr;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.io.ObjectOutputStream;
import static junit.framework.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LaptopDaoXMLFileTest {


    private static ArrayList<Laptop> laptopi;
    @BeforeAll
    static void dodajLaptope(){
        laptopi = new ArrayList<>();
        laptopi.add(new Laptop("HP","model1",123,1,1,1,"procesor1","gk1",1));
        laptopi.add(new Laptop("dell","model2",456,2,2,2,"procesor2","gk2",2));
        laptopi.add(new Laptop("asus","model3",789,3,3,3,"procesor3","gk3",3));
    }

    @Test
    void dodajLaptopUListu() {
        ArrayList<Laptop> ln = new ArrayList<>();
        LaptopDaoXMLFile l = new LaptopDaoXMLFile(null);
        l.napuniListu(ln);
        Laptop l2 = new Laptop("lenovo","model4",147,4,4,4,"procesor4","gk4",4);
        l.dodajLaptopUListu(l2);
        assertEquals(1, ln.size());
        assertEquals(l2, ln.get(0));
    }
    @Test
    void getLaptopTest() throws NeodgovarajuciProcesorException {
        LaptopDaoXMLFile m = mock(LaptopDaoXMLFile.class);
        Laptop l = new Laptop("lenovo", "model4", 147, 4, 4, 4, "procesor4", "gk4", 4);
        when(m.getLaptop("procesor4")).thenReturn(l);
        try {
            Laptop stv = m.getLaptop("procesor4");
            assertEquals(l, stv);
        } catch (NeodgovarajuciProcesorException e) {
            e.printStackTrace();
        }
    }

    @Test
    void napuniListu() {
        LaptopDaoXMLFile xmlf = new LaptopDaoXMLFile(new File("laptopiT.xml"));
        xmlf.napuniListu(laptopi);
        for(Laptop l : laptopi){
            try{
                Laptop provj = xmlf.getLaptop(l.getProcesor());
                assertEquals(l,provj);
            } catch (NeodgovarajuciProcesorException e) {
                e.printStackTrace();
            }

        }

    }
}