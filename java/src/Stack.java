import java.util.Iterator;
import java.util.NoSuchElementException;

public class Stack<T> implements Iterable<T> {

    private LinkedList<T> head;

    public void push(T x) {
        head = new LinkedList<>(x, null, head);
    }

    public T pop() {
        if (head == null)
            throw new NoSuchElementException();

        var value = head.value;
        head = head.next;

        return value;
    }

    public Iterator<T> iterator() {
        return new LinkedListIterator<>(head);
    }
}
