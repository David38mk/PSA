

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println(count());
    }

    private static int count(){
        int x1, x2, y1, y2;
        int brojac = 0;
        for(x1 = 0; x1 <8;x1++){
            for(y1 = 0; y1 <8;y1++){
                for(x2 = 0; x2 <8;x2++){
                    for(y2 = 0; y2 <8;y2++){
                        if(!neSeNapagjaat(x1,x2,y1,y2)){
                            brojac++;
                        }
                    }
                }
            }   
        }
        return brojac;
    }

    private static boolean  neSeNapagjaat(int x1, int x2, int y1, int y2){
        return (x1==x2 || y1==y2 || Math.abs(x1-x2) == Math.abs(y1-y2));
    }
}
