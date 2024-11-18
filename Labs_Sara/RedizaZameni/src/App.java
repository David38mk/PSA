
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Queue<Integer> redica = new LinkedList<Integer>();
        int N;
        System.out.println("Vnesi N: ");
        Scanner s = new Scanner(System.in);
        N = s.nextInt();
        for(int i = 0;i<5;i++){
            redica.add(i+1);
        }

        Queue<Integer> novaRedica = sozdadiNovaRed(redica,N);

        while(!novaRedica.isEmpty()){
            System.out.print(novaRedica.remove()+" ");
        }
    }

    private static Queue<Integer> sozdadiNovaRed(Queue<Integer> redica, int N) {
        Queue<Integer> novaRedica = new LinkedList<Integer>();
        while(!redica.isEmpty()) {
            int elem = redica.remove();
            for(int j=0;j<N;j++){
                novaRedica.add(elem/N);
            }
        }
        return novaRedica;
    }
}
