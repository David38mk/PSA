
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import javax.lang.model.element.Element;
import javax.swing.event.InternalFrameAdapter;



public class App {
    public static void main(String[] args) throws Exception {
        SlinkedList<Integer> list = new SlinkedList<Integer>();

        for(int i = 0; i<10;i++){
            list.InsertFirst(i+1);
        }

        int[] array = {0,3,6,7};
        removeElementsAtIndex(list,array);
        list.PrintList();
        Queue<Integer> queue = formQueue(list);

        for(int element : queue){
            System.out.println(element+" ");
        }
    }

    private static void removeElementsAtIndex(SlinkedList<Integer> list, int[] array) {
        Node<Integer> listIterator = list.getHead();
        Node<Integer> previous = null;

        int listElementIndex = 0, arrayIterator =0;
        while(listIterator!=null && arrayIterator < array.length){
            if (listElementIndex == array[arrayIterator]) {
                if(previous != null){
                    previous.next = listIterator.next;
                }else{
                    list.setHead(listIterator.next);
                }
                arrayIterator++;
            }else{
                previous = listIterator;
            }
            
            listIterator = listIterator.next;
            listElementIndex++;
        }
    }

    private static Queue<Integer> formQueue(SlinkedList<Integer> list) {
        ArrayList<Integer> evenArray = new ArrayList<Integer>();
        ArrayList<Integer> oddArray = new ArrayList<Integer>();
        
        Node<Integer> listIterator = list.getHead();

        while(listIterator!=null){
            if(listIterator.data%2==0){
                evenArray.add(listIterator.data);
            }else{
                oddArray.add(listIterator.data);
            }
            listIterator = listIterator.next;
        }

        Collections.sort(evenArray);
        Collections.sort(oddArray);

        Queue<Integer> queue = new LinkedList<Integer>();

        for(int element : evenArray){
            queue.add(element);
        }
        for(int element : oddArray){
            queue.add(element);
        }
        return queue;
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
    public void setHead(Node<E> n){

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

    public int size(){
        Node<E> tmp = head;
        int size = 0;
        while(tmp!= null){
            size++;
            tmp = tmp.next;
        }
        return size;
    }

    public void PrintList(){
        Node<E> temp = head;
        while(temp.next !=null){
            System.out.print(temp.data + "->");
            temp = temp.next;
        }
        System.out.println(temp.data);
    }
}

