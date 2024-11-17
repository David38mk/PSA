

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

    public void printList() {
        Node<E> tmp = head;
        if (tmp != null) {
            while (tmp.next != null) {
                System.out.print(tmp.data + " <-> ");
                tmp = tmp.next;
            }
            System.out.println(tmp.data);
        }
    }
}

public class App {
    public static void main(String[] args) {
        DLinkedList<Integer> list1 = new DLinkedList<Integer>();
        DLinkedList<Integer> list2 = new DLinkedList<Integer>();
        DLinkedList<Integer> list3;

        list1.insertLast(2);
        list1.insertLast(5);
        list1.insertLast(7);
        list1.insertLast(9);
        list1.insertLast(3);
        list1.insertLast(3);
        list1.insertLast(7);
        list1.insertLast(1);

        list2.insertLast(5);
        list2.insertLast(3);
        list2.insertLast(4);
        list2.insertLast(1);
        list2.insertLast(5);
        list2.insertLast(1);
        list2.insertLast(1);
        list2.insertLast(5);
        list2.insertLast(3);
        list2.insertLast(4);
        list2.insertLast(4);
        list2.insertLast(3);

        list3 = combineLists(list1, list2);

        list3.printList();
    }

    private static DLinkedList<Integer> combineLists(DLinkedList<Integer>
list1, DLinkedList<Integer> list2) {
        DLinkedList<Integer> list3 = new DLinkedList<Integer>();

        Node<Integer> listIterator1 = list1.getHead(), listIterator2 =
list2.getTail();
        int sum1, sum2;

        while (listIterator1 != null && listIterator2 != null) {
            sum1 = listIterator1.data + listIterator1.next.data;
            sum2 = listIterator2.data + listIterator2.prev.data;

            if (sum1 == sum2) {
                list3.insertLast(sum2);
            } else {
                list3.insertLast(0);
            }

            listIterator1 = listIterator1.next.next;
            listIterator2 = listIterator2.prev.prev;
        }

        while (listIterator1 != null) {
            list3.insertLast(listIterator1.data);
            listIterator1 = listIterator1.next;
        }

        while (listIterator2 != null) {
            list3.insertLast(listIterator2.data);
            listIterator2 = listIterator2.prev;
        }

        return list3;
    }
}