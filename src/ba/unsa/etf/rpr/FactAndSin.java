package ba.unsa.etf.rpr;
import java.lang.Math;

public class FactAndSin {
    public static double factorial(int n){
        int k = 1;
        for(int i = 1; i <= n; i++){
            k*=i;
        }
        return k;

    }

    public static double sinus(int n){
        return Math.sin(n);
    }
}
