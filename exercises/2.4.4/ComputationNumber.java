/*************************************************************************
 *  ano:            2015
 *  aluno:          Lucas Sung Jun Hong
 *  n.USP:          812 432 9
 *
 *  exercise:       2.4.4
 *
 *  Dependencies:   MinPQ.java
 *  Compilation:    javac-algs4 ComputationNumber.java
 *  Execution:      java-algs4 ComputationNumber
 *
 *	Mensagem ao monitor:
 *		O programa foi testado até N = 4000 ao inves de 100.000
 *		Quando testado para N = 5000, recebo erros como:
 *			java.lang.OutOfMemoryError: Java heap space
 *		ou
 * 			java.lang.OutOfMemoryError: GC overhead limit exceeded
 *
 *		Segui tutoriais e ajudas onlines para tentar resolver esse problema
 * 		e uma das recomendações foram usar os comandos:
 *			-XX:-UseGCOverheadLimit
 * 		ou
 *			java -Xmx2048m
 *
 *		Mas isso não resolveu o problema.
 *
 *
 *************************************************************************/

public class ComputationNumber implements Comparable<ComputationNumber> {
	private final long sumL;
	private final long sumR;
	private final int a;
	private final int b;
	private final int c;
	private final int d;

	public ComputationNumber(int a, int b, int c, int d) {
		this.sumL = (long) a + (long) 2*b*b;
		this.sumR = (long) 3*c*c*c + (long) 4*d*d*d*d;
		this.a = a;
		this.b = b;
		this.c = c;
		this.d = d;
	}

	public int compareTo(ComputationNumber that) {
	    if      (this.sumR < that.sumR || this.sumL < that.sumL) return -1;
	    else if (this.sumR > that.sumR || this.sumL > that.sumL) return +1;
	    else                          return  0;
	}

	/*	O programa cria 4 filas de prioridades: pqL, pqR, pqL_inv e pqR_inv.
	 *	O motivo para tal criação é para evitar o seguinte erro:
	 * 		Exception in thread "main" java.lang.OutOfMemoryError: GC overhead limit exceeded
	 * 			at ComputationNumber.main(ComputationNumber.java:55)
	 *
	 *  pqL contem:
	 *		a + 2*b*b
	 *  pqL_inv contem:
	 * 		b + 2*a*a
	 *  pqR contem:
	 *		3*c*c*c + 4*d*d*d*d
	 *	pqR_inv contem:
	 *		3*d*d*d + 4*c*c*c*c
	 *
	 *  O programa compara o minimo das 4 filas:
	 *		Caso possuam o mesmo resultado, o valor sera impresso;
	 *		Caso contrario, o menor valor sera retirado da fila.
	 *
	 */

	public static void main(String[] args) {
		
		MinPQ<ComputationNumber> pqL = new MinPQ<ComputationNumber>();
		MinPQ<ComputationNumber> pqR = new MinPQ<ComputationNumber>();
		MinPQ<ComputationNumber> pqL_inv = new MinPQ<ComputationNumber>();
		MinPQ<ComputationNumber> pqR_inv = new MinPQ<ComputationNumber>();

		int N = 100000; // compilar para N = 4000 foi o maximo que o programa conseguiu resolver
						// algo alem N > 4000, obtem-se java.lang.OutOfMemoryError: GC overhead limit exceeded
		for (int i = 0; i <= N; i++) {
			for (int j = 0; j <= i; j++) {
				pqL.insert(new ComputationNumber(i, j, 0, 0));
				pqR.insert(new ComputationNumber(0, 0, i, j));
			}
		}

		for (int i = 0; i <= N; i++) {
			for (int j = 0; j <= i; j++) {
				pqL_inv.insert(new ComputationNumber(j, i, 0, 0));
				pqR_inv.insert(new ComputationNumber(0, 0, j, i));
			}
		}

		ComputationNumber left = pqL.delMin();
		ComputationNumber left_inv = pqL_inv.delMin();
		ComputationNumber right = pqR.delMin();
		ComputationNumber right_inv = pqR_inv.delMin();

		while(!pqL.isEmpty() && !pqR.isEmpty() && !pqL_inv.isEmpty() && !pqR_inv.isEmpty()) {
			/*-----------------------------------------------------------------------------
				quando temos um resultado igual, retiramos ambos elementos das filas
			-----------------------------------------------------------------------------*/
			if (left.sumL == right.sumR) {
				StdOut.println(left.a + "+" + left.b + " = "
		    				+ right.c + "+" + right.d +" = "+ right.sumR);
				left = pqL.delMin();
				right = pqR.delMin();
			}
			else if (left.sumL == right_inv.sumR) {
				StdOut.println(left.a + "+" + left.b + " = "
		    				+ right_inv.c + "+" + right_inv.d +" = "+ right_inv.sumR);
				left = pqL.delMin();
				right_inv = pqR_inv.delMin();
			}
			else if (left_inv.sumL == right.sumR) {
				StdOut.println(left_inv.a + "+" + left_inv.b + " = "
		    				+ right.c + "+" + right.d +" = "+ right.sumR);
				left_inv = pqL_inv.delMin();
				right = pqR.delMin();
			}
			else if (left_inv.sumL == right_inv.sumR) {
				StdOut.println(left_inv.a + "+" + left_inv.b + " = "
		    				+ right_inv.c + "+" + right_inv.d +" = "+ right_inv.sumR);
				left_inv = pqL_inv.delMin();
				right_inv = pqR_inv.delMin();
			}
			/*-----------------------------------------------------------------------------
				caso contrario, observamos qual o menor elemento das filas e retiramos ele
			-----------------------------------------------------------------------------*/
			else if (left.sumL > right.sumR && left_inv.sumL > right.sumR)
				right = pqR.delMin();
			else if (left.sumL > right_inv.sumR && left_inv.sumL > right_inv.sumR)
				right_inv = pqR_inv.delMin();

			else if (left.sumL < right.sumR && left.sumL < right_inv.sumR)
				left = pqL.delMin();
			else if (left_inv.sumL < right.sumR && left_inv.sumL < right_inv.sumR)
				left_inv = pqL_inv.delMin();
		}
	}
}