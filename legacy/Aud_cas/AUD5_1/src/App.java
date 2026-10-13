//rastojanie megju dve tocki
class Tocka{
    public double x, y;
    Tocka(double x, double y){
        this.x = x;
        this.y = y;
    }
}

public class App {
    public static void main(String[] args) throws Exception {
        Tocka[] array = new Tocka[10];
        for(int i =0;i<10;i++){
            array[i]= new Tocka(Math.random()*10, Math.random() *20);

        }
        System.out.println(min_rast(array,10));
    }
    private static double min_rast(Tocka[] array, int n){
        int i,j;
        double pom;
        double min=Math.sqrt(Math.pow(array[0].x-array[1].x,2) + Math.pow(array[0].y-array[1].y,2));
        for(i = 0; i < n-1;i++){
            for(j = i+1;j<n;j++){
                pom = Math.sqrt(Math.pow(array[i].x-array[j].x,2) + Math.pow(array[i].y-array[j].y,2));
                if(pom<min){
                    pom = min;
                }
            }
        }        
        
        return min;
    }
}
