import java.util.HashMap;
import java.util.Map;

public class DisjointSet<T> {

    private final Map<T, T> parent = new HashMap<>();
    private final Map<T, Integer> size = new HashMap<>();
    private int count = 0;

    public void insert(T x) {
        if (parent.get(x) != null) return;

        parent.put(x, x);
        size.put(x, 1);
        count++;
    }

    public T root(T x) {
        var r = x;
        var s = parent.get(r);

        if (s == null)
            throw new IllegalArgumentException();

        while (r != s) {
            r = s;
            s = parent.get(s);
        }

        return r;
    }

    public boolean isConnected(T a, T b) {
        return a == b || root(a) == root(b);
    }

    public void union(T a, T b) {
        if (a == b) return;

        var rootA = root(a);
        var rootB = root(b);

        if (rootA == rootB) return;

        var sizeA = size.get(rootA);
        var sizeB = size.get(rootB);

        if (sizeA < sizeB) {
            parent.put(rootA, rootB);
            size.put(rootB, sizeA + sizeB);
        }
        else {
            parent.put(rootB, rootA);
            size.put(rootA, sizeA + sizeB);
        }

        count--;
    }

    public int count() {
        return count;
    }
}
