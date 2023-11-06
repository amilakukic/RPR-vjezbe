package ba.unsa.etf.rpr;

public enum Grad {
    BIHAC("037"),ORASJE("031"),BANOVICI("035"),ZENICA("032"),
    GORAZDE("038"),TRAVNIK("030"),MOSTAR("036"),
    SIROKI_BRIJEG("039"),SARAJEVO("033"),LIVNO("034");

    private String pozivniBroj;

    Grad(String pozivniBroj){this.pozivniBroj = pozivniBroj;}

    public static Grad izPozivnog(String pozivni){
        for(Grad g : Grad.values())if(g.getPozivniBroj().equals(pozivni))return g;

        return null;
    }

    public String getPozivniBroj() {
        return pozivniBroj;
    }
}
