import java.util.LinkedList;
import java.util.Queue;
public class App {
    public static void main(String[] args) throws Exception {
        Queue<Integer> qu = new LinkedList<Integer>();
        
        for(int i=0;i<5;i++){
            qu.add(i);
        }

        while(!qu.isEmpty()){
            System.out.println(qu.peek() + ", ");
            qu.remove();
        }
    }
}
