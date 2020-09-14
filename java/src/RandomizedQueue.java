import edu.princeton.cs.algs4.StdRandom;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<T> implements Iterable<T> {

    private T[] items = (T[]) new Object[1];
    private int size = 0;

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void enqueue(T x) {
        if (x == null)
            throw new IllegalArgumentException();

        if (items.length == size)
            resize(size * 2);

        items[size++] = x;
    }

    public T dequeue() {
        if (isEmpty())
            throw new NoSuchElementException();

        if (items.length >= size * 4)
            resize(size * 2);

        var pos = StdRandom.uniform(0, size--);
        var item = items[pos];
        items[pos] = items[size];
        items[size] = null;

        return item;
    }

    public T sample() {
        if (isEmpty())
            throw new NoSuchElementException();

        var pos = StdRandom.uniform(0, size);
        return items[pos];
    }

    private void resize(int newSize) {
        T[] newItems = (T[]) new Object[newSize];

        System.arraycopy(items, 0, newItems, 0, size);

        items = newItems;
    }

    public Iterator<T> iterator() {
        return new RandomizedQueueIterator();
    }

    private class RandomizedQueueIterator implements Iterator<T> {

        private int[] randomIndexes = StdRandom.permutation(size);
        private int i = 0;

        public boolean hasNext() {
            return i <= randomIndexes.length - 1;
        }

        public T next() {
            if (i >= randomIndexes.length)
                throw new NoSuchElementException();

            return items[randomIndexes[i++]];
        }
    }
}
