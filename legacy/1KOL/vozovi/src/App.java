
import java.util.ArrayList;




class Node<E>{
    protected E data;
    protected Node<E> next;

    public Node(){
        data=null;
        next=null;
    }
    public Node(E data, Node<E> next){
        this.data = data;
        this.next = next;
    }
}

class SlinkedList<E>{
    private Node<E> head;

    public Node<E> getHead()
    {
        return head;
    }

    public SlinkedList(){
        head = null;
    }
    public void InsertFirst(E e){
        Node<E> first = new Node(e, head);
        head = first;
    }
    public void InsertAfter(E e, Node<E> n){
        if(n!= null){
            Node<E> node = new Node(e, n.next);
            n.next = node;
        }else{
            System.out.println("Error");
        }
    }
    public void InsertBefore(E e, Node<E> n){
        if(head!=null){
            Node<E> temp = head;
            if(temp==n){
                this.InsertFirst(e);
                return;
            }
            while (temp.next != n && temp.next!=null) {
                temp = temp.next; 
            }
            if(temp.next == n){
                Node<E> node = new Node(e, n);
                temp.next = node;
            }
        }
    }
    public void InsertLast(E e){
        if(head!=null){
            Node<E> temp = head;
            while(temp.next!=null){
                temp = temp.next;
            }
            Node<E> last = new Node(e, null);
            temp.next = last;
        }else{
            this.InsertFirst(e);
        }
    }
    public void DeleteFirst(){
        if(head!=null){
            head = head.next;
        }else{
            System.out.println("Error");
        }
    }
    public void PrintList(){
        Node<E> temp = head;
        while(temp.next !=null){
            System.out.print(temp.data + "->");
            temp = temp.next;
        }
        System.out.println(temp.data);
    }
    public void reverse(){
        if(head!=null){
            Node<E> curr = head;
            Node<E> prev = null, next = null;
            while(curr != null){
                next = curr.next;
                curr.next = prev;
                prev = curr;
                curr = next;
            }   
            head = prev; 
        }
    }
    public int getSize(){
        Node<E> temp = head;
        int count = 0;
        while (temp!=null) { 
            count++;
            temp = temp.next;
        }
        return count;
    }
}

class Vagon{
    int klasa;
    int brPatnici;
    final int MaxPatnici = 10;
    ArrayList<Integer> idPatnik = new ArrayList<>();
    public Vagon(int klasa) {
        this.klasa = klasa;
    }
    public void VagonPecati(){
        for(int i=0;i<brPatnici;i++){
            System.err.print(idPatnik.get(i)+" ");
        }
    }
}

class Patnik{
    int brBilet;
    int klasa;

    public Patnik(int brBilet, int klasa) {
        this.brBilet = brBilet;
        this.klasa = klasa;
    }
    
}

public class App {
    public static void main(String[] args) throws Exception {
        SlinkedList<Vagon> voz = new SlinkedList<Vagon>();
        //0 za prva klasa
        Vagon v1 = new Vagon(0);
        Vagon v2 = new Vagon(1);
        Vagon v3 = new Vagon(1);

        voz.InsertLast(v1);
        voz.InsertLast(v2);
        voz.InsertLast(v3);

        Patnik p1 = new Patnik(1, 0);
        Patnik p2 = new Patnik(2,0);
        Patnik p3 = new Patnik(3,1);
        Patnik p4 = new Patnik(4,1);
        Patnik p5 = new Patnik(5,0);
        Patnik p6 = new Patnik(6,0);

        ArrayList<Patnik> patniciPrva = new ArrayList<>();
        ArrayList<Patnik> patniciVtora = new ArrayList<>();

        patniciPrva.add(p6);
        patniciPrva.add(p5);
        patniciVtora.add(p4);
        patniciVtora.add(p3);
        patniciPrva.add(p2);
        patniciPrva.add(p1);

        VnesiPatnici(voz,patniciPrva,patniciVtora);
    }

    private static void VnesiPatnici(SlinkedList<Vagon> voz, ArrayList<Patnik> patniciPrva, ArrayList<Patnik> patniciVtora) {
        
    }
}
