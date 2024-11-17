package av04_t2;

import java.util.Stack;

public class AV04_t2 {
    public static void main(String[] args) {
        Stack<Integer> s = new Stack<Integer>();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < i + 1; j++) {
                s.add(i);
                System.out.print(i + ", ");
            }
        }
        
        System.out.println();
        
        compress(s);
        
        while (!s.isEmpty()) {
            System.out.print(s.pop() + ", ");
        }
        System.out.println();
    }

    private static void compress(Stack<Integer> s) {
        int el_val, el_count;
        Stack<Integer> sr = new Stack<Integer>();
        
        while (!s.isEmpty()) {
            el_val = s.peek();
            el_count = 0;
            
            while (!s.isEmpty() && el_val == s.peek()) {
                s.pop();
                el_count++;
            }
            
            sr.push(el_val);
            sr.push(el_count);
        }
        
        while (!sr.isEmpty()) {
            s.push(sr.pop());
        }
    }
}
