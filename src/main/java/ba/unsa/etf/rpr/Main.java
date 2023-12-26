package ba.unsa.etf.rpr;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        GeografijaDAO geografijaDAO = GeografijaDAO.getInstance();
        System.out.println(ispisiGradove());
        glavniGrad();
    }

    public static String ispisiGradove() {
        GeografijaDAO g = GeografijaDAO.getInstance();
        StringBuilder output = new StringBuilder();

        for (Grad grad : g.gradovi()) {
            //Drzava drzava = g.nadjiDrzavu(grad.getIdDrzava() + "");
            String red=grad.getNaziv()+"("+grad.getIdDrzava().getNaziv()+")-"+grad.getBrojStanovnika();
           /* String red = String.format("%s (%s) - %d stanovnika",
                    grad.getNaziv(), drzava.getNaziv(), grad.getBrojStanovnika());*/
            output.append(red).append("\n");
        }
        return output.toString();
    }
    public static void glavniGrad() {
        Scanner s = new Scanner(System.in);
        GeografijaDAO geografijaDAO = GeografijaDAO.getInstance();

        System.out.println("Unesite naziv države:");
        String drzava = s.nextLine();
        Grad gg = geografijaDAO.glavniGrad(drzava);
        if (gg != null) {
            System.out.println("Glavni grad države "+drzava+" je "+ gg.getNaziv());
        } else {
            System.out.println("Nepostojeća država");
        }
    }
}
