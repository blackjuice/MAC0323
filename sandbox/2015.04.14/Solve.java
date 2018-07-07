/*************************************************************************
 *  Compilation:  
 *  Execution:    
 *
 *************************************************************************/

public class Solve {

    public static void main(String[] args) {

        int N = Integer.parseInt(args[0]);
	
	int[][] coeffs = StdArrayIO.readInt2D();
	Poly P = new Poly(coeffs);
	coeffs = StdArrayIO.readInt2D();
	Poly Q = new Poly(coeffs);

	Stopwatch sw = new Stopwatch();

        // initialize priority queue
        MinPQ<Pair> pql = new MinPQ<Pair>();
        MinPQ<Pair> pqr = new MinPQ<Pair>();

        for (int i = 1; i <= N; i++) {
            pql.insert(new Pair(i, 1, P, "LHS"));
        }
        for (int i = 1; i <= N; i++) {
            pqr.insert(new Pair(i, 1, Q, "RHS"));
        }

        // enumerate sums in ascending order, look for repeated sums
        while (!pql.isEmpty() && !pqr.isEmpty()) {
            Pair l = pql.min();
            Pair r = pqr.min();

	    if (l.f() < r.f()) {
		pql.delMin();
		if (l.j() < N) pql.insert(new Pair(l.i(), l.j() + 1, P, "LHS"));
		continue;
	    } else if (l.f() > r.f()) {
		pqr.delMin();
		if (r.j() < N) pqr.insert(new Pair(r.i(), r.j() + 1, Q, "RHS"));
		continue;
	    } else {
		StdOut.println(l.f() + ":");
		while (!pql.isEmpty() && pql.min().f() == l.f())
		    StdOut.println("  " + pql.delMin());
		while (!pqr.isEmpty() && pqr.min().f() == l.f())
		    StdOut.println("  " + pqr.delMin());
	    }

        }
	StdOut.println("Elapsed time: " + sw.elapsedTime());

    }

}
