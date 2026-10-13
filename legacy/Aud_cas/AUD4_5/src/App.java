//ispitna zadaca: Mirror  na pola dvete polovini

// input: 10,50,19,54,32,67
// output: 10,50,19,19,50,10,54,32,67,76,32,54

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class App {
    public static void main(String[] args) throws Exception {
        Queue<Integer> q = new LinkedList<Integer>();

        for(int i = 0; i<4;i++){
            q.add(i);
        }

        Queue<Integer> q_r = mirrorQ(q);
        if(q_r.isEmpty()){
            System.out.println("Redot bil prazen");
        }else{
            while(!q_r.isEmpty()){
                System.out.println(q_r.remove()+ ", ");
            }
        }
    }

    private static Queue<Integer> mirrorQ(Queue<Integer> q){
        int qu_size = q.size();
        int re;
        Queue<Integer> q_r = new LinkedList();
        Stack<Integer> s = new Stack();

        if(q.isEmpty()){
            return q;
        }
        if(q.size() %2 ==1){
            throw new IllegalArgumentException("Dimenzijata na redicata ne moze da e neparen broj ");
        }

        int j = 0;
        int qu_half = qu_size/2;

        while(j<qu_half){
            re = q.remove();
            q_r.add(re);
            s.push(re);
            j++;
        }
        while(!q.isEmpty()){
            q_r.add(s.pop());
        }
        j = 0;
        while(j<qu_half){
            re = q.remove();
            q_r.add(re);
            s.push(re);
            j++;
        }
        while(!q.isEmpty()){
            q_r.add(s.pop());
        }


        return q_r;
    }
}
