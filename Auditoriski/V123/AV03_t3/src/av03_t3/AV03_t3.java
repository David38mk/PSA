package av03_t3;

import java.util.Scanner;

class Node<E> {
    protected E data;
    protected Node<E> prev, next;
    
    public Node() {
        data = null;
        prev = next = null;
    }
    
    public Node(E data, Node<E> prev, Node<E> next) {
        this.data = data;
        this.prev = prev;
        this.next = next;
    }
}

class DLinkedList<E> {
    private Node<E> head, tail;
    
    public Node<E> getHead() {
        return head;
    }
    
    public Node<E> getTail() {
        return tail;
    }
    
    public DLinkedList() {
        head = null;
        tail = null;
    }
    
    public void insertFirst(E e) {
        Node<E> first = new Node(e, null, head);
        
        if (head != null) {
            head.prev = first;
        }
        
        if (tail == null) {
            tail = first;
        }
        
        head = first;
    }
    
    public void insertLast(E e) {
        if (head != null) {
            Node<E> last = new Node(e, tail, null);
            tail.next = last;
            tail = last;
        } else {
            this.insertFirst(e);
        }
    }
}

public class AV03_t3 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        
        DLinkedList<Integer> list = new DLinkedList();
        
        int brEl = Integer.parseInt(input.nextLine());
        
        for (int i = 0; i < brEl; i++) {
            list.insertLast(i);
        }
        
        Node<Integer> tmp = list.getHead();
        
        while (tmp != list.getTail()) {
            System.out.print(tmp.data + " <-> ");
            tmp = tmp.next;
        }
        System.out.println(tmp.data);
    }
    
}
