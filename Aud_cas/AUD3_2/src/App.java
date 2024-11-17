
public class App {
    public static void main(String[] args)  {
        SlinkedList<Integer> lista = new SlinkedList() ;
        lista.InsertFirst(1);
        lista.InsertLast(2);
        lista.InsertLast(3);
        lista.DeleteFirst();
        lista.InsertFirst(5);
        lista.PrintList();
        lista.reverse();
        lista.PrintList();
    }
}

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
}

