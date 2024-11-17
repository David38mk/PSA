package av04_t1;

import java.util.Stack;

public class AV04_t1 {
    public static void main(String[] args) {
        Stack<Integer> s = new Stack<Integer>();
        
        for(int i = 0; i < 5; i++) {
            s.push(i);
        }
        
        int m = maxValueNoIssues(s);
        
        System.out.println(m);
        System.out.println(s.isEmpty());
        
    }

    public static int maxValue(Stack<Integer> s) {
        int maxValue = s.pop();
        
        while(!s.isEmpty()) {
            int next = s.pop();
            maxValue = Math.max(maxValue, next);
        }
        
        return maxValue;
    }

    private static int maxValueNoIssues(Stack<Integer> s) {
        Stack<Integer> backup = new Stack<Integer>();
        
        int maxValue = s.pop();
        backup.push(maxValue);
        
        while(!s.isEmpty()) {
            int next = s.pop();
            backup.push(next);
            maxValue = Math.max(maxValue, next);
        }
        
        while(!backup.isEmpty()) {
            s.push(backup.pop());
        }
        
        return maxValue;
    }
    
}
