/**************************************************************************
 *   author:        Lucas Sung Jun Hong  
 *   n.USP:         812 4329
 * 
 *   Dependencies:  Stack.java
 *   exercise:      4.3.13
 *   http://introcs.cs.princeton.edu/java/43stack/
 * 
 *   % java-introcs 
 *   1 + 2 ) * 3 - 4 ) * 5 - 6 ) ) ) 
 *   ( ( 1 + 2 ) * ( ( 3 - 4 ) * ( 5 - 6 ) ) )
 *
**************************************************************************/

public class LeftParenthesis {
	
	public static void main (String[] args) {
		Stack<String>   alpha = new Stack<String>();
        String          s = StdIn.readLine();
        int             n = s.length();

        for (int i = 0; i < n; i++) {                   /* leitura da string */
            String      x = (s.substring(i, i + 1));    /* x armazena trecho da String s */

            if (x.equals(" "))      continue;           /* ignorar espacos */
            if (!x.equals(")"))     alpha.push(x);      /* inserir na pilha strings diferentes de ")" */

            /* Quando string == ")", retirar tres ultimos 
             * elementos da pilha, armazena-los em um elemento
             * e inserir na pilha */
            if (x.equals(")")) {
                String a = alpha.pop();
                String b = alpha.pop();
                String c = alpha.pop();

                alpha.push("( " + c + " " + b + " " + a + " )");
            }   
        }
        StdOut.println(alpha.pop());                    /* retirar o ultimo elemento da pilha */
	}
}
