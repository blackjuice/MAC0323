/*************************************************************************
 *  Compilation:  
 *  Execution:    
 *
 *************************************************************************/

public class Ex4 {

    public static void main(String[] args) {
	
        int M = Integer.parseInt(args[0]);
	int[][] coeff = {{0, 0, 0, 0, 4}, {0, 0, 0, 0, 0}, 
			 {0, 0, 0, 0, 0}, {3, 0, 0, 0, 0},
                         {0, 0, 0, 0, 0}};
	Bifunction S = new Poly(coeff);
	
        MinPQ<Pair> pq = new MinPQ<Pair>();
	for (int i = 1; i <= M; i++)
	    pq.insert(new Pair(i, 1, S));
        
        while (!pq.isEmpty()) {
            Pair s = pq.delMin();
	    StdOut.println(s);
	    Pair u = new Pair(s.i(), s.j() + 1, S);
	    if (u.j() <= M) pq.insert(u);
        }
    }
}
