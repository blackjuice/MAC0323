public class ComputationNumber implements Comparable<ComputationNumber> {
	private final long sumL;
	private final long sumR;
	private final int a;
	private final int b;
	private final int c;
	private final int d;

	public ComputationNumber(int a, int b, int c, int d) {
		this.sumL = (long) a + (long) 2*b*b;
		this.sumR = (long) 3*c*c*c + (long) 4*d*d*d*d;
		this.a = a;
		this.b = b;
		this.c = c;
		this.d = d;
	}

	public int compareTo(ComputationNumber that) {
	    if      (this.sumR < that.sumR || this.sumL < that.sumL) return -1;
	    else if (this.sumR > that.sumR || this.sumL > that.sumL) return +1;
	    else                          return  0;
	}

	public static void main(String[] args) {
		MinPQ<ComputationNumber> pqL = new MinPQ<ComputationNumber>();
		MinPQ<ComputationNumber> pqR = new MinPQ<ComputationNumber>();

		int N = Integer.parseInt(args[0]);
		//int N = 100;
		Stopwatch start = new Stopwatch(); 

		for (int i = 0; i <= N; i++) {
			for (int j = 0; j <= i; j++) {
				pqL.insert(new ComputationNumber(i, j, 0, 0));
				pqL.insert(new ComputationNumber(j, i, 0, 0));
				pqR.insert(new ComputationNumber(0, 0, i, j));
				pqR.insert(new ComputationNumber(0, 0, j, i));
			}
		}

		
		while(!pqL.isEmpty() && !pqR.isEmpty()) {
			ComputationNumber left = pqL.delMin();
			ComputationNumber right = pqR.delMin();
			StdOut.println(left.a + "+" + left.b + " = " + right.c + "+" + right.d + " = " + right.sumR);
		}








		double end = start.elapsedTime();
		StdOut.println("time = " + end);
	}
}
