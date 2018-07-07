public class ComputationNumberR implements Comparable<ComputationNumberR> {

    public final long sumR;
    public final int c;
    public final int d;

    public ComputationNumberR(int c, int d) {
        this.sumR = (long) 3*c*c*c + (long) 4*d*d*d*d;
        this.c = c;
        this.d = d;
    }
    
}