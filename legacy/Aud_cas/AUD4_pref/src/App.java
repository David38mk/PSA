
import java.util.Scanner;
import java.util.Stack;


//Zadaca za prefix resavanje recimo e sledniov (3+5)*2+6*7
// +*67*2+35  

public class App {
    public static void main(String[] args) throws Exception {
        System.out.print("Vnesi prefix notacija: ");
        Scanner input = new Scanner(System.in);
        String s = input.nextLine();

        System.out.print(s + "=" + evaluatePrefix(s));
    }
    private static int evaluatePrefix(String s){
        Stack<Integer> st = new Stack<Integer>();
        for(int i=s.length()-1;i>=0;i--){
            char c = s.charAt(i);
            
            if(c == ' '){
                continue;
            }else if(Character.isDigit(c)){
                int n = 0;
                int place = 1;
                while(i>=0 && Character.isDigit(s.charAt(i))){
                    c = s.charAt(i);
                    n += (int)(c -'0')*place;
                    place*=10;
                    i--;
                }
                i++;
                st.push(n);
            }else{
                if(st.size()<2){
                    throw new IllegalArgumentException("Not enought op!");
                }
                int t2 = st.pop();
                int t1 = st.pop();
                switch (c) {
                    case '+':
                        st.push(t1+t2);  
                        break;
                    case '-':
                        st.push(t1-t2);
                        break;
                    case '*':
                        st.push(t1*t2);
                        break;
                    case '/':
                        st.push(t1/t2);
                        break;
                    default:
                        throw new AssertionError();
                }
            }
        }
        return st.pop();
    }
}
