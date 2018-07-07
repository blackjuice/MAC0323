public class Logarithm {
	public static void main(String[] args) { 

		//double x = (int)(Math.log(14350) / Math.log(1.5) + 1e-10 );
		double x = Math.ceil ((Math.log(14350) / Math.log(1.5) + 1e-10 ) );
		StdOut.println(" >> x = " + x);
	}
}