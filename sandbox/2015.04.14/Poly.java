/*************************************************************************
 *  
 *************************************************************************/

public class Poly implements Bifunction {
    private int d;
    private int[][] coeffs;

    public Poly(int[][] coeffs) {
	this.d = coeffs.length - 1;
	this.coeffs = coeffs;
    }

    public long eval(int x, int y) {
	long[] xp = new long[d + 1];
	long[] yp = new long[d + 1];
	xp[0] = yp[0] = 1;
	for (int i = 1; i <= d; i++) {
	    xp[i] = xp[i-1] * x;
	    yp[i] = yp[i-1] * y;
	}
	long p = 0;
	for (int i = 0; i <= d; i++) 
	    for (int j = 0; j <= d; j++) 
		p += coeffs[i][j] * xp[i] * yp[j];

        return p;
    }

    public static void main(String[] args) {
	int[][] coeffs = StdArrayIO.readInt2D();
	int x = Integer.parseInt(args[0]);
	int y = Integer.parseInt(args[1]);
	Poly f = new Poly(coeffs);

	long z = f.eval(x, y);

	StdOut.println("f(" + x + ", " + y + ") = " + z);
    }
}
