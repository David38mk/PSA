package av02_t1;

class Pair1<T> {
    public T first;
    public T second;
    
    public Pair1() {
        first = null;
        second = null;
    }
    
    public Pair1(T first, T second) {
        this.first = first;
        this.second = second;
    } 
}

class Pair2<T, U> {
    public T first;
    public U second;
    
    public Pair2() {
        first = null;
        second = null;
    }
    
    public Pair2(T first, U second) {
        this.first = first;
        this.second = second;
    } 
}

public class AV02_t1 {
    public static void main(String[] args) {
        Pair1<String> p1 = new Pair1("T1", "T2");
        Pair1<Integer> p11 = new Pair1(4, 5);
        
        Pair2<String, Integer> p2 = new Pair2("S1", 1);
        Pair2<String, String> p22;
    }
    
}
