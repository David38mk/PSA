package av03_t1;

import java.util.ArrayList;
import java.util.Scanner;

public class AV03_t1 {
    public static void main(String[] args) {
        ArrayList<Integer> prvPolinom = new ArrayList<Integer>();
        ArrayList<Integer> vtorPolinom = new ArrayList<Integer>();
        
        ArrayList<Integer> rezultat;
        
        System.out.println("Vnesi polinom eden:");
        vnesiElementi(prvPolinom);
        System.out.println("Vnesi polinom dva:");
        vnesiElementi(vtorPolinom);
        
        pechatiElementi(prvPolinom);
        pechatiElementi(vtorPolinom);
        
        rezultat = soberi(prvPolinom, vtorPolinom);
        pechatiElementi(rezultat);
    }

    static void vnesiElementi(ArrayList<Integer> al) {
        Scanner input = new Scanner(System.in);
        
        do {
            al.add(input.nextInt()); // eksponent
            al.add(input.nextInt()); // koeficient
            
            if (al.get(al.size() - 2) == 0) break;
            
            System.out.println("Vnesi nula za kraj:");
            if (input.nextInt() == 0) break;
        } while(true);
    }

    static void pechatiElementi(ArrayList<Integer> al) {
        for (int i = 0; i < al.size(); i += 2) {
            System.out.print(al.get(i + 1) + " * x^" + al.get(i) + " + ");
        }
        System.out.println();
    }

    static ArrayList<Integer> soberi(ArrayList<Integer> p1, ArrayList<Integer> p2) {
        ArrayList<Integer> rezultat = new ArrayList<Integer>();
        
        int i = 0, j = 0;
        
        while (i < p1.size() && j < p2.size()) {
            if (p1.get(i) == p2.get(j)) {
                rezultat.add(p1.get(i));
                rezultat.add(p1.get(i + 1) + p2.get(j + 1));
                i += 2;
                j += 2;
            } else if (p1.get(i) > p2.get(j)) {
                rezultat.add(p1.get(i));
                rezultat.add(p1.get(i + 1));
                i += 2;
            } else {
                rezultat.add(p2.get(j));
                rezultat.add(p2.get(j + 1));
                j += 2;
            }
        }
        
        while (i < p1.size()) {
            rezultat.add(p1.get(i));
            rezultat.add(p1.get(i + 1));
            i += 2;
        }
        
        while (j < p2.size()) {
            rezultat.add(p2.get(j));
            rezultat.add(p2.get(j + 1));
            j += 2;
        }
        
        return rezultat;
    }
    
}
