//funk koja kako arg prima dve listi 

public class App {
    public static void main(String[] args) throws Exception {
        DlinkedList<Integer> list1 = new DlinkedList<Integer>();
        DlinkedList<Integer> list2 = new DlinkedList<Integer>();
        DlinkedList<Integer> list3;

        list1.InsertLast(2);
        list1.InsertLast(5);
        list1.InsertLast(7);
        list1.InsertLast(9);
        list1.InsertLast(3);
        list1.InsertLast(3);
        list1.InsertLast(7);
        list1.InsertLast(1);

        list2.InsertLast(5);
        list2.InsertLast(3);
        list2.InsertLast(4);
        list2.InsertLast(1);
        list2.InsertLast(5);
        list2.InsertLast(1);
        list2.InsertLast(1);
        list2.InsertLast(5);
        list2.InsertLast(3);
        list2.InsertLast(4);
        list2.InsertLast(4);
        list2.InsertLast(3);

        list3 = combineLists(list1,list2);

        list3.printList();

    }

    private static DlinkedList<Integer> combineLists(DlinkedList<Integer> list1, DlinkedList<Integer> list2) {
        DlinkedList<Integer> list3 = new DlinkedList<Integer>();

        Node<Integer> listIterator1 = list1.getHead(), listIterator2 = list2.getTail();

        int sum1, sum2;

        while(listIterator1 != null && listIterator2 !=null && listIterator1.next != null  && listIterator2.prev != null){
            sum1 = listIterator1.data + listIterator1.next.data;
            sum2 = listIterator2.data + listIterator1.prev.data;
            if(sum1 == sum2){
                list3.InsertLast(sum2);
            } else{
                list3.InsertLast(0);
            }
            listIterator1 = listIterator1.next.next;
            listIterator2 = listIterator2.prev.prev;

        }
        while(listIterator1!=null){
            list3.InsertLast(listIterator1.data);
            listIterator1 = listIterator1.next;
        }
        while(listIterator2!=null){
            list3.InsertLast(listIterator2.data);
            listIterator2 = listIterator2.next;
        }
        return list3;
    }

}


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
    public void printList(){
        Node<E> tmp = head;
        while(tmp.next!=null){
            System.out.print(tmp.data+" <-> ");
            tmp = tmp.next;
        }
        System.out.println(tmp.data);
    }
}

