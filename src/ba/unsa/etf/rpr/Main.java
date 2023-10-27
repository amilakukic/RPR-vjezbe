package ba.unsa.etf.rpr;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Double> listBrojeva = new ArrayList<Double>();
        for(;;){
            System.out.println("Unesite broj (stop za prekid unosa)");
            String s = scanner.nextLine();
            if("stop".equalsIgnoreCase(s.trim()))break;
            try{
                Double n = Double.parseDouble(s);
                listBrojeva.add(n);
            }catch(Exception e){
                System.out.println("Niste unijeli broj");
                continue;
            }
        }
        System.out.println("MIN  " + CalculatorForList.min(listBrojeva) + " MAX " + CalculatorForList.max(listBrojeva) +
                " MEAN " + CalculatorForList.mean(listBrojeva) + " STANDARDNA DEVIJACIJA " + CalculatorForList.standardnaDevijacija(listBrojeva));

    }
}
