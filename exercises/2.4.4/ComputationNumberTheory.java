public class ComputationNumberTheory implements Comparable<ComputationNumberTheory> {

    private final long sum;
    private final int a;
    private final int b;

    // create a new tuple (i, j, i^3 + j^3)
    public ComputationNumberTheory(int a, int b) {
        this.sum = (long) a + (long) 2*b*b;
        this.a = a;
        this.b = b;
    }


    public int compareTo(ComputationNumberTheory that) {
        if      (this.sum < that.sum) return -1;
        else if (this.sum > that.sum) return +1;
        else                          return  0;
    }


    public static void main(String[] args) {

        int N = 10;

        // initialize priority queue
        MinPQ<ComputationNumberTheory> pq = new MinPQ<ComputationNumberTheory>();
        for (int a = 0; a <= N; a++) {
            for (int b = 0; b <= a; b++) {
                pq.insert(new ComputationNumberTheory(a, b));
                pq.insert(new ComputationNumberTheory(b, a));
            }
        }

        while(!pq.isEmpty()) {
            StdOut.println(pq.delMin());
        }
    }

}
