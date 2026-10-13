import java.util.Stack;

public class App {
    public static void main(String[] args) throws Exception {
        Stack<Integer> s = new Stack<Integer>();
        for(int i=0;i<5;i++){
            s.push(i);
        }
        int m = maxValue(s); 
        System.out.println(m);  
        System.out.println(s.isEmpty());     
    }

    public static int maxValue(Stack<Integer> s){
        Stack<Integer> backup = new Stack<Integer>();
        int maxValue = s.pop();
        backup.push(maxValue);
    
        while(!s.isEmpty()){
            int next = s.pop();
            backup.push(next);
            maxValue = Math.max(maxValue,next);
        }

        while (!backup.isEmpty()) {
            s.push(backup.pop());   
        }

        return maxValue;
    }
}

