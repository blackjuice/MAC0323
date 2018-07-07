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
		Stopwatch start = new Stopwatch(); 
		
		MinPQ<ComputationNumber> pqL = new MinPQ<ComputationNumber>();
		MinPQ<ComputationNumber> pqR = new MinPQ<ComputationNumber>();
		MinPQ<ComputationNumber> pqL_inv = new MinPQ<ComputationNumber>();
		MinPQ<ComputationNumber> pqR_inv = new MinPQ<ComputationNumber>();

		int N = Integer.parseInt(args[0]);
		//int N = 100;
		for (int i = 0; i <= N; i++) {
			for (int j = 0; j <= i; j++) {
				pqL.insert(new ComputationNumber(i, j, 0, 0));
				pqR.insert(new ComputationNumber(0, 0, i, j));
			}
		}

		for (int i = 0; i <= N; i++) {
			for (int j = 0; j <= i; j++) {
				pqL_inv.insert(new ComputationNumber(j, i, 0, 0));
				pqR_inv.insert(new ComputationNumber(0, 0, j, i));
			}
		}

		ComputationNumber left = pqL.delMin();
		ComputationNumber left_inv = pqL_inv.delMin();
		ComputationNumber right = pqR.delMin();
		ComputationNumber right_inv = pqR_inv.delMin();

		while(!pqL.isEmpty() && !pqR.isEmpty() && !pqL_inv.isEmpty() && !pqR_inv.isEmpty()) {
			if (left.sumL == right.sumR) {
				StdOut.println(left.a + "+" + left.b + "=" + left.sumL + " | "
		    				+ right.c + "+" + right.d +"="+ right.sumR);
				left = pqL.delMin();
				right = pqR.delMin();
			}

			else if (left.sumL == right_inv.sumR) {
				StdOut.println(left.a + "+" + left.b + "=" + left.sumL + " | "
		    				+ right_inv.c + "+" + right_inv.d +"="+ right_inv.sumR);
				left = pqL.delMin();
				right_inv = pqR_inv.delMin();
			}

			else if (left_inv.sumL == right.sumR) {
				StdOut.println(left_inv.a + "+" + left_inv.b + "=" + left_inv.sumL + " | "
		    				+ right.c + "+" + right.d +"="+ right.sumR);
				left_inv = pqL_inv.delMin();
				right = pqR.delMin();
			}

			else if (left_inv.sumL == right_inv.sumR) {
				StdOut.println(left_inv.a + "+" + left_inv.b + "=" + left_inv.sumL + " | "
		    				+ right_inv.c + "+" + right_inv.d +"="+ right_inv.sumR);
				left_inv = pqL_inv.delMin();
				right_inv = pqR_inv.delMin();
			}


			else if (left.sumL > right.sumR)
				right = pqR.delMin();
			else if (left_inv.sumL > right.sumR)
				right = pqR.delMin();
			else if (left.sumL > right_inv.sumR)
				right_inv = pqR_inv.delMin();
			else if (left_inv.sumL > right_inv.sumR)
				right_inv = pqR_inv.delMin();

			else if (left.sumL < right.sumR)
				left = pqL.delMin();
			else if (left.sumL < right_inv.sumR)
				left = pqL.delMin();
			else if (left_inv.sumL < right.sumR)
				left_inv = pqL_inv.delMin();
			else if (left_inv.sumL < right_inv.sumR)
				left_inv = pqL_inv.delMin();
		}




		double end = start.elapsedTime();
		StdOut.println("time = " + end);
	}
}

/*


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


		while(!pqL.isEmpty() && !pqR.isEmpty()) {
			ComputationNumber left = pqL.delMin();
			ComputationNumber right = pqR.delMin();
			StdOut.println(left.a + "+" + left.b + "=" + left.sumL + " | "
		    				+ right.c + "+" + right.d +"="+ right.sumR);
		}
*/




		while(!pqL.isEmpty() && !pqR.isEmpty() && !pqL_inv.isEmpty() && !pqR_inv.isEmpty()) {

				StdOut.println(left.a + "+" + left.b + "=" + left.sumL + " | "
		    				+ right.c + "+" + right.d +"="+ right.sumR
		    				+ "         " + left_inv.a + "+" + left_inv.b + "=" + left_inv.sumL + " | "
		    				+ right_inv.c + "+" + right_inv.d +"="+ right_inv.sumR);

				left = pqL.delMin();
				right = pqR.delMin();
				left_inv = pqL_inv.delMin();
				right_inv = pqR_inv.delMin();

		}

































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
		Stopwatch start = new Stopwatch(); 
		
		MinPQ<ComputationNumber> pqL = new MinPQ<ComputationNumber>();
		MinPQ<ComputationNumber> pqR = new MinPQ<ComputationNumber>();
		MinPQ<ComputationNumber> pqL_inv = new MinPQ<ComputationNumber>();
		MinPQ<ComputationNumber> pqR_inv = new MinPQ<ComputationNumber>();

		int N = Integer.parseInt(args[0]);
		//int N = 100;
		for (int i = 0; i <= N; i++) {
			for (int j = 0; j <= i; j++) {
				pqL.insert(new ComputationNumber(i, j, 0, 0));
				pqR.insert(new ComputationNumber(0, 0, i, j));
			}
		}

		for (int i = 0; i <= N; i++) {
			for (int j = 0; j <= i; j++) {
				pqL_inv.insert(new ComputationNumber(j, i, 0, 0));
				pqR_inv.insert(new ComputationNumber(0, 0, j, i));
			}
		}

		ComputationNumber left = pqL.delMin();
		ComputationNumber left_inv = pqL_inv.delMin();
		ComputationNumber right = pqR.delMin();
		ComputationNumber right_inv = pqR_inv.delMin();

		while(!pqL.isEmpty() && !pqR.isEmpty() && !pqL_inv.isEmpty() && !pqR_inv.isEmpty()) {

			if (left.sumL == right.sumR) {
				StdOut.println(left.a + "+" + left.b + "=" + left.sumL + " | "
		    				+ right.c + "+" + right.d +"="+ right.sumR);
				left = pqL.delMin();
				right = pqR.delMin();
			}

			else if (left.sumL == right_inv.sumR) {
				StdOut.println(left.a + "+" + left.b + "=" + left.sumL + " | "
		    				+ right_inv.c + "+" + right_inv.d +"="+ right_inv.sumR);
				left = pqL.delMin();
				right_inv = pqR_inv.delMin();
			}

			else if (left_inv.sumL == right.sumR) {
				StdOut.println(left_inv.a + "+" + left_inv.b + "=" + left_inv.sumL + " | "
		    				+ right.c + "+" + right.d +"="+ right.sumR);
				left_inv = pqL_inv.delMin();
				right = pqR.delMin();
			}

			else if (left_inv.sumL == right_inv.sumR) {
				StdOut.println(left_inv.a + "+" + left_inv.b + "=" + left_inv.sumL + " | "
		    				+ right_inv.c + "+" + right_inv.d +"="+ right_inv.sumR);
				left_inv = pqL_inv.delMin();
				right_inv = pqR_inv.delMin();
			}

		}




		double end = start.elapsedTime();
		StdOut.println("time = " + end);
	}
}

/*


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


		while(!pqL.isEmpty() && !pqR.isEmpty()) {
			ComputationNumber left = pqL.delMin();
			ComputationNumber right = pqR.delMin();
			StdOut.println(left.a + "+" + left.b + "=" + left.sumL + " | "
		    				+ right.c + "+" + right.d +"="+ right.sumR);
		}
*/