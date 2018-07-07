import java.util.Arrays;

public class Client {
    public static void main(String[] args) {
	BinarySearch B = new BinarySearch();
	System.out.println("Hello, World");

	In in = new In(args[0]);
        int[] whitelist = in.readAllInts();

        // sort the array
        Arrays.sort(whitelist);

        // read integer key from standard input; print if not in whitelist
        while (!StdIn.isEmpty()) {
            int key = StdIn.readInt();
            if (B.rank(key, whitelist) == -1)
                StdOut.println(key);

	}
    }
}
