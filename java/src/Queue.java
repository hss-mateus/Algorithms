import java.util.Iterator;

public class Queue<T> implements Iterable<T> {

    private LinkedList<T> head;
    private LinkedList<T> tail;

    public void enqueue(T x) {
        if (head == null) {
            head = new LinkedList<>(x);
            tail = head;
            return;
        }

        tail.next = new LinkedList<>(x, tail);
        tail = tail.next;
    }

    public T dequeue() {
        var value = head.value;
        head = head.next;
        return value;
    }

    public Iterator<T> iterator() {
        return new LinkedListIterator<>(head);
    }
}
