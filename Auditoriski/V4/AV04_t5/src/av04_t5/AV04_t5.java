package av04_t5;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class AV04_t5 {
    public static void main(String[] args) {
        Queue<Integer> q = new LinkedList<Integer>();
        
        for (int i = 0; i < 5; i++) {
            q.add(i);
        }
        
        Queue<Integer> q_r = mirrorQ(q);
        
        if (q_r.isEmpty()) {
            System.out.println("Originalniot red bil prazen, pa zatoa i rezultatot e vaka.");
        } else {
            while (!q_r.isEmpty()) {
                System.out.println(q_r.remove() + ", ");
            }
        }
    }

    private static Queue<Integer> mirrorQ(Queue<Integer> q) {
        int queue_size = q.size();
        Queue<Integer> q_r = new LinkedList<Integer>();
        Stack<Integer> s = new Stack<Integer>();
        int re;
        
        if (q.isEmpty()) return q;
        
        if (queue_size % 2 == 1) {
            throw new IllegalArgumentException("Dimenzijata na redicata ne mozhe da e neparen broj.");
        }
        
        int j = 0;
        int queue_half = queue_size / 2;
        
        while (j < queue_half) {
            re = q.remove();
            q_r.add(re);
            s.push(re);
            j++;
        }
        
        while (!s.isEmpty()) {
            q_r.add(s.pop());
        }
        
        j = 0;
        
        while (j < queue_half) {
            re = q.remove();
            q_r.add(re);
            s.push(re);
            j++;
        }
        
        while (!s.isEmpty()) {
            q_r.add(s.pop());
        }
        
        return q_r;
    }
}
