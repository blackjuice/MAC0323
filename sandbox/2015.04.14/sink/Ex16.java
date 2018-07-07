/*************************************************************************
 *  Compilation:  
 *  Execution:    
 *
 *************************************************************************/

public class Ex16 {

    public static void main(String[] args) {
	
        int M = Integer.parseInt(args[0]);
	Bifunction S = new PowerSum(2, 5);
	
        MinPQ<Pair> pq = new MinPQ<Pair>();
	int i = 0;
	Pair t = new Pair(i, 0, S);
	while (t.f() <= M) {
	    pq.insert(t);
	    t = new Pair(t.i() + 1, 0, S);
	}
        
        while (!pq.isEmpty()) {
            Pair s = pq.delMin();
	    StdOut.println(s);
	    Pair u = new Pair(s.i(), s.j() + 1, S);
	    if (u.f() <= M) pq.insert(u);
        }
    }
}
