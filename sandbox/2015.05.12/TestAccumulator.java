/*
 * Algs4th, p. 93
 *
 * $ java-algs4 TestAccumulator 1000
 * Mean (1000 values): 0.47984
 * $ java-algs4 TestAccumulator 1000
 * Mean (1000 values): 0.47882
 */

public class TestAccumulator
{
    public static void main(String[] args)
    {
	int T = Integer.parseInt(args[0]);
	Accumulator a = new Accumulator();
	for (int t = 0; t < T; t++)
	    a.addDataValue(StdRandom.uniform());
	StdOut.println(a);
    }
}
