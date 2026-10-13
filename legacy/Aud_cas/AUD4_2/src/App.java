import java.util.Stack;

public class App {
    public static void main(String[] args) throws Exception {
        Stack<Integer> s = new Stack<Integer>();
        //1, 2, 2, 3, 3, 3, 4, 4, 4, 4
        for(int i=0;i<4;i++){
            for(int j=0;j<i+1;j++){
                s.add(i);
                System.out.print(i + ", ");
            }
        }
        System.out.println("");
        
        compress(s);

        while (!s.isEmpty()) { 
            System.out.print(s.pop()+ ", ");
        }
        System.out.println("");
    }

    private static void compress(Stack<Integer> s){
        int el_val, count;
        Stack<Integer> sr = new Stack<Integer>();
        while(!s.isEmpty()){
            
            el_val = s.peek();
            count = 0;
            while(!s.isEmpty() && el_val == s.peek()){
                s.pop();
                count ++;
            }
            sr.push(el_val);
            sr.push(count);
        }
        while (!sr.isEmpty()){
            s.push(sr.pop());
        }
    }
}
