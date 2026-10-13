//Да се напише функција која како аргумент добива низа од N единечно поврзани листи, каде N е цел број кој корисникот го внесува од тастатура. Функцијата треба да ги спои N-те листи во една единечно поврзана листа, така што во крајната листа првин ќе се додаде првиот јазол од секоја листа, па вториот јазол, итн. се додека не се изминат сите листи и не се додадат сите елементи. Задачата може да ја решите или со SLinkedList или со LinkedList

import java.util.ArrayList;
import java.util.Scanner;

class Node<E> {

    protected E data;
    protected Node<E> next;

    public Node() {
        data = null;
        next = null;
    }

    public Node(E data, Node<E> next) {
        this.data = data;
        this.next = next;
    }
}

class SlinkedList<E> {

    private Node<E> head;

    public Node<E> getHead() {
        return head;
    }

    public SlinkedList() {
        head = null;
    }

    public void InsertFirst(E e) {
        Node<E> first = new Node(e, head);
        head = first;
    }

    public void InsertAfter(E e, Node<E> n) {
        if (n != null) {
            Node<E> node = new Node(e, n.next);
            n.next = node;
        } else {
            System.out.println("Error");
        }
    }

    public void setHead(Node<E> n) {

    }

    public void InsertBefore(E e, Node<E> n) {
        if (head != null) {
            Node<E> temp = head;
            if (temp == n) {
                this.InsertFirst(e);
                return;
            }
            while (temp.next != n && temp.next != null) {
                temp = temp.next;
            }
            if (temp.next == n) {
                Node<E> node = new Node(e, n);
                temp.next = node;
            }
        }
    }

    public void InsertLast(E e) {
        if (head != null) {
            Node<E> temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            Node<E> last = new Node(e, null);
            temp.next = last;
        } else {
            this.InsertFirst(e);
        }
    }

    public void DeleteFirst() {
        if (head != null) {
            head = head.next;
        } else {
            System.out.println("Error");
        }
    }

    public int size() {
        Node<E> tmp = head;
        int size = 0;
        while (tmp != null) {
            size++;
            tmp = tmp.next;
        }
        return size;
    }

    public void PrintList() {
        Node<E> temp = head;
        while (temp.next != null) {
            System.out.print(temp.data + "->");
            temp = temp.next;
        }
        System.out.println(temp.data);
    }

}

public class App {

    public static void main(String[] args) throws Exception {
        int N;
        Scanner s = new Scanner(System.in);
        N = s.nextInt();
        int n, max = 0;
        ArrayList<SlinkedList<Integer>> lista = new ArrayList<SlinkedList<Integer>>();
        for (int i = 0; i < N; i++) {
            n = s.nextInt();
            if (max < n) {
                max = n;
            }
            SlinkedList<Integer> el = new SlinkedList<Integer>();
            for (int j = 0; j < n; j++) {
                el.InsertLast(j);
            }
            lista.add(el);
        }
        SlinkedList<Integer> l = new SlinkedList<Integer>();
        for (int i = 0; i < max; i++) {
            a:
            for (int j = 0; j < N; j++) {
                SlinkedList<Integer> pm = lista.get(j);
                Node<Integer> pomNode = pm.getHead();
                if (pomNode.next == null) {
                    l.InsertLast(pomNode.data);
                    lista.remove(j);
                    N--;
                    j--;
                    continue;
                }
                l.InsertLast(pomNode.data);
                pm.DeleteFirst();

            }
        }
        l.PrintList();
        System.out.println(max);
    }
}
