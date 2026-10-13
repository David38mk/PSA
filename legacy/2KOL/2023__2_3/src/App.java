import java.util.ArrayList;
import java.util.Scanner;

class Map<K extends Comparable<K>, E> {
    public K key;
    public E value;
    
    public Map(K key, E value) {
        this.key = key;
        this.value = value;
    }
}

class SLLNode<E> {
    public E info;
    public SLLNode<E> next;
    
    public SLLNode(E info, SLLNode<E> next) {
        this.info = info;
        this.next = next;
    }
}

class SLLHT<K extends Comparable<K>, E> {
    public SLLNode<Map<K, E>>[] htable;
    
    public SLLHT(int n) {
        htable = new SLLNode[n];
        for (int i = 0; i < n; i++) {
            htable[i] = null;
        }
    }
    
    private int hash(K key) {
        return Math.abs(key.hashCode()) % htable.length;
    }
    
    public SLLNode<Map<K, E>> find(K look) {
        int h = hash(look);
        
        for (SLLNode<Map<K, E>> node = htable[h]; node != null; node = node.next) {
            if (look.equals(node.info.key)) {
                return node;
            }
        }
        
        return null;
    }
    
    public void insert(K key, E value) {
        Map<K, E> entry = new Map(key, value);
        
        int h = hash(key);
        
        for (SLLNode<Map<K, E>> node = htable[h]; node != null; node = node.next) {
            if (key.equals(node.info.key)) {
                node.info = entry;
                return;
            }
        }
        
        htable[h] = new SLLNode<Map<K, E>> (entry, htable[h]);
    }
    
    public void delete (K key) {
        int h = hash(key);
        
        for (SLLNode<Map<K, E>> pred = null, node = htable[h]; node != null; 
                pred = node, node = node.next) {
            if (key.equals(node.info.key)) {
                if (pred == null) {
                    htable[h] = node.next;
                } else {
                    pred.next = node.next;
                }
                return;
            }
        }
    }
}

class Korisnik{
    public String Kime;
    public int Lozinka;
    public ArrayList<Korisnik> lista_prijateli = new ArrayList<Korisnik>();

    public Korisnik(String Kime, int Lozinka){
        this.Kime = Kime;
        this.Lozinka = Lozinka;
    }
}

class Spodeluvanje{
    public Korisnik user;
    public String date;
    public int [][][] mat = new int[256][256][3];

    public Spodeluvanje(Korisnik user, String date){
        this.user = user;
        this.date = date;
    }    
}

public class App {
    public static void main(String[] args) throws Exception {
        Korisnik [] kor = new Korisnik[4];
        kor[0] = new Korisnik("David", 0000);
        kor[1] = new Korisnik("Mihajlo",0001);
        kor[2] = new Korisnik("Tomi", 0002);
        kor[3] = new Korisnik("Viki", 0003);
        
        Spodeluvanje [] spodeluvanja = new Spodeluvanje[4];

        spodeluvanja[0] = new Spodeluvanje(kor[0], "01/01/2024 14:24");
        spodeluvanja[1] = new Spodeluvanje(kor[0], "01/01/2024 17:24");
        spodeluvanja[2] = new Spodeluvanje(kor[1], "01/01/2024 14:24");
        spodeluvanja[3] = new Spodeluvanje(kor[1], "01/01/2024 17:24");

        SLLHT<String,Korisnik> tabelaK = new SLLHT<String, Korisnik>(4);

        tabelaK.insert(kor[0].Kime, kor[0]);
        tabelaK.insert(kor[1].Kime, kor[1]);
        tabelaK.insert(kor[2].Kime, kor[2]);
        tabelaK.insert(kor[3].Kime, kor[3]);
        
        SLLHT<String,Spodeluvanje> tabelaS = new SLLHT<String, Spodeluvanje>(4);

        tabelaS.insert(f(spodeluvanja[0].user,spodeluvanja[0].date), spodeluvanja[0]);
        tabelaS.insert(f(spodeluvanja[1].user,spodeluvanja[1].date), spodeluvanja[1]);
        tabelaS.insert(f(spodeluvanja[2].user,spodeluvanja[2].date), spodeluvanja[2]);
        tabelaS.insert(f(spodeluvanja[3].user,spodeluvanja[3].date), spodeluvanja[3]);




        Scanner s = new Scanner(System.in);

        String ime = s.nextLine();
        String datum = s.nextLine();
        String date = datum.split(" ")[0];

        SLLNode<Map<String,Korisnik>> BaranKorisnik = tabelaK.find(ime);
        if(BaranKorisnik!=null){
            SLLNode<Map<String,Spodeluvanje>> BaranoSpodeluvanje = tabelaS.find(f(BaranKorisnik.info.value,datum));
            if(BaranoSpodeluvanje!=null){
                for(int i=0;i<tabelaS.htable.length;i++){
                    for(SLLNode<Map<String,Spodeluvanje>> node = tabelaS.htable[i]; node!=null; node = node.next){
                        if(node.info.value.date.contains(date) && node.info.value.user.Kime.equals(ime)){
                            System.out.println(node.info.value.date + " " + node.info.value.user.Kime);
                        }
                    }
                    
                }
            }else{
                System.out.println("Baraniot datum e navaliden");
            }
        }else{
            System.out.println("Baraniot korisnik ne postoi");
        }

    }

    private static String f(Korisnik user, String datum){
        return user.Kime.trim() + " " + datum.trim();
    }
}
