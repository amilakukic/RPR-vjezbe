package com.company;
import java.util.Scanner;
public class Sabiranje {

    public static void main(String[] args) {
        Scanner ulaz = new Scanner(System.in);
        System.out.println("Unesite prvi broj: ");
        
        int br1 = ulaz.nextInt();
        System.out.println("Unesite drugi broj: ");
        int br2 = ulaz.nextInt();
        System.out.println("Prvi broj: " + br1);
        System.out.println("Drugi broj: " + br2);
    }
}
