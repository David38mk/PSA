
import java.util.Scanner;


class Map<K extends Comparable<K>, E>{
    public K key;
    public E value;

    public Map(K key, E value){
        this.key = key;
        this.value = value;
    }
}

class SLLNode<E> {
    public E info;
    public SLLNode<E> next;
    public SLLNode(E info, SLLNode<E> next){
        this.info = info;
        this.next = next;
    }
}

class SLLHT<K extends Comparable<K>, E>{
    private SLLNode<Map<K,E>>[] htable;
    public SLLHT(int n){
        htable = new SLLNode[n];
        for(int i=0;i<n;i++){
            htable[i] = null; 
        }
    }
    //hesh funkcija koja sekogas bi trebalo da e private
    private int hash(K key){
        return (key.toString().charAt(0) - 'a') % htable.length;
    }

    public SLLNode<Map<K,E>> find(K look){
        int h = hash(look);
        
        for(SLLNode<Map<K,E>> node = htable[h]; node!= null; node = node.next){
            if(look.equals(node.info.key)){
                return node;
            }
        }
        return null;
    } 

    public void insert(K key, E value){
        Map<K,E> entry = new Map(key, value);

        int h = hash(key);

        for(SLLNode<Map<K,E>> node = htable[h]; node!= null; node = node.next){
            if(key.equals(node.info.key)){
                node.info = entry;
                return;
            }
        }

        htable[h] = new SLLNode<Map<K, E>>(entry, htable[h]);
    }

    public void delete(K key){
        int h = hash(key);

        for(SLLNode<Map<K,E>> pred = null, node = htable[h];node != null; pred = node, node = node.next){
            if(key.equals(node.info.key)){
                if(pred == null){
                    htable[h] = node.next;
                }else{
                    pred.next = node.next;
                }
                return;
            }
        }

    }
}

public class App {
    public static void main(String[] args) throws Exception {
        Scanner input = new Scanner(System.in);

        int n;

        n = input.nextInt();
        input.next();

        SLLHT<String, String[]> base = new SLLHT(n);
        SLLNode<Map<String, String[]>> pom;

        for(int i=0;i<3;i++){
            String red[] = input.nextLine().split(" ");
            base.insert(red[2],red);
        }

        String key = input.next();
        pom = base.find(key);

        if(pom!=null){
            System.out.println(pom.info.value[0]+" "+pom.info.value[1]);
        }else{
            System.out.println("Pod ne postoi");
        }
    }
}
