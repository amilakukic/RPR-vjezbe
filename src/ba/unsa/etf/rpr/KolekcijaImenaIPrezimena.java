package ba.unsa.etf.rpr;

import java.util.List;

public class KolekcijaImenaIPrezimena {
    List<String> imena;
    List<String> prezimena;

    public KolekcijaImenaIPrezimena(List<String> imena, List<String> prezimena){
        this.imena=imena;
        this.prezimena=prezimena;
    }
    public int getIndexNajduzegPara(){
        int indeks=0;
        int duzina=0;
        for(int i=0;i<imena.size();i++){
            String z = imena.get(i)+prezimena.get(i);
            if(z.length()>duzina){
                duzina=z.length();
                indeks=i;
            }
        }
        return indeks;
    }
    public String getImeiPrezime(int i){
        return imena.get(i) + " " + prezimena.get(i);
    }

    public List<String> getImena() {
        return imena;
    }

    public List<String> getPrezimena() {
        return prezimena;
    }
}
