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
        return (Integer)key % htable.length;
    }

    public int getSize(){
        return  htable.length; 
    }

    public SLLNode<Map<K,E>> getHtable(int i){
        return htable[i];
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

class Kompanija {
    public String ime;
    public int brojID;
    public String zemja;
    public float Rating;

    public Kompanija(String ime, int brojID, String zemja, float Rating) {
        this.ime = ime;
        this.brojID = brojID;
        this.zemja = zemja;
        this.Rating = Rating;
    }
    
}

class Let {
    public String mestoPoletuvanje;
    public String destinacija;
    public int VremetraenjeMinuti;
    public int BrojPatnici;
    public Kompanija AvioKomp;
    
    public Let(String mestoPoletuvanje, int VremetraenjeMinuti, String destinacija, int BrojPatnici, Kompanija AvioKomp){
        this.mestoPoletuvanje = mestoPoletuvanje;
        this.VremetraenjeMinuti = VremetraenjeMinuti;
        this.destinacija = destinacija;
        this.BrojPatnici = BrojPatnici;
        this.AvioKomp = AvioKomp;
    }

    @Override
    public String toString(){
        return AvioKomp.ime + " " + mestoPoletuvanje + " " + destinacija; 
    }
}

public class App {
    public static void main(String[] args) throws Exception {
        Kompanija [] K = new Kompanija[2];
        K[0] = new Kompanija("TurkishAirlines", 1, "Turcija", (float) 3.6);
        K[1] = new Kompanija("KoreanAir", 2, "Korea", (float) 3.6);

        Let [] L = new Let[6];
        L[0] = new Let("Kopenhagen", 50, "Istanbul", 44, K[0]);
        L[1] = new Let("Kopenhagen", 70, "Skopje", 44, K[1]);
        L[2] = new Let("Kopenhagen", 700, "Atina", 44, K[0]);
        L[3] = new Let("Kopenhagen", 200, "Sofija", 44, K[1]);
        L[4] = new Let("Kopenhagen", 201, "Finki ;^)", 44, K[0]);
        L[5] = new Let("Kopenhagen", 350, "Helsinki", 44, K[1]);


        SLLHT<Integer, Kompanija> KompanijaHashed = new SLLHT<Integer, Kompanija>(2);

        KompanijaHashed.insert(K[0].brojID, K[0]);
        KompanijaHashed.insert(K[1].brojID, K[1]);

        SLLHT<Integer, Let> LetoviHashed = new SLLHT<Integer, Let>(6); 

        LetoviHashed.insert(K[0].brojID, L[0]);
        LetoviHashed.insert(K[0].brojID+2, L[2]);
        LetoviHashed.insert(K[0].brojID+4, L[4]);
        LetoviHashed.insert(K[1].brojID, L[1]);
        LetoviHashed.insert(K[1].brojID+2, L[3]);
        LetoviHashed.insert(K[1].brojID+4, L[5]);

        NajdiLetovi(LetoviHashed);
        
        }
        
        private static void NajdiLetovi(SLLHT<Integer, Let> letoviHashed) {            
            for(int i=0;i< letoviHashed.getSize();i++){
                for(SLLNode<Map<Integer, Let>> node = letoviHashed.getHtable(i); node!=null; node = node.next){
                    if(node.info.value.VremetraenjeMinuti<300 && node.info.value.AvioKomp.Rating > 3.5){
                        System.out.println(node.info.value);
                    }
                }
            }
        }
}
