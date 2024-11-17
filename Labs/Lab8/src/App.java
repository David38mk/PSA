
import java.util.ArrayList;

class Spisanija{
    int h, w;
    public Spisanija(int h, int w){
        this.h = h;
        this.w = h;
    }
}

public class App {
    public static void main(String[] args) throws Exception {
        ArrayList<Spisanija> spis = new ArrayList<Spisanija>();
        Spisanija s1 = new Spisanija(30, 2);
        Spisanija s2 = new Spisanija(12, 4);
        Spisanija s3 = new Spisanija(16, 9);
        Spisanija s4 = new Spisanija(24, 6);
        Spisanija s5 = new Spisanija(5, 8);
        
        spis.add(s1);
        spis.add(s2);
        spis.add(s3);
        spis.add(s4);
        spis.add(s5);

        sort(spis);


        
        int currentWidth = 0; 
        int currentHeight = 0; 
        int totalHeight = 0;  

        for (Spisanija sp : spis) {
            if (currentWidth + sp.w <= 10) {
                currentWidth += sp.w;
                currentHeight = Math.max(currentHeight, sp.h); 
            } else {
                totalHeight += currentHeight;
                currentWidth = sp.w;
                currentHeight = sp.h;
            }
        }

        
        totalHeight += currentHeight;

        System.out.println(" Min pol " + totalHeight);
        

        }
        
        private static void sort(ArrayList<Spisanija> spis) {
            for(int i = 0;i<spis.size()-1;i++){
                Spisanija pom = spis.get(i);
                for(int j=i+1;j<spis.size();j++){
                    Spisanija pom1 = spis.get(j);
                    if(pom1.h>pom.h){
                        Spisanija pom3 = pom;
                        pom = pom1;
                        pom1 = pom3;
                    }
                }
            }
        }
}
