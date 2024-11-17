
class Pair<T>{
    private T val1;
    private T val2;

    Pair(){
        val1 = null;
        val2 = null;
    }
    Pair(T val1, T val2){
        this.val1 = val1;
        this.val2 = val2;
    }

    public T GetVal1(){
        return val1;
    }
    public T GetVal2(){
        return val2;
    }
    public void SetVal1(T val1){
        this.val1 = val1;
    }
    public void SetVal2(T val2){
        this.val2 = val2;
    }
    public void PrintValues(){
        System.out.println("Vrednosti: " +val1+ " " + val2);
    }
}

public class App {
    public static void main(String[] args) throws Exception {
        Pair<Integer> P = new Pair();
        P.PrintValues();
        P.SetVal1(10);
        P.SetVal2(20);
        System.out.println(P.GetVal1() + P.GetVal2());
        P.PrintValues();
    }
}
