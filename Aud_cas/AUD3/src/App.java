
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        ArrayList<Integer> prvpolinom = new ArrayList<Integer>();
        ArrayList<Integer> vtorpolinom = new ArrayList<Integer>();

        ArrayList<Integer> rezultat;

        System.out.println("Vnesi polinom eden:");
        vnesiElementi(prvpolinom);
        System.out.println("Vnesi polinom dva:");
        vnesiElementi(vtorpolinom);
        
        pechatiElementi(prvpolinom);
        pechatiElementi(vtorpolinom);
        
        rezultat = soberi(prvpolinom, vtorpolinom);
        pechatiElementi(rezultat);
    }

    static void vnesiElementi(ArrayList<Integer> polinom){
        Scanner s = new Scanner(System.in);
        while (true){
            polinom.add(s.nextInt());// eksponent
            polinom.add(s.nextInt());// koeficient

            if((polinom.get(polinom.size()-2)) == 0){break;}

            System.out.println("Za kraj vnesi nula");
            if(s.nextInt() == 0){
                break;
            }
        }
    }

    static void pechatiElementi(ArrayList<Integer> polinom){
        for(int i = 0; i < polinom.size(); i+=2){
            System.out.print(polinom.get(i+1) + "*x^(" + polinom.get(i) + ") + ");
        }
        System.out.println();
    }

    static ArrayList<Integer> soberi(ArrayList<Integer> pol1, ArrayList<Integer> pol2){
        ArrayList<Integer> rez = new ArrayList<Integer>();
        int i = 0, j = 0;
        
        while (i < pol1.size() && j < pol2.size()) {
            if (pol1.get(i) == pol2.get(j)) {
                rez.add(pol1.get(i));
                rez.add(pol1.get(i + 1) + pol2.get(j + 1));
                i += 2;
                j += 2;
            } else if (pol1.get(i) > pol2.get(j)) {
                rez.add(pol1.get(i));
                rez.add(pol1.get(i + 1));
                i += 2;
            } else {
                rez.add(pol2.get(j));
                rez.add(pol2.get(j + 1));
                j += 2;
            }
        }
        
        while (i < pol1.size()) {
            rez.add(pol1.get(i));
            rez.add(pol1.get(i + 1));
            i += 2;
        }
        
        while (j < pol2.size()) {
            rez.add(pol2.get(j));
            rez.add(pol2.get(j + 1));
            j += 2;
        }

        return rez;
    }
}
