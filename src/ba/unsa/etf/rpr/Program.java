package ba.unsa.etf.rpr;

import java.util.Scanner;
import java.util.Set;

public class Program {
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Imenik imenik = new Imenik();

        imenik.dodaj("Amila",new FiksniBroj(Grad.BANOVICI,"123-456"));
        imenik.dodaj("Rijad",new FiksniBroj(Grad.BANOVICI,"123-789"));
        imenik.dodaj("Dalila",new MobilniBroj(62,"456-369"));
        imenik.dodaj("Sara",new MobilniBroj(63,"741-852"));
        imenik.dodaj("Una",new MedunarodniBroj("+387","8529-63"));
        imenik.dodaj("Amina",new MedunarodniBroj("+387","567-890"));

        System.out.println("Odaberite zeljenu radnju: dodaj, dajBroj, dajIme, naSlovo" +
                ",izGrada, izGradaBrojevi, imenik, kraj");
        while(true){
            String s = scanner.nextLine();
            switch(s){
                case "dodaj":
                    System.out.println("Unesite ime");
                    String ime = scanner.nextLine();
                    TelefonskiBroj br = unosTelBr();
                    imenik.dodaj(ime,br);
                    break;
                case "dajBroj":
                    System.out.println("Unesite ime");
                    String i = scanner.nextLine();
                    String zeljenoIme = imenik.dajBroj(i);
                    System.out.println(zeljenoIme == null? "nema imena " : zeljenoIme);
                    break;
                case "dajIme":
                    System.out.println("Unesite broj");
                    TelefonskiBroj bt = unosTelBr();
                    String imee = imenik.dajIme(bt);
                    if (imee == null)System.out.println("Nije u imeniku!");
                    else System.out.println("Ime " + imee + " broj telefona "+ bt.ispisi());
                    break;
                case "naSlovo":
                    System.out.println("Unesite slovo");
                    String unosSL = scanner.nextLine();
                    String rez = imenik.naSlovo(unosSL.toCharArray()[0]);
                    System.out.println(rez);
                    break;
                case "izGrada":
                    System.out.println("Unesite grad");
                    String unosG = scanner.nextLine();
                    try{
                       Grad g = Grad.valueOf(unosG);
                       Set<String> rezGR = imenik.izGrada(g);
                       System.out.println(rezGR);
                    } catch(Exception exc){
                        System.out.println("Neispravan unos!");
                    }
                    break;
                case "izGradaBrojevi":
                    System.out.println("Unesite grad");
                    String grad = scanner.nextLine();
                    try{
                        Grad g = Grad.valueOf(grad);
                        Set<TelefonskiBroj> t = imenik.izGradaBrojevi(g);
                        for(TelefonskiBroj tbr : t)
                        System.out.println(tbr.ispisi());
                    }catch(Exception exc){
                        System.out.println("Neispravan unos!");
                        return;
                    }
                    break;
                case "imenik":
                    System.out.println(imenik.toString());
                    break;
                case "kraj":
                    System.exit(0);
                    break;

                default:
                    System.out.println("Neispravan unos, molimo ponovite!");

            }

        }
    }



    private static TelefonskiBroj unosTelBr() {
        System.out.println("Odaberite tip broja fiksni, mobilni, međunarodni, kraj");
        String s = scanner.nextLine();
        switch(s){
            case "fiksni":
                System.out.println("Unesite pozivni");
                String poz = scanner.nextLine();
                System.out.println("Unesite broj");
                String brT = scanner.nextLine();
                return new FiksniBroj(Grad.izPozivnog(poz),brT);

            case "mobilni":
                System.out.println("Unesite mrezu");
                int mr = scanner.nextInt();
                System.out.println("Unesite broj");
                String brTl = scanner.nextLine();
                return new MobilniBroj(mr,brTl);


            case "međunarodni":
                System.out.println("Unesite pozivni");
                String pz = scanner.nextLine();
                System.out.println("Unesite broj");
                String brTlf = scanner.nextLine();
                return new MedunarodniBroj(pz,brTlf);


            case "kraj":
                return null;
        }
        return null;
    }
}
