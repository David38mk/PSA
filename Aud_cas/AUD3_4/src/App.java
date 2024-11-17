//sl lista zadaca 2


class Node<E extends Comparable>{
    protected E data;
    protected int count;
    protected Node<E> next;

    public Node(){
        data=null;
        next=null;
        count = 0;
    }
    public Node(E data, Node<E> next, int count){
        this.data = data;
        this.next = next;
        this.count = count;
    }
}

class SlinkedList<E extends Comparable>{
    private Node<E> head;

    public Node<E> getHead()
    {
        return head;
    }

    public SlinkedList(){
        head = null;
    }
    public void InsertFirst(E e){
        Node<E> first = new Node(e, head,0);
        head = first;
    }
    public void InsertAfter(E e, Node<E> n){
        if(n!= null){
            Node<E> node = new Node(e, n.next,0);
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
                Node<E> node = new Node(e, n,0);
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
            Node<E> last = new Node(e, null,0);
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
            System.out.print(temp.data +"("+ temp.count+")" +"->");
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
    public void deleteduplicates()
    {
        if(head!=null)
        {
            Node<E> tmp1=head;
            Node<E> tmp2=head.next;
            Node<E> prev = tmp1;

            while(tmp1.next != null)
            {
                while(tmp2 !=null)
                {
                   // if(tmp1.data == tmp2.data)//za int funkcionira no za objekti ne
                   if(tmp1.data.compareTo(tmp2.data) ==0)//ednakvi se
                    {
                        tmp1.count+=1;
                        //vtoroto poav temo 2 go briseme
                        prev.next = tmp2.next;

                    }
                    else{
                        prev=tmp2;

                    }
                    tmp2=tmp2.next;//prev go setirame sekade kade go dvizam temp2

                }
                tmp1=tmp1.next;
                prev=tmp1;
                tmp2=tmp1.next;
            }
        }
    }
}
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


