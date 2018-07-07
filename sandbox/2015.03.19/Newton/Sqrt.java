/*************************************************************************
 *  Compilation:  javac Sqrt.java
 *  Execution:    java Sqrt
 *
 *  f(x) = c - x^2
 *
 *************************************************************************/

public class Sqrt implements DifferentiableFunction {
    private double c;

    public Sqrt(double c) {
        this.c = c;
    }

    // return f(x) = c - x^2
    public double eval(double x) {
        return c - x*x;
    }

    // return f'(x) = -2x
    public double diff(double x) {
        return -2 * x;
    }

    public static void main(String[] args) { 
	double c = Double.parseDouble(args[0]);
	double x = Double.parseDouble(args[1]);
	DifferentiableFunction f = new Sqrt(c);
	StdOut.println(f.eval(x));
	StdOut.println(f.diff(x));
    }
}
