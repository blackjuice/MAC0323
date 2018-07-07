/*************************************************************************
 *  Compilation:  
 *  Execution:    
 *
 *************************************************************************/

public class Pair implements Comparable<Pair> {
    private final Bifunction f; 
    private final int i;
    private final int j;
    private final String S;

    public Pair(int i, int j, Bifunction f) {
        this.f = f;
        this.i = i;
        this.j = j;
	this.S = "f";
    }

    public Pair(int i, int j, Bifunction f, String S) {
        this.f = f;
        this.i = i;
        this.j = j;
	this.S = S;
    }

    public long f() { return f.eval(i, j); }
    public int i() { return i; }
    public int j() { return j; }

    public int compareTo(Pair that) {
        if      (this.f() < that.f()) return -1;
        else if (this.f() > that.f()) return +1;
        else                          return  0;
    }

    public String toString() {
	return this.S + "(" + i + ", " +  j + ") = " + this.f();
    }

    public static void main(String[] args) {

        int N = Integer.parseInt(args[0]);
	int[][] coeffs = StdArrayIO.readInt2D();
	Bifunction P = new Poly(coeffs);

        MinPQ<Pair> pq = new MinPQ<Pair>();
        for (int i = 0; i <= N; i++) 
            pq.insert(new Pair(i, 0, P));
        
        while (!pq.isEmpty()) {
            Pair s = pq.delMin();
	    StdOut.println(s);
	    if (s.j < N) pq.insert(new Pair(s.i, s.j + 1, P));
        }
    }

}
