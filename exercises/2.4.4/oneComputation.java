public class ComputationNumber implements Comparable<ComputationNumber> {

    private final long sum;
    private final int a;
    private final int b;

    // create a new tuple (i, j, i^3 + j^3)
    public ComputationNumber(int a, int b) {
        this.sum = (long) a + (long) 2*b*b;
        this.a = a;
        this.b = b;
    }


    public int compareTo(ComputationNumber that) {
        if      (this.sum < that.sum) return -1;
        else if (this.sum > that.sum) return +1;
        else                          return  0;
    }

    public static void main(String[] args) {

        int N = 4;
        // initialize priority queue
        MinPQ<ComputationNumber> pq = new MinPQ<ComputationNumber>();
        for (int a = 0; a <= 100000; a++) {
            for (int b = 0; b <= a; b++) {
                pq.insert(new ComputationNumber(a, b));
                pq.insert(new ComputationNumber(b, a));
                StdOut.print(".");
            }
        }
        StdOut.println();

        while(!pq.isEmpty()) {
            ComputationNumber x = pq.delMin();
            StdOut.println(x.sum);
        }
    }
}

/*
pq.insert(new ComputationNumber(a, b));
ComputationNumber x = pq.delMin();
StdOut.println("a(" + a + ") + b(" + b + ") = " + x.sum);
pq.insert(new ComputationNumber(b, a));
ComputationNumber y = pq.delMin();
StdOut.println("b(" + b + ") + a(" + a + ") = " + y.sum);
*/


public class ComputationNumber implements Comparable<ComputationNumber> {

    private final long sumL;
    private final long sumR;
    private final int a;
    private final int b;
    private final int c;
    private final int d;

    public ComputationNumberL(int a, int b) {
        this.sumL = (long) a + (long) 2*b*b;
        this.a = a;
        this.b = b;
    }
    public ComputationNumberR(int c, int d) {
        this.sumR = (long) 3*c*c*c + (long) 4*d*d*d*d;
        this.c = c;
        this.d = d;
    }

    public int compareTo(ComputationNumber that) {
        if      (this.sumL < that.sumR) return -1;
        else if (this.sumL > that.sumR) return +1;
        else                            return  0;
    }

    public static void main(String[] args) {

        int N = 4;
        // initialize priority queue
        MinPQ<ComputationNumberL> pq = new MinPQ<ComputationNumberL>();
        for (int a = 0; a <= 3; a++) {
            for (int b = 0; b <= a; b++) {
                pq.insert(new ComputationNumberL(a, b));
                pq.insert(new ComputationNumberL(b, a));
                StdOut.print(".");
            }
        }
        StdOut.println();

        while(!pq.isEmpty()) {
            ComputationNumberL x = pq.delMin();
            StdOut.println(x.sum);
        }
    }
}

/*
pq.insert(new ComputationNumber(a, b));
ComputationNumber x = pq.delMin();
StdOut.println("a(" + a + ") + b(" + b + ") = " + x.sum);
pq.insert(new ComputationNumber(b, a));
ComputationNumber y = pq.delMin();
StdOut.println("b(" + b + ") + a(" + a + ") = " + y.sum);
*/