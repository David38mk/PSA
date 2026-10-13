
import java.util.Scanner;

//trifibonaci

public class App {
    public static void main(String[] args) throws Exception {
        int N;
        Scanner s = new Scanner(System.in);
        System.out.println("Vnesi broj N");
        N = s.nextInt();
        trifib(N);
    }

    private static void trifib(int N) {
        int fib=0;
        int first=0, second=1, third=1;
        if(N==1){
            fib=0;
            System.out.println("Brojot e "+fib);
        }else if(N==2){
            fib=1;
            System.out.println("Brojot e "+fib);
        }else if(N==3){
            fib=1;
            System.out.println("Brojot e "+fib);
        }else if(N>3){
            int i = 3;
            while (i<N) { 
            fib = first + second + third;
            first = second;
            second = third;
            third = fib;
            i++;
            }
            System.out.println("Brojot e "+fib);
        }else{
            fib = 0;
            System.out.println("Invalid N value");
        }
    }
}
