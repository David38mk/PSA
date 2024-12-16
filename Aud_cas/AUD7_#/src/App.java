
import com.sun.net.httpserver.BasicAuthenticator;
import java.util.BitSet;
import java.util.Scanner;

class Map<K extends Comparable<K>, E>{
    public K key;
    public E value;

    public Map(K key, E value){
        this.key = key;
        this.value = value;
    }
}

class OAHT<K extends Comparable<K>, E>{
    private Map<K,E> [] oatable;
    private int num_elements = 0;

    public OAHT(int n){
        oatable = new Map[n];
    }   

    private int hash(K key){
        return Math.abs(key.hashCode()) % oatable.length;
    }

    public Map<K,E> getSlot(int i){
        return oatable[i];
    }

    public int find (K look){
        int h = hash(look);
        int num_searches = 0;
        for(;;){
            Map<K,E> old = oatable[h];
            if(old == null){
                return -1;
            }else if(old.key.equals(look)){
                return h;
            }else{
                h = (h+1) % oatable.length;
                num_searches++;

                if(num_searches == oatable.length){
                    return -1;
                }
            }
        }
    }   

    public void insert(K key, E value){
        Map<K, E> newEntry = new Map<K,E>(key, value);

        int h = hash(key);

        int num_searches = 0;

        for(;;){
            Map<K,E> old = oatable[h];
            if(old == null){
                oatable[h]= newEntry;
                num_elements++;
                return;
            }else if (key.equals(old.key)) {
                oatable[h] = newEntry;
                return;
            }else{
                h = (h+1) % oatable.length;
                num_searches++;
                if(num_searches == oatable.length){
                    return;
                }
            }
        }
    }

    public void delete(K key){
        int h = hash(key);
        int num_searches = 0;

        for(;;){
            Map<K,E> old = oatable[h];
            if(key.equals(old.key)){
                oatable[h]= null;
                num_elements--;
                return;
            }else{
                h = (h+1) % oatable.length;
                num_searches++;
                if(num_searches==oatable.length){
                    return;
                }
            }
        }
    }
}

public class App {
    public static void main(String[] args) throws Exception {
        Scanner input = new Scanner(System.in);

        int n = input.nextInt();
        input.next();

        OAHT<String, String[]> base = new OAHT(n);
    
        for(int i=0;i<3;i++){
            String[] red = input.nextLine().split(" ");
            base.insert(red[2],red);
        }

        String findKey = input.next();

        int pom = base.find(findKey);
        if(pom!= -1){
            System.out.println(base.getSlot(pom).toString());
        }
    }
}
