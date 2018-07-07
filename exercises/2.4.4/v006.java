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
		for (int a = 0; a <= N; a++) {
			for (int b = 0; b <= a; b++) {
				pqL.insert(new ComputationNumber(a, b, 0, 0));
				pqL.insert(new ComputationNumber(b, a, 0, 0));
			}
		}

		for (int c = 0; c <= N; c++) {
			for (int d = 0; d <= c; d++) {
				pqR.insert(new ComputationNumber(0, 0, c, d));
				pqR.insert(new ComputationNumber(0, 0, d, c));
			}
		}
		
		ComputationNumber left = pqL.delMin();
		ComputationNumber right = pqR.delMin();
		while(!pqL.isEmpty() && !pqR.isEmpty()) {
			if (left.sumL == right.sumR) {
				StdOut.println(left.a + "+" + left.b + " = " + right.c + "+" + right.d + " = " + right.sumR);
				left = pqL.delMin();
				right = pqR.delMin();
			}
			else if (left.sumL > right.sumR)
				right = pqR.delMin();
			else if (left.sumL < right.sumR)
				left = pqL.delMin();
		}
	}
}

/*
				//StdOut.println(left.a + " + " + " 2* (" + left.b + ") ^2 " + " = " + " 3* (" + right.c + ") ^3 " + " + " + " 4* (" + right.d + ") ^4 " + " = " + right.sumR);
				//left = pqL.delMin();
		//    StdOut.println(left.a + "+" + left.b + "=" + left.sumL);


*/