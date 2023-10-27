package ba.unsa.etf.rpr;

import java.util.List;

public class CalculatorForList {
    //min
    public static Double min(List<Double> listaBr){
        Double min=Double.MAX_VALUE;
            for(Double el : listaBr){
                if(el < min)min=el;
            }

        return min;
    }
    //max
    public static Double max(List<Double> listaBr){
        Double max=Double.MIN_VALUE;
        for(Double el : listaBr){
            if(el > max)max=el;
        }

        return max;
    }
    //mean
    public static Double mean(List<Double> listaBr){
        Double m = 0.;
        for(Double el : listaBr)m+=el;
        return m/listaBr.size();
    }
    //standardna devijacija
    public static Double standardnaDevijacija(List<Double> listaBr){
        Double m = CalculatorForList.mean(listaBr);
        Double s = 0.;
        for(Double el : listaBr)s+=Math.pow(el-m,2);
        return Math.sqrt(s/listaBr.size());
    }

}
