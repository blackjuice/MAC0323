public class ComputationNumberL implements Comparable<ComputationNumberL> {

    public final long sum;

    public final int a;
    public final int b;

    public ComputationNumberL(int a, int b) {
        this.sum = (long) a + (long) 2*b*b;
        this.a = a;
        this.b = b;
    }
 
    public int compareTo(ComputationNumber that) {
        if      (this.sum < that.sum) return -1;
        else if (this.sum > that.sum) return +1;
        else                            return  0;
    }
}




/*
public class ComputationNumberL implements Comparable<ComputationNumberL> {

    public final long sumL;
    public final int a;
    public final int b;

    public ComputationNumberL(int a, int b) {
        this.sumL = (long) a + (long) 2*b*b;
        this.a = a;
        this.b = b;
    }
}
*/