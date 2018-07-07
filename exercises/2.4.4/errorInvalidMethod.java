public class ComputationNumberTheory implements Comparable<ComputationNumberTheory> {
    private final long sumL;
    private final long sumR;
    private final int a;
    private final int b;
    private final int c;
    private final int d;

    // create a new tuple (i, j, i^3 + j^3)
    public ComputationNumberTheory(int a, int b) {
        this.sum = (long) a + (long) 2*b*b;
        this.a = a;
        this.b = b;
    }


    public int compareTo(ComputationNumberTheory that) {
        if      (this.sumL < that.sumR) return -1;
        else if (this.sumL > that.sumR) return +1;
        else                            return  0;
    }


    public static void main(String[] args) {

        int N = 10;

        // initialize priority queue
        MinPQ<ComputationNumberTheory> pqL = new MinPQ<ComputationNumberTheory>();

        for (int a = 0; a <= N; a++) {
            for (int b = 0; b <= a; b++) {
                pqL.insert(new ComputationNumberTheory(a, b));
                pqL.insert(new ComputationNumberTheory(b, a));
            }
        }

        while(!pqL.isEmpty()) {
            StdOut.println(pqL.delMin());
        }
    }

}

/*
    public ComputationNumberTheoryR(int c, int d) {
        this.sumR = (long) 3*c*c*c + (long) 4*d*d*d*d;
        this.c = c;
        this.d = d;
    }
        MinPQ<ComputationNumberTheory> pqR = new MinPQ<ComputationNumberTheory>();

        for (int c = 0; c <= N; c++) {
            for (int d = 0; d <= c; d++) {
                pqR.insert(new ComputationNumberTheoryL(c, d));
                pqR.insert(new ComputationNumberTheoryL(d, c));
            }
        }
            StdOut.println(pqR.delMin());



*/