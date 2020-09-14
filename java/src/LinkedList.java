import java.util.Iterator;

public class LinkedList<T> implements Iterable<T> {

    public T value;
    public LinkedList<T> next;
    public LinkedList<T> prev;

    public LinkedList(T value) {
        if (value == null)
            throw new IllegalArgumentException();

        this.value = value;
    }

    public LinkedList(T value, LinkedList<T> prev) {
        this(value);
        this.prev = prev;
    }

    public LinkedList(T value, LinkedList<T> prev, LinkedList<T> next) {
        this(value, prev);
        this.next = next;
    }

    public Iterator<T> iterator() {
        return new LinkedListIterator<>(this);
    }
}
