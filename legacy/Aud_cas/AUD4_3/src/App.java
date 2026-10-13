
import java.util.Scanner;
import java.util.Stack;



public class App {
    public static void main(String[] args) throws Exception {
        Scanner input = new Scanner(System.in);
        System.out.println("Vnesete postfix izraz: ");
        String s = input.nextLine();

        System.out.println(s + " = " + evaluatePostfix(s));
    }
    private static int evaluatePostfix(String s){
        Stack<Integer> st = new Stack<Integer>();
        
        for(int i=0;i<s.length();i++){
            char c = s.charAt(i);
            
            if( c == ' '){
                continue;
            }else if(Character.isDigit(c)){
                int n = 0;
                
                while(Character.isDigit(c)){
                    n = n*10 + (int)(c -'0');
                    i++;
                    c = s.charAt(i);
                }

                i--;
                st.push(n);

            }else{
                int val1 = st.pop();
                int val2 = st.pop();

                switch (c) {
                    case '+':
                        st.push(val1+val2);
                        break;
                    case '-':
                        st.push(val1-val2);
                        break;
                    case '*':
                        st.push(val1*val2);
                        break;
                    case '/':
                        st.push(val1/val2);
                        break;
                    default:
                        throw new AssertionError();
                }
            }
        }
        return st.pop();
    }
}
