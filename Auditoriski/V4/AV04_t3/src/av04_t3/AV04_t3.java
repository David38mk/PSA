package av04_t3;

import java.util.Scanner;
import java.util.Stack;

public class AV04_t3 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        
        System.out.println("Vnesete postfix izraz:");
        String s = input.nextLine();
        
        System.out.println(s + " = " + evaluatePostfix(s));
    }    

    private static int evaluatePostfix(String s) {
        Stack<Integer> stack = new Stack<>();
        
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            
            if (c == ' ') {
                continue;
            } else if (Character.isDigit(c)) {
                int n = 0;
                
                while (Character.isDigit(c)) {
                    n = n * 10 + (int)(c - '0');
                    i++;
                    c = s.charAt(i);
                }
                
                i--;
                
                stack.push(n);
            } else {
                int val1 = stack.pop();
                int val2 = stack.pop();
                
                switch(c) {
                    case '+':
                        stack.push(val1 + val2);
                        break;
                    case '-':
                        stack.push(val2 - val1);
                        break;
                    case '*':
                        stack.push(val1 * val2);
                        break;
                    case '/':
                        stack.push(val2 / val1);
                        break;
                }
            }
        }
        
        return stack.pop();
    }
}
