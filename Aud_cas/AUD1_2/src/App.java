
class Coin{
    private final int HEADS = 0;
    private int value;

    Coin(){
        FLIP();
    }

    public void FLIP(){
        value = (int)(Math.random()*2);
    }

    public boolean  isHeads(){
        return value == HEADS;
    }

    public String toString(){
        if(value == HEADS){
            return "Heads";
        }else{
            return "Tails";
        }
    }
}

public class App {
    public static void main(String[] args) {
        Coin coin = new Coin();
        int heads = 0, tails = 0;
        for (int i = 0; i < 10; i++) {
            System.out.println(coin);
            if(coin.isHeads()){
                heads++;
            }else{
                tails++;
            }
            coin.FLIP();
        }
        System.out.println("Number of heads: " + heads + " and number of tails: " + tails);
    }
}