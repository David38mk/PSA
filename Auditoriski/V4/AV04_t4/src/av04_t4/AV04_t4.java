package av04_t4;

import java.util.LinkedList;
import java.util.Queue;

public class AV04_t4 {
    public static void main(String[] args) {
        Queue<Integer> queue = new LinkedList<Integer>();
        
        for (int i = 0; i < 5; i++) {
            queue.add(i);
        }
        
        while (!queue.isEmpty()) {
            System.out.print(queue.peek() + ", ");
            queue.remove();
        }
    }
    
}
