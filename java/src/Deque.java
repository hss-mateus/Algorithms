import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<T> implements Iterable<T> {

    private LinkedList<T> head;
    private LinkedList<T> tail;
    private int size = 0;

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void addFirst(T x) {
        if (head == null) {
            head = new LinkedList<>(x);
            tail = head;
        } else {
            head = new LinkedList<>(x, null, head);
        }

        size++;
    }

    public void addLast(T x) {
        if (head == null) {
            head = new LinkedList<>(x);
            tail = head;
        } else if (tail == null) {
            tail = new LinkedList<>(x);
        } else {
            tail.next = new LinkedList<>(x, tail);
            tail = tail.next;
        }

        size++;
    }

    public T removeFirst() {
        if (head == null)
            throw new NoSuchElementException();

        var value = head.value;
        head = head.next;
        size--;

        return value;
    }

    public T removeLast() {
        if (head == null)
            throw new NoSuchElementException();

        var value = tail.value;
        tail = tail.prev;
        tail.next = null;
        size--;

        return value;
    }

    public Iterator<T> iterator() {
        return new LinkedListIterator<>(head);
    }
}
