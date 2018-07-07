/*
 * compiling: javac-algs4 operators.java
 * execution: java-algs4 operators < input.txt
 *
 * Example: n = 41
 * a = ++n; // a = 42, n = 42
 * a = n++; // a = 41, n = 42
 */

public class operators {
    
    public static void main(String[] args) {

	String[] 	s = StdIn.readAllStrings();
	int 		n = s.length;

	StdOut.println("initial n = " + n);

	String		x = s[n - 1];
	StdOut.println("x = " + x + " and n - 1 = " + (n - 1) + "([--n])");
	String		y = s[--n];
	StdOut.println("y = " + y + " and n = " + n + "([n--])");
	String		z = s[n];
	StdOut.println("z = " + z);
    }
}
