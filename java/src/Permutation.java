import edu.princeton.cs.algs4.StdIn;

public class Permutation {

    public static void main(String[] args) {
        var k = Integer.parseInt(args[0]);
        var queue = new RandomizedQueue<String>();

        while (!StdIn.isEmpty()) {
            var str = StdIn.readString();
            queue.enqueue(str);
        }

        for (int i = 0; i < k; i++) {
            System.out.println(queue.dequeue());
        }
    }
}
