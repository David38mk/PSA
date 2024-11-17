package av01_t2;

class Coin {
    private final int HEADS = 0;
    private int face;
    
    public Coin() {
        flip();
    }
    
    public void flip() {
        face = (int) (Math.random() * 2);
    }
    
    public boolean isHeads() {
        return face == HEADS;
    }
    
    public String toString() {
        String faceName;
        if (face == HEADS) {
            faceName = "Heads";
        } else {
            faceName = "Tails";
        }
        
        return faceName;
    }
}

public class AV01_t2 {
    public static void main(String[] args) {
        final int num_flips = 10;
        int heads = 0, tails = 0;
        
        Coin coin = new Coin();
        
        for (int i = 0; i < num_flips; i++) {
            coin.flip();
            System.out.println(coin);
            
            if (coin.isHeads()) {
                heads++;
            } else {
                tails++;
            }
        }
        
        System.out.println("Heads: " + heads + " vs Tails: " + tails);
    }
}
