
import java.util.Stack;



class Element{
    int value;
    int count;
    public Element(int value){
        this.value = value;
    }
}

class Node<Element>{
    protected Element data;
    protected Node<Element> next;

    public Node(){
        data=null;
        next=null;
    }
    public Node(Element data, Node<Element> next){
        this.data = data;
        this.next = next;
    }
}

class Mag<Element>{
    private Node<Element> head;

    public Mag(){
        head = null;
    }
    public void push(Element e){
        Node<Element> first = new Node(e, head);
        head = first;
    }
    public void pop(){
        Stack<Element>
    }
    
}

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
    }
}
