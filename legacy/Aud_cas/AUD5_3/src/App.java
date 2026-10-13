

public class App {
    public static void main(String[] args) throws Exception {
        //System.out.println(min_pari(1000));

        int [] moneti = {50,20,10,2,1};
        System.out.println(minBrPariGreedy(moneti, 10));
    }

    private static int minBrPariGreedy(int[] moneti, int suma){
        int i, j, pom, br=0;
        for (i=0;i<moneti.length -1;i++) {
            for(j=i+1;j<moneti.length;j++){
                if(moneti[i]<moneti[j]){
                    pom = moneti[i];
                    moneti[i]= moneti[j];
                    moneti[j]= pom;
                }
            }
        }
        i=0;
        while(suma>0 && i<moneti.length){
            br+= suma/moneti[i];
            suma -= (suma/moneti[i]) * moneti[i];
            i++;
        }

        return br;
    }


    private static int min_pari(int suma){
        int p1, p2, p3, p4, p5;
        int min = Integer.MAX_VALUE;
        int pom, broj;
        for(p1 = 0; p1 <= suma/50;p1++ ){
            for(p2 = 0; p2 <= suma/20;p2++){
                for(p3 = 0;p3<= suma/10;p3++){
                    for(p4 = 0;p4<= suma/2;p4++){
                        for(p5 = 0;p5<= suma;p5++){
                            pom = p1*50 + p2*20 + p3*10 + p4*2 + p5;
                            if(pom==suma){
                                broj = p1+p2+p3+p4+p5;
                                if(broj<min){
                                    min  = broj;
                                }
                            }
                        }
                    }   
                }
            }
        }
        return min;
    }
}
