//Да се напише функција која како аргумент добива магацин. Функцијата треба да ја сортира содржината на магацинот така што најмалиот елемент ќе биде сместен на врвот од магацинот, а најголемиот елемент ќе биде сместен на дното. Притоа, за реализација на решението функцијата може да користи само магацини како помошна структура (дополнително дефинирање на променливи – обични целобројни или структурни не е дозволено).


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;
import java.util.jar.Attributes;

public class App {
    public static void main(String[] args) throws Exception {
        Stack<Integer> orgstack = new Stack<Integer>();
        int N,x;
        Scanner s = new Scanner(System.in);
        System.out.println("Vnesi broj na el: ");
        N = s.nextInt();
        for(int i=0;i<N;i++){
            x = s.nextInt();
            orgstack.push(x);
        }
        Sortiraj(orgstack, N);
        while(!orgstack.isEmpty()){
            System.out.println(orgstack.pop()+" ");
        }
    }
    public static void Sortiraj(Stack<Integer> org, int N){
        ArrayList<Stack> magacini = new ArrayList<Stack>();
        int temp;
        while(!org.isEmpty()){
            temp = org.pop();
            if(magacini.isEmpty()){
                Stack<Integer> s0 = new Stack<Integer>();
                s0.push(temp);
                magacini.add(s0);
            }else{
                boolean checksum = false; 
                for(int i=0;i<magacini.size();i++){
                    Stack<Integer> s1 = magacini.get(i);
                    if(temp>s1.peek()){
                        s1.push(temp);
                        checksum = true;
                        break;
                    }
                }
                if(!checksum){
                    Stack<Integer> s2 = new Stack<Integer>();
                    s2.push(temp);
                    magacini.add(s2);
                }
            }
        }
        for(int i = 0; i<N;i++){
            int max = 0;
            for(int j=0;j<magacini.size();j++){
                Stack<Integer> st = magacini.get(j);
                if(!st.isEmpty())
                {
                    if(max<st.peek()){
                        max = st.peek();
                    }
                }
            }
            for(int j=0;j<magacini.size();j++){
                Stack<Integer> st = magacini.get(j);
                if(!st.isEmpty()){
                    if(max==st.peek()){
                        org.push(st.pop());
                    }
                }
            }
        }
    }
}
