
import java.util.LinkedList;
import java.util.Scanner;

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
        int size = 0;
        Node<E> pomNode = head;
        while(pomNode!=null){
            size++;
            pomNode = pomNode.next;
        }
        return size;
    }
    public void otstraniElem(int N, Node<E> node){
        int size = getSize();
        int pozz = size - N;
        int i = 0;
        if(pozz<0){
            System.out.println("Out of bounds");
            return;
        }
        Node<E> pom = head;
        Node<E> prev = head;
        while(pom!=null){
            if(pom==head){
                if(i==pozz){
                    DeleteFirst();
                    break;
                }
                pom = pom.next;
            }else{
                if(i==pozz){
                    prev.next = pom.next;
                    pom = pom.next;
                    break;
                }
                prev = pom;
                pom = pom.next;
            }
            i++;
        }
    }
}


public class App {
    public static void main(String[] args) throws Exception {
        SlinkedList<Integer> list = new SlinkedList<Integer>();
        for(int i=0;i<10;i++){
            list.InsertLast(i+1);
        }
        int N;
        Scanner s = new Scanner(System.in);
        N = s.nextInt();
        
        list.otstraniElem(N,list.getHead());

        list.PrintList();
        
    }

    
}
