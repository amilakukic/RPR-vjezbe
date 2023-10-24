package ba.unsa.etf.rpr;

public class Main {

    public static void main(String[] args) {

        int a = Integer.parseInt(args[0]);
        double s = FactAndSin.sinus(a);
        double f = FactAndSin.factorial(a);


        System.out.println("Faktorijel :" + f);
        System.out.println("Sinus :" + s);
    }
}
