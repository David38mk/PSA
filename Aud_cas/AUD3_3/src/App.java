
import java.util.Scanner;

class Node<E>{
    protected E data;
    protected Node<E> prev, next;

    public Node(){
        data = null;
        prev = next = null;
    }
    public Node(E data, Node<E> prev, Node<E> next){
        this.data = data;
        this.prev = prev;
        this.next = next;
    }
}

class DlinkedList<E>{
    private  Node<E> head; 
    private  Node<E> tail;
    public Node<E> getHead(){
        return head;
    }
    

    public Node<E> getTail(){
        return tail;
    }

    public DlinkedList(){
        head = null;
        tail = null;
    }

    public void InsertFirst(E e){
        Node<E> first = new Node(e, null, head);
        if(head !=null){
            head.prev = first;
        }
        if(tail == null){
            tail = first;
        }
        head = first;
    }
    public void InsertLast(E e){
        if (head!=null) {
            Node<E> last = new Node(e, tail, null);
            tail.next = last;
            tail = last;
        }else{
            this.InsertFirst(e);
        }
    }
}


public class App {
    public static void main(String[] args) throws Exception {
        Scanner input = new Scanner(System.in);
        DlinkedList<Integer> lista = new DlinkedList();
        int brEl = Integer.parseInt(input.nextLine());

        for(int i =0; i < brEl;i++){
            lista.InsertLast(i);
        }

        Node<Integer> temp = lista.getHead();
        while(temp!=lista.getTail()){
            System.out.print(temp.data + "<->");
            temp = temp.next;
        }
        System.out.println(temp.data);
    }
}
