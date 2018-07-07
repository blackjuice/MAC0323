/*
 * Algs4th, p. 95
 * 
 * $ java-algs4 TestVisualAccumulator 1000
 * Mean (1000 values):  0.49251
 * $ java-algs4 TestVisualAccumulator 1000
 * Mean (1000 values):  0.48841
 */

public class TestVisualAccumulator {
    public static void main(String[] args)
    {
	int T = Integer.parseInt(args[0]);
	VisualAccumulator a = new VisualAccumulator(T, 1.0);
	for (int t = 0; t < T; t++)
	    a.addDataValue(StdRandom.uniform());
	StdOut.println(a);
    }
}
