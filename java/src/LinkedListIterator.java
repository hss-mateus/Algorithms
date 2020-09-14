import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedListIterator<T> implements Iterator<T> {

    private LinkedList<T> current;

    public LinkedListIterator(LinkedList<T> list) {
        this.current = list;
    }

    public boolean hasNext() {
        return current != null;
    }

    public T next() {
        if (current == null)
            throw new NoSuchElementException();

        var value = current.value;
        current = current.next;
        return value;
    }
}
